using System;
using System.Collections.Generic;
using System.IO;
using System.IO.Pipes;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media;

namespace GestureModality
{
    class AppServer
    {
        private NamedPipeServerStream server;
        private StreamReader reader;
        private bool isSpeakRunning;

        private MainWindow window;

        public bool IsSpeakRunning
        {
            get
            {
                return isSpeakRunning;
            }

            set
            {
                isSpeakRunning = value;
            }
        }

        public AppServer(MainWindow window) {
            isSpeakRunning = false;
            this.window = window;
        }

        private void resetWindow() {
            isSpeakRunning = false;
            window.Dispatcher.BeginInvoke((Action)(() =>
            {
                window.resetDefaultColor();
            }));
        }

        public void run()
        {
            Task.Factory.StartNew(() => {
            connectionLoop: while (true)
                {

                    server = new NamedPipeServerStream("GUESTURESCALLBACK");
                    reader = new StreamReader(server);
                    server.WaitForConnection();
                    Console.WriteLine("NOVA CONEXAO");


                    while (true)
                    {

                        var line = reader.ReadLine();
                        Console.WriteLine("RECEBI " + line);

                        switch (line)
                        {
                            case "<START>":
                                isSpeakRunning = true;
                                //Task.Delay(1500).ContinueWith(t => resetWindow());

                                break;
                            case "<FIRST_START>":
                                isSpeakRunning = true;
                                window.Dispatcher.BeginInvoke((Action)(() =>
                                {
                                    window.resetDefaultColor();
                                    window.changeColorTiles("HELP",Brushes.Green);
                                }));
                                break;
                            case "<STOP>":
                                resetWindow();
                                break;

                            case null:
                            case "<CLOSE>":
                                server.Close();
                                
                                goto connectionLoop;

                        }


                    }
                }

            });

        }
    }
}
