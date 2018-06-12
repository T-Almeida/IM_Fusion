using AppGui.Pages.Help;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace AppGui.Pages
{
    /// <summary>
    /// Interaction logic for PresentationPage.xaml
    /// </summary>
    public partial class PresentationPage : Page
    {
        private List<Page> pages;
        private MainWindow window;

        public PresentationPage(MainWindow mainWindow)
        {
            Console.WriteLine("ENTROU");
            InitializeComponent();
            this.pages = new List<Page>();
            this.window = mainWindow;
            pages.Add(new CanteensHelp());
            pages.Add(new ParksHelp());
            pages.Add(new TicketsHelp());
            pages.Add(new WeatherHelp());
            pages.Add(new NewsHelp());
            window.isInHelpPage = true;
            Task.Factory.StartNew(() => startSlideShow());
        }

        public void startSlideShow()
        {
            for (int i = 0; ; i++)
            {
                if (window.isInHelpPage)
                {

                    window.Dispatcher.BeginInvoke((Action)(() =>
                    {
                        window.NavigationService.Navigate(pages.ElementAt(i % pages.Count));
                    }));

                    Thread.Sleep(5000);
                }

                else
                {
                    break;
                }
            }
        }
    }
}
