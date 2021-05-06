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

namespace BitmapEditor
{
    public partial class Form3 : Form
    {
        Bitmap obr;
        Bitmap obrPom;
        int i = 0;
        int name;
        ArrayList aL = new ArrayList();
        Panel[] panel = new Panel[20];


        public Form3(Bitmap picture)
        {
            InitializeComponent();
            obr = picture;
            pictureBox1.Image = obr;
            obrPom = obr;
        }

        private void pictureBox1_MouseClick(object sender, MouseEventArgs e)
        {
            Bitmap sourceBitmap = (Bitmap)this.pictureBox1.Image;

            if ((!aL.Contains(sourceBitmap.GetPixel(e.X, e.Y)))&&(i < 20))
            {
                aL.Add(sourceBitmap.GetPixel(e.X, e.Y));
                panel[i] = new Panel();
                panel[i].Width = 60;
                panel[i].Height = 60;
                if(i > 10)
                {
                    panel[i].Location = new Point(13 + 65 * (i - 9), 78);
                }
                else
                {
                    panel[i].Location = new Point(13 + 65 * i, 13);
                }
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
            name = Convert.ToInt32(pnl.Name.ToString());
            nahradBarvu(panel[name].BackColor);
        }

        private void nahradBarvu(Color barva)
        {
            int x = 0;
            int y = 0;
            Color pixelcolor;
            
            while(true)
            {
                if(x != obr.Width)
                {
                    pixelcolor = obr.GetPixel(x, y);
                    if(barva == pixelcolor)
                    {
                        obrPom.SetPixel(x, y, button2.BackColor);
                    }
                }
                else
                {
                    y++;
                    if(y > obr.Height - 1)
                    {
                        break;
                    }
                    x = 0;
                }
                x++;
            }
            panel[name].BackColor = button2.BackColor;
            pictureBox1.Image = obrPom;
        }

        private void nahradKazdouBarvu()
        {
            if (i > 12)
            {
                int x = 0;
                int y = 0;

                while (true)
                {
                    if (x != obr.Width)
                    {
                        obr.GetPixel(x, y);
                        for (int index = 0; index < 20; index++)
                        {
                            if (panel[index] != null)
                            {
                                if (panel[index].BackColor == obr.GetPixel(x, y))
                                {
                                    obrPom.SetPixel(x, y, button2.BackColor);
                                }
                            }
                        }
                    }
                    else
                    {
                        y++;
                        if (y > obr.Height - 1)
                        {
                            break;
                        }
                        x = 0;
                    }
                    x++;
                }

                for (int q = 0; q < i; q++)
                {
                    panel[q].BackColor = button2.BackColor;
                }

                pictureBox1.Image = obrPom;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            nahradKazdouBarvu();
            pictureBox1.Image = obrPom;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            ColorDialog Dialog = new ColorDialog();
            Dialog.AllowFullOpen = true;
            Dialog.ShowHelp = true;
            Dialog.Color = button2.BackColor;
            Dialog.HelpRequest += new System.EventHandler(ColorDialog_ShowHelp);
            if (Dialog.ShowDialog() == DialogResult.OK)
            {
                button2.BackColor = Dialog.Color;
            }
        }

        private void ColorDialog_ShowHelp(object sender, System.EventArgs e)
        {
            MessageBox.Show("Please select any color from basic colors by clicking it or difine your own colors. " + "Chosen color will be used as replacment color for color you choose to replace.");
        }
    }
}
