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

namespace AppGui
{
    /// <summary>
    /// Interaction logic for CanteensPage.xaml
    /// </summary>
    public partial class CanteensPage : Page
    {
        public CanteensPage(List<CanteenData> meals)
        {
            InitializeComponent();
            loadData(meals);
        }

        private void loadData(List<CanteenData> meals)
        {
            List<CanteenData> filteredList = meals.Where(x => x.Disabled.Equals("0")).ToList();
            cantinas.ItemsSource = filteredList;
        }
    }
}
