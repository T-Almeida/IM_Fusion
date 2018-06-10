using System;

using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO.Pipes;
using System.IO;
namespace AppGui
{
    class GesturesClient
    {
        private NamedPipeClientStream clientGestures;
        private StreamWriter writerGuestures;

        private NamedPipeClientStream clientSpeak;
        private StreamWriter writerSpeak;

        private Action ttsGreatingsCallback;

        public GesturesClient(Action ttsGreatingsCallback)
        {

            clientGestures = new NamedPipeClientStream("GUESTURESCALLBACK");
            writerGuestures = new StreamWriter(clientGestures);

            clientSpeak = new NamedPipeClientStream("SPEAKCALLBACK");
            writerSpeak = new StreamWriter(clientSpeak);

            this.ttsGreatingsCallback = ttsGreatingsCallback;

        }

        public void connect() {
            Task.Factory.StartNew(()=> {
                Console.WriteLine("Wait connection to GUESTURES AND SPEAK");
                clientGestures.Connect();
                clientSpeak.Connect();
                Console.WriteLine("Connected");
                ttsGreatingsCallback();
            });
            
            
        }

        private void send(string message, Action f) {

            try
            {
                writerGuestures.WriteLine(message);
                writerGuestures.Flush();

                writerSpeak.WriteLine(message);
                writerSpeak.Flush();
            }
            catch (Exception e)
            { //Para o caso do server ter levado reboot a meio da conexao
                Console.WriteLine("Retry connect because of error: " + e.Message);
                /*
                Task.Factory.StartNew(() =>
                {
                    Console.WriteLine("Wait connection");
                    if (!clientGestures.IsConnected)
                        clientGestures.Connect();
                    if (!clientSpeak.IsConnected)
                        clientSpeak.Connect();
                    Console.WriteLine("Connected");
                    f();
                });*/
            }

        }
        private void send(string message,bool news = false)
        {
            if (!news) { //if dynamic add dont send to guestures
                try
                {
                    writerGuestures.WriteLine(message);
                    writerGuestures.Flush();
                }
                catch (Exception e) { Console.WriteLine("ATENçÃO esta execao aconteceu!!!! linha 65 nSpeachClient ver "); }
            }

            try
            {
                writerSpeak.WriteLine(message);
                writerSpeak.Flush();
            }
            catch (Exception e) { Console.WriteLine("ATENçÃO esta execao aconteceu!!!! linha 65 nSpeachClient ver "); }

        }

        public void sendTtsStop() {
            send("<STOP>", sendTtsStop);
        }

        public void sendTtsStart()
        {
            send("<START>", sendTtsStart);
        }

        public void sendTtsFirstStart()
        {
            send("<FIRST_START>", sendTtsFirstStart);
        }

        public void sendDynamicNews(List<string> news)
        {
            Console.WriteLine("\nADD DYNAMIC\n");
            send("<DYNAMICADD>" + String.Join("|", news),true);
        }

        public void close() {
            send("<CLOSE>");
            clientGestures.Close();
            clientSpeak.Close();
        }
    }
}
