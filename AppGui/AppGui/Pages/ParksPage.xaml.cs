﻿using AppGui.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
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
    /// Interaction logic for ParksPage.xaml
    /// </summary>
    public partial class ParksPage : Page
    {


        public ParksPage(List<ParkData> parks) {
            InitializeComponent();
            loadData(parks);
        }

        private void loadData(List<ParkData> parks)
        {
            parques.ItemsSource = parks;
        }
    }
}
