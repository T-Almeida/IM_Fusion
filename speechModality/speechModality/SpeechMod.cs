using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using mmisharp;
using Microsoft.Speech.Recognition;
using System.Windows.Controls;

namespace speechModality
{
    public class SpeechMod
    {
        private SpeechRecognitionEngine sre;
        private Grammar grMain;
        private Grammar grYesNo;
        public event EventHandler<SpeechEventArg> Recognized;

        private List<Tuple<string, string>> specialCharacters;


        private AppServer appServer;

        protected virtual void onRecognized(SpeechEventArg msg)
        {
            EventHandler<SpeechEventArg> handler = Recognized;
            if (handler != null)
            {
                handler(this, msg);
            }
        }

        private LifeCycleEvents lce;
        private MmiCommunication mmic;

        public SpeechMod(TextBox textBox)
        {


            specialCharacters = new List<Tuple<string, string>>();
            specialCharacters.Add(new Tuple<string, string>("é","<e>"));
            specialCharacters.Add(new Tuple<string, string>("í", "<i>"));
            specialCharacters.Add(new Tuple<string, string>("ã", "<a_till>"));


            Console.WriteLine("OK...");
            //init LifeCycleEvents..
            lce = new LifeCycleEvents("ASR", "FUSION","speech-1", "acoustic", "command"); // LifeCycleEvents(string source, string target, string id, string medium, string mode)
            mmic = new MmiCommunication("localhost",9876,"User1", "ASR");  //PORT TO FUSION - uncomment this line to work with fusion later
            //mmic = new MmiCommunication("localhost", 8000, "User1", "ASR"); // MmiCommunication(string IMhost, int portIM, string UserOD, string thisModalityName)

            mmic.Send(lce.NewContextRequest());

            

            //load pt recognizer
            sre = new SpeechRecognitionEngine(new System.Globalization.CultureInfo("pt-PT"));
            grMain = new Grammar(Environment.CurrentDirectory + "\\ptG.grxml");
            grMain.Name = "Main Grammar";
            grMain.Enabled = true;

            grYesNo = new Grammar(Environment.CurrentDirectory + "\\yesNoGrammar.grxml");
            grYesNo.Name = "YesNo Grammar";
            grYesNo.Enabled = false;

            sre.LoadGrammar(grMain);
            sre.LoadGrammar(grYesNo);

            sre.SetInputToDefaultAudioDevice();
            sre.RecognizeAsync(RecognizeMode.Multiple);
            sre.SpeechRecognized += Sre_SpeechRecognized;
            sre.SpeechHypothesized += Sre_SpeechHypothesized;

            //server to receive commands from APP!!
            appServer = new AppServer(sre, textBox, resetGrammar);
            appServer.run();

            

            
        }

        private void resetGrammar() {
            grMain.Enabled = true; //may cause problems TODO
            grYesNo.Enabled = false;
        }

        private void Sre_SpeechHypothesized(object sender, SpeechHypothesizedEventArgs e)
        {
            onRecognized(new SpeechEventArg() { Text = e.Result.Text, Confidence = e.Result.Confidence, Final = false });
        }

        private void switchYesNoGrammar() {
            foreach (Grammar grIn in sre.Grammars)
            {
                if (grIn.Name.Equals("Main Grammar"))
                {
                    grIn.Enabled = false;
                    grMain = grIn;
                }
            }
            grYesNo.Enabled = true;

        }

        private void Sre_SpeechRecognized(object sender, SpeechRecognizedEventArgs e)
        {
            onRecognized(new SpeechEventArg(){Text = e.Result.Text, Confidence = e.Result.Confidence, Final = true});

            
            StringBuilder json = new StringBuilder("{");

            json.Append("\"source\":\"VOZ\",");

            if (grYesNo.Enabled)
            {
                json.Append("\"type\":\"YESNO\",");
                if (e.Result.Confidence > 0.80)
                {
                    resetGrammar();
                    json.Append("\"confidence\":\"GOOD\",");
                }
                else {
                    json.Append("\"confidence\":\"BAD\",");
                }
            }
            else {

                resetGrammar();
                json.Append("\"type\":\"NORMAL\",");

                if (e.Result.Confidence > 0.7)
                {
                    json.Append("\"confidence\":\"GOOD\",");
                }
                else if (e.Result.Confidence > 0.30)
                {
                    json.Append("\"confidence\":\"MEDIUM\",");

                    //handle special characters
                    string inputText = e.Result.Text;
                    foreach (var t in specialCharacters) {
                        inputText = inputText.Replace(t.Item1,t.Item2);
                    }
                    
                    json.Append("\"inputText\":\"" + inputText + "\",");
                    switchYesNoGrammar(); //this method change grammars Enabled Method
                }
                else
                {
                    json.Append("\"confidence\":\"BAD\",");
                }
            }
            

            

            //SEND
            // IMPORTANT TO KEEP THE FORMAT {"recognized":["SHAPE","COLOR"]}
            json.Append("\"recognized\": [");
            foreach (var resultSemantic in e.Result.Semantics)
            {
                json.Append("\"" + resultSemantic.Value.Value + "\",");
            }
            json.Remove(json.Length-1,1);
            
            json.Append("]}");

            Console.WriteLine(json.ToString());

            var exNot = lce.ExtensionNotification(e.Result.Audio.StartTime+"", e.Result.Audio.StartTime.Add(e.Result.Audio.Duration)+"",e.Result.Confidence, json.ToString());
            mmic.Send(exNot);
        }
    }
}
