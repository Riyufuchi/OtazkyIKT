using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BitmapEditor
{
    public partial class Form1 : Form
    {
        public static Bitmap obr;
        public static Bitmap obrPom;
        public static Bitmap scaledPicture;
        public static Bitmap cropedPicture;

        private static decimal podR = 0.2989M;
        private static decimal podG = 0.5866M;
        private static decimal podB = 0.1145M;

        private static decimal brightness;
        private static decimal brightnessOldPixel;

        int positionX;
        int positionY;

        bool kresleni;

        Point pocBod = new Point();
        Point endBod = new Point();

        public Form1()
        {
            InitializeComponent();
        }

        #region WorkWithBitmap

        #region OpenFile
        private void otevřítToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog1.Filter = "PNG|*.png|JPG|*.jpg|Bitmap|*.bmp|All|*.*";
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                obr = new Bitmap(openFileDialog1.FileName);
                pictureBox1.Load(openFileDialog1.FileName);
                obrPom = new Bitmap(openFileDialog1.FileName);
            }
        }
        #endregion

        #region BlackAndWhite
        private void blackAndWhiteToolStripMenuItem_Click(object sender, EventArgs e)
        {
            label1.Text = Convert.ToString(hScrollBar1.Value);
            //Color[,] poleBarev = new Color[obr.Width, obr.Height];

            if (obrPom != null)
            {
                obr = new Bitmap(obrPom);
                Color pixelColor;

                for (int i = 0; i < obr.Width; i++)
                {
                    for (int j = 0; j < obr.Height; j++)
                    {
                        pixelColor = obr.GetPixel(i, j);
                        int red = pixelColor.R;
                        int green = pixelColor.G;
                        int blue = pixelColor.B;
                        brightness = (red * podR + green * podG + blue * podB);

                        if (brightness < hScrollBar1.Value)
                        {
                            pixelColor = Color.Black;
                        }
                        else
                        {
                            pixelColor = Color.White;
                        }

                        obr.SetPixel(i, j, pixelColor);
                    }
                }
                pictureBox2.Image = obr;
            }
            else
            {
                upozorneni();
            }
        }

        private void hScrollBar1_ValueChanged(object sender, EventArgs e)
        {
            label1.Text = Convert.ToString(hScrollBar1.Value);
            //Color[,] poleBarev = new Color[obr.Width, obr.Height];

            if (obrPom != null)
            {
                obr = new Bitmap(obrPom);
                Color pixelColor;

                for (int i = 0; i < obr.Width; i++)
                {
                    for (int j = 0; j < obr.Height; j++)
                    {
                        pixelColor = obr.GetPixel(i, j);
                        int red = pixelColor.R;
                        int green = pixelColor.G;
                        int blue = pixelColor.B;
                        brightness = (red * podR + green * podG + blue * podB);

                        if (brightness < hScrollBar1.Value)
                        {
                            pixelColor = Color.Black;
                        }
                        else
                        {
                            pixelColor = Color.White;
                        }

                        obr.SetPixel(i, j, pixelColor);
                    }
                }
                pictureBox2.Image = obr;
            }
        }
        #endregion

        #region Silluete
        private void sillueteToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (obrPom != null)
            {
                obr = new Bitmap(obrPom);
                Color pixelColor;

                for (int i = 0; i < obr.Width; i++)
                {
                    for (int j = 0; j < obr.Height; j++)
                    {
                        pixelColor = obr.GetPixel(i, j);
                        int red = pixelColor.R;
                        int green = pixelColor.G;
                        int blue = pixelColor.B;
                        brightnessOldPixel = (red * podR + green * podG + blue * podB);

                        if ((i < obr.Width - 1)&&(j < obr.Height - 1))
                        {
                            pixelColor = obr.GetPixel(i + 1, j + 1);
                            red = pixelColor.R;
                            green = pixelColor.G;
                            blue = pixelColor.B;
                            brightness = (red * podR + green * podG + blue * podB);
                        }

                        if (brightness < brightnessOldPixel - 45)
                        {
                            pixelColor = Color.Black;
                        }
                        else
                        {
                            pixelColor = Color.White;
                        }

                        obr.SetPixel(i, j, pixelColor);
                    }
                }
                pictureBox2.Image = obr;
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        #region Rotate
        private void rotateToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (obr != null)
            {
                Form2 frm2 = new Form2(obrPom);
                if (obrPom.Height < 1000)
                {
                    frm2.Width = obr.Height + 100;
                }
                else
                {
                    frm2.Width = 800;
                }
                if (obrPom.Width < 1000)
                {
                    frm2.Height = obr.Width + 125;
                }
                else
                {
                    frm2.Height = 600;
                }
                frm2.Show();
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        #region Scale
        private void scaleToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (obrPom != null)
            {
                obr = new Bitmap(obrPom);
                Color pixelColor;
                scaledPicture = new Bitmap(obr.Width * 2, obr.Height * 2);

                for (int i = 0; i < obr.Width; i++)
                {
                    for (int j = 0; j < obr.Height; j++)
                    {
                        pixelColor = obrPom.GetPixel(i, j);

                        scaledPicture.SetPixel(i * 2, j * 2, pixelColor);
                        scaledPicture.SetPixel(i * 2 + 1, j * 2, pixelColor);
                        scaledPicture.SetPixel(i * 2, j * 2 + 1, pixelColor);
                        scaledPicture.SetPixel(i * 2 + 1, j * 2 + 1, pixelColor);
                    }
                }
                pictureBox2.Image = scaledPicture;
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        #region Gray
        private void degresesOfGrayToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (obrPom != null)
            {
                obr = new Bitmap(obrPom);
                Color pixelColor;
                int barva;
                int red;
                int green;
                int blue;

                for (int i = 0; i < obr.Width; i++)
                {
                    for (int j = 0; j < obr.Height; j++)
                    {
                        pixelColor = obr.GetPixel(i, j);
                        red = pixelColor.R;
                        green = pixelColor.G;
                        blue = pixelColor.B;
                        brightness = (red * podR + green * podG + blue * podB);

                        /*
                         * if(svetlost < 0)
                         * {
                         * brightness = 0;
                         * }
                         * 
                         * if(svetlost > 255)
                         * {
                         * brightness = 255;
                         * }
                         */

                        barva = Convert.ToInt32(brightness);
                        pixelColor = Color.FromArgb(barva, barva, barva);
                        obr.SetPixel(i, j, pixelColor);
                    }
                }
                pictureBox2.Image = obr;
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        #region Crop
        private void pictureBox1_MouseDown(object sender, MouseEventArgs e)
        {
            pocBod = pictureBox1.PointToClient(Cursor.Position);
            kresleni = true;
        }

        private void pictureBox1_MouseMove(object sender, MouseEventArgs e)
        {
            if (kresleni)
            {
                if (obrPom != null)
                {
                    obr = new Bitmap(obrPom);
                    int tloustka = Convert.ToInt32(32);
                    Graphics grx = Graphics.FromImage(obr);

                    endBod = pictureBox1.PointToClient(Cursor.Position);
                    Pen pero = new System.Drawing.Pen(System.Drawing.Color.Red);
                    pero.Width = 1;
                    //grx.DrawRectangle(pero, pocBod.X, pocBod.Y, endBod.X, endBod.Y);
                    grx.DrawLine(pero, pocBod.X, pocBod.Y, endBod.X, pocBod.Y);
                    grx.DrawLine(pero, pocBod.X, pocBod.Y, pocBod.X, endBod.Y);
                    grx.DrawLine(pero, endBod.X, pocBod.Y, endBod.X, endBod.Y);
                    grx.DrawLine(pero, pocBod.X, endBod.Y, endBod.X, endBod.Y);
                    pictureBox1.Image = obr;
                }
            }
        }

        private void pictureBox1_MouseUp(object sender, MouseEventArgs e)
        {
            kresleni = false;
        }

        private void justCropToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if(pocBod.X > endBod.X)
            {
                if(pocBod.Y > endBod.Y)
                {
                    leftUp();
                }
                else
                {
                    leftdown();
                }
            }
            else
            {
                if (pocBod.Y > endBod.Y)
                {
                    rightUp();
                }
                else
                {
                    rightdown();
                }
            }
        }

        private void cropAndScaleToolStripMenuItem_Click(object sender, EventArgs e)
        {

            if (pocBod.X > endBod.X)
            {
                if (pocBod.Y > endBod.Y)
                {
                    leftUp();
                }
                else
                {
                    leftdown();
                }
            }
            else
            {
                if (pocBod.Y > endBod.Y)
                {
                    rightUp();
                }
                else
                {
                    rightdown();
                }
            }

            scaleCroppedPicture();
        }

        #region ActualCropping
        private void rightdown()
        {
            if (obrPom != null)
            {
                if (pocBod.X > 0)
                {
                    int x = pocBod.X + 1;
                    int y = pocBod.Y + 1;
                    int height = endBod.Y;
                    int width = endBod.X;

                    obr = new Bitmap(obr);
                    Color pixelColor;

                    cropedPicture = new Bitmap(width, height);

                    while (true)
                    {
                        pixelColor = obrPom.GetPixel(x, y);
                        if (y != endBod.Y)
                        {
                            cropedPicture.SetPixel(x, y, pixelColor);
                        }
                        else
                        {
                            x++;
                            if (x > endBod.X - 1)
                            {
                                break;
                            }
                            y = pocBod.Y;
                        }
                        y++;
                    }
                    pictureBox2.Image = cropedPicture;
                }
            }
            else
            {
                upozorneni();
            }
        }

        private void rightUp()
        {
            if (obrPom != null)
            {
                if (pocBod.X > 0)
                {
                    int x = endBod.X - 1;
                    int y = endBod.Y + 1;
                    int height = pocBod.Y;
                    int width = endBod.X;

                    obr = new Bitmap(obr);
                    Color pixelColor;

                    cropedPicture = new Bitmap(width, height);

                    while (true)
                    {
                        pixelColor = obrPom.GetPixel(x, y);
                        if (y < pocBod.Y)
                        {
                            cropedPicture.SetPixel(x, y, pixelColor);
                        }
                        else
                        {
                            x--;
                            if (x == pocBod.X - 1)
                            {
                                break;
                            }
                            y = endBod.Y + 1;
                        }
                        y++;
                    }
                    pictureBox2.Image = cropedPicture;
                }
            }
            else
            {
                upozorneni();
            }
        }

        private void leftdown()
        {
            if (obrPom != null)
            {
                if (pocBod.X > 0)
                {
                    positionX = pocBod.X;
                    positionY = pocBod.Y;
                    pocBod.X = endBod.X;
                    pocBod.Y = endBod.Y;
                    endBod.X = positionX;
                    endBod.Y = positionY;
                    int x = endBod.X - 1;
                    int y = endBod.Y + 1;
                    int height = pocBod.Y;
                    int width = endBod.X;

                    obr = new Bitmap(obr);
                    Color pixelColor;

                    cropedPicture = new Bitmap(width, height);

                    while (true)
                    {
                        pixelColor = obrPom.GetPixel(x, y);
                        if (y < pocBod.Y)
                        {
                            cropedPicture.SetPixel(x, y, pixelColor);
                        }
                        else
                        {
                            x--;
                            if (x == pocBod.X - 1)
                            {
                                break;
                            }
                            y = endBod.Y + 1;
                        }
                        y++;
                    }
                    pictureBox2.Image = cropedPicture;
                }
            }
            else
            {
                upozorneni();
            }
        }

        private void leftUp()
        {
            if (obrPom != null)
            {
                if (pocBod.X > 0)
                {
                    positionX = pocBod.X;
                    positionY = pocBod.Y;
                    pocBod.X = endBod.X;
                    pocBod.Y = endBod.Y;
                    endBod.X = positionX;
                    endBod.Y = positionY;
                    int x = pocBod.X + 1;
                    int y = pocBod.Y + 1;
                    int height = endBod.Y;
                    int width = endBod.X;

                    obr = new Bitmap(obr);
                    Color pixelColor;

                    cropedPicture = new Bitmap(width, height);

                    while (true)
                    {
                        pixelColor = obrPom.GetPixel(x, y);
                        if (y != endBod.Y)
                        {
                            cropedPicture.SetPixel(x, y, pixelColor);
                        }
                        else
                        {
                            x++;
                            if (x > endBod.X - 1)
                            {
                                break;
                            }
                            y = pocBod.Y;
                        }
                        y++;
                    }
                    pictureBox2.Image = cropedPicture;
                }
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        private void scaleCroppedPicture()
        {
            Color pixelColor;
            scaledPicture = new Bitmap(cropedPicture.Width * 2, cropedPicture.Height * 2);

            for (int i = 0; i < cropedPicture.Width; i++)
            {
                for (int j = 0; j < cropedPicture.Height; j++)
                {
                    pixelColor = cropedPicture.GetPixel(i, j);

                    scaledPicture.SetPixel(i * 2, j * 2, pixelColor);
                    scaledPicture.SetPixel(i * 2 + 1, j * 2, pixelColor);
                    scaledPicture.SetPixel(i * 2, j * 2 + 1, pixelColor);
                    scaledPicture.SetPixel(i * 2 + 1, j * 2 + 1, pixelColor);
                }
            }
            pictureBox2.Image = scaledPicture;
        }
        #endregion

        #region PictureToASCII
        private void pictureToASCIIToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (obrPom != null)
            {
                PictureToAscii frm3 = new PictureToAscii(obrPom);
                frm3.Show();
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        #region ColorTeplacment
        private void colorReplacmentToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (obr != null)
            {
                Form3 frm3 = new Form3(obrPom);
                frm3.Show();
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        #region Transparency
        private void transparencyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (obr != null)
            {
                Form4 frm4 = new Form4(obrPom);
                frm4.Show();
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        #region ColorColoration
        private void colorColorationToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (obr != null)
            {
                Form5 frm5 = new Form5(obrPom);
                frm5.Show();
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        #region Save
        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (obr != null)
            {
                saveFileDialog1.Filter = "PNG|*.png|JPG|*.jpg|Bitmap|*.bmp";
                saveFileDialog1.Title = "Save an Image File";
                if (saveFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    string path = saveFileDialog1.FileName;
                    Image obr = pictureBox1.Image;
                    switch (saveFileDialog1.FilterIndex)
                    {
                        case 1: obr.Save(path, System.Drawing.Imaging.ImageFormat.Png); break;
                        case 2: obr.Save(path, System.Drawing.Imaging.ImageFormat.Jpeg); break;
                        case 3: obr.Save(path, System.Drawing.Imaging.ImageFormat.Bmp); break;
                    }
                }
            }
            else
            {
                upozorneni();
            }
        }
        #endregion

        #endregion

        #region ResizeForm
        private void Form1_SizeChanged(object sender, EventArgs e)
        {
            pictureBox1.Width = (this.Width - 34) / 2;
            pictureBox1.Height = (this.Height - 116);
            pictureBox2.SetBounds(pictureBox1.Location.X + pictureBox1.Width + 10, pictureBox1.Location.Y, pictureBox1.Width - 14, pictureBox1.Height);
            hScrollBar1.SetBounds(pictureBox2.Location.X, pictureBox2.Location.Y + pictureBox1.Height + 5, pictureBox1.Width - 12, 21);
            label1.SetBounds(hScrollBar1.Location.X + hScrollBar1.Location.X / 2, hScrollBar1.Location.Y + 20, 25, 13);
            pictureBox1.Image = obrPom;
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            pictureBox1.Width = (this.Width - 34) / 2;
            pictureBox1.Height = (this.Height - 116);
            pictureBox2.SetBounds(pictureBox1.Location.X + pictureBox1.Width + 10, pictureBox1.Location.Y, pictureBox1.Width - 14, pictureBox1.Height);
            hScrollBar1.SetBounds(pictureBox2.Location.X, pictureBox2.Location.Y + pictureBox1.Height + 5, pictureBox1.Width - 12, 21);
            label1.SetBounds(hScrollBar1.Location.X + hScrollBar1.Location.X / 2, hScrollBar1.Location.Y + 20, 25, 13);
            pictureBox1.Refresh();
        }
        #endregion

        #region Methods
        private void upozorneni()
        {
            MessageBox.Show("Není vybrán obrázek", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
        #endregion
    }
}
