﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO.Pipes;
using System.IO;
using Microsoft.Speech.Recognition;
using System.Windows.Controls;
using System.Windows;

namespace speechModality
{
    class AppServer 
    {
        private NamedPipeServerStream server;
        private StreamReader reader;
        private SpeechRecognitionEngine sre;
        public TextBox textBox;
        private NewsGrammarModifier grammarModifier;

        private Action resetGrammar;

        public AppServer(SpeechRecognitionEngine sre, TextBox textBox, Action resetGrammar)
        {
            this.sre = sre;
            this.textBox = textBox;
            this.resetGrammar = resetGrammar;

            grammarModifier = new NewsGrammarModifier(sre);
        }

        public void run()
        {
            Task.Factory.StartNew(() => {
        connectionLoop: while (true) {

                server = new NamedPipeServerStream("SPEAKCALLBACK");
                reader = new StreamReader(server);
                server.WaitForConnection();
                Console.WriteLine("NOVA CONEXAO");
                resetGrammar();

                while (true)
                {

                    var line = reader.ReadLine();
                    Console.WriteLine("RECEBI " + line);

                    //contais
                    if (line != null && line.StartsWith("<DYNAMICADD>")) {
                        grammarModifier.addGrammar(line.Substring(12).Split('|'));
                        continue;
                    }

                    switch (line)
                    {
                        case "<START>":
                        case "<FIRST_START>":
                            sre.RecognizeAsyncStop();//try stop rec
                            //text.Text = "[RECOGNIZED STOPED]";
                            textBox.Dispatcher.BeginInvoke((Action)(() => {
                                textBox.FontWeight = FontWeights.Normal;
                                textBox.Text = "[RECOGNIZED STOPED]";
                            }));

                            break;
                        case "<STOP>":
                            sre.RecognizeAsync(RecognizeMode.Multiple);
                            //text.Text = "[RECOGNIZED RESTARTED]";
                            textBox.Dispatcher.BeginInvoke((Action)(() => {
                                textBox.FontWeight = FontWeights.Normal;
                                textBox.Text = "[SPEAK]";
                            }));

                            break;

                        case null:
                        case "<CLOSE>":
                            server.Close();
                            try
                            {
                                sre.RecognizeAsync(RecognizeMode.Multiple);

                                //text.Text = "[RECOGNIZED RESTARTED]";
                                textBox.Dispatcher.BeginInvoke((Action)(() =>
                                {
                                    textBox.FontWeight = FontWeights.Normal;
                                    textBox.Text = "[SPEAK]";
                                }));
                            }
                            catch (Exception e) { }
                            goto connectionLoop;

                        }
                        
  
                    }
                }

            });

        }

    }
}
