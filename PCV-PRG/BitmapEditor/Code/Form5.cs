using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Imaging;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BitmapEditor
{
    public partial class Form5 : Form
    {
        Bitmap obr;
        Bitmap obrPom;
        double x;

        public Form5(Bitmap picture)
        {
            InitializeComponent();
            obrPom = obr = picture;
            pictureBox1.Image = obr;
            label6.Text = "1";
            label7.Text = "1";
            label8.Text = "1";
            label9.Text = "1";
            label10.Text = "1";
        }

        private void upravBitmapu()
        {
            var br = (double)HSBrightness.Value / 100;
            //var red = (double)HSRed.Value / 100;
            //var ct = (double)hScrollBar3.Value / 100;
            Image img = new Bitmap(obr, obr.Width, obr.Height);
            Graphics gr = Graphics.FromImage(img);
            ColorMatrix colorMatrix = new ColorMatrix(new float[][]{new float[] { (float)HSRed.Value, 0, 0, 0, 0 },
                                                                    new float[] { 0, (float)HSGreen.Value, 0, 0, 0 },
                                                                    new float[] { 0, 0, (float)HSBlue.Value, 0, 0 },
                                                                    new float[] { 0, 0, 0, (float)hScrollBar3.Value, 0 },
                                                                    new float[] { (float)br, (float)br, (float)br, 0, 1 } });
            ImageAttributes iAtr = new ImageAttributes();
            iAtr.SetColorMatrix(colorMatrix);
            gr.DrawImage(img, new Rectangle(0, 0, img.Width, img.Height), 0, 0, img.Width, img.Height, GraphicsUnit.Pixel, iAtr);
            gr.Dispose();
            pictureBox1.Image = img;
        }

        private void HSRed_ValueChanged(object sender, EventArgs e)
        {
            label6.Text = Convert.ToString(HSRed.Value);
            upravBitmapu();
        }

        private void HSGreen_ValueChanged(object sender, EventArgs e)
        {
            label7.Text = Convert.ToString(HSGreen.Value);
            upravBitmapu();
        }

        private void HSBlue_ValueChanged(object sender, EventArgs e)
        {
            label8.Text = Convert.ToString(HSBlue.Value);
            upravBitmapu();
        }

        private void HSBrightness_ValueChanged(object sender, EventArgs e)
        {
            label10.Text = Convert.ToString(HSBrightness.Value);
            upravBitmapu();
        }

        private void hScrollBar3_ValueChanged(object sender, EventArgs e)
        {
            label9.Text = Convert.ToString(hScrollBar3.Value);
            upravBitmapu();
        }

        private void Form5_Load(object sender, EventArgs e)
        {

        }
    }
}
