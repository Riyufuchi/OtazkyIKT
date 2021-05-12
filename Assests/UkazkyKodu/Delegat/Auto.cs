using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace UkolOM
{
    delegate void Delegat();

    class Auto
    {
        public event Delegat Troubeni;

        private Color barva;
        private int obsahMotoru;
        private string typMotoru;
        private int pocetDveri;
        private bool svitiSvetla;

        public Auto()
        {
            this.barva = Color.Yellow;
            this.obsahMotoru = 600;
            this.typMotoru = "Spalovací";
            this.pocetDveri = 3;
            this.svitiSvetla = false;
        }

        public Color getBarva()
        {
            return this.barva;
        }

        public int getPocetDveri()
        {
            return this.pocetDveri;
        }

        public void setPocetDeri(int value)
        {
            this.pocetDveri = value;
        }

        public bool SvitiSvetla
        {
            get { return this.svitiSvetla; }
            set { this.svitiSvetla = value; }
        }

        public void Zatroubeni(bool trub)
        {
            if (trub == true)
            {
                Troubeni();
            }
        }
    }
}
