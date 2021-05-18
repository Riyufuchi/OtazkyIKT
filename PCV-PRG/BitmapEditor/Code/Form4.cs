using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Collections;
using System.Drawing.Imaging;

namespace BitmapEditor
{
    public partial class Form4 : Form
    {
        Bitmap obr;
        Bitmap obrPom;
        int i = 0;
        ArrayList aL = new ArrayList();
        Panel[] panel = new Panel[11];
        ColorMap[] map = new ColorMap[11];

        public Form4(Bitmap picture)
        {
            InitializeComponent();
            for(int o = 0; o < 11; o++)
            {
                map[o] = new ColorMap();
            }
            obr = picture;
            pictureBox1.Image = obr;
            obrPom = obr;
        }

        private void pictureBox1_MouseClick(object sender, MouseEventArgs e)
        {
            Bitmap sourceBitmap = (Bitmap)this.pictureBox1.Image;

            if ((!aL.Contains(sourceBitmap.GetPixel(e.X, e.Y))) && (i < 11))
            {
                aL.Add(sourceBitmap.GetPixel(e.X, e.Y));
                panel[i] = new Panel();
                panel[i].Width = 60;
                panel[i].Height = 60;
                panel[i].Location = new Point(13 + 65 * i, 13);
                panel[i].Name = i.ToString();
                panel[i].BackColor = sourceBitmap.GetPixel(e.X, e.Y);
                panel[i].Show();
                panel[i].Parent = this;
                panel[i].Click += new EventHandler(Panel_Click);
                i++;
            }
        }

        private void Panel_Click(object sender, EventArgs e)
        {
            Panel pnl = (Panel)sender;
            i = Convert.ToInt32(pnl.Name.ToString());
            Bitmap newBtm = new Bitmap(obrPom.Width, obrPom.Height);
            map[i] = new ColorMap();
            map[i].OldColor = pnl.BackColor;
            map[i].NewColor = Color.FromArgb(0, 255, 255, 255);
            ImageAttributes ia = new ImageAttributes();
            ia.SetRemapTable(map);
            Graphics g = Graphics.FromImage(newBtm);
            g.DrawImage(pictureBox1.Image, new Rectangle(0, 0, obrPom.Width, obrPom.Height), 0, 0, obrPom.Width, obrPom.Height, GraphicsUnit.Pixel, ia);
            g.Dispose();
            pictureBox1.Image = newBtm;
        }

        private void Form4_Load(object sender, EventArgs e)
        {

        }
    }
}
