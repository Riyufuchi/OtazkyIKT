using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace BitmapEditor
{
    public partial class PictureToAscii : Form
    {
        private static Bitmap picture;

        private string[] AsciiChars = { "██", "##", "@@", "%%", "==", "++", "::", "--", "..", "  "};
        private string pictureInAscii = " ";

        private static decimal podR = 0.2989M;
        private static decimal podG = 0.5866M;
        private static decimal podB = 0.1145M;
        private static decimal brightness;

        public PictureToAscii(Bitmap obrPom)
        {
            InitializeComponent();
            picture = obrPom;
        }

        private void PictureToAscii_Load(object sender, EventArgs e)
        {
            Color pixelColor;

            int red = 0;
            int green = 0;
            int blue = 0;
            int x = 0;
            int y = 0;
            int index = 0;

            if(Directory.Exists(Application.StartupPath + @"\Picture"))
            {
            }
            else
            {
                Directory.CreateDirectory(Application.StartupPath + @"\Picture");
            }

            if (File.Exists(Application.StartupPath + "\\Picture\\" + "picture" + ".txt"))
            {
                File.Delete(Application.StartupPath + "\\Picture\\" + "picture" + ".txt");
            }


            while (true)
            {
                pixelColor = picture.GetPixel(x, y);
                red = pixelColor.R;
                green = pixelColor.G;
                blue = pixelColor.B;
                brightness = (red * podR + green * podG + blue * podB);

                if (x != picture.Width - 1)
                {
                    if (brightness > 25)
                    {
                        if (brightness > 50)
                        {
                            if (brightness > 75)
                            {
                                if (brightness > 100)
                                {
                                    if (brightness > 125)
                                    {
                                        if (brightness > 150)
                                        {
                                            if (brightness > 175)
                                            {
                                                if (brightness > 200)
                                                {
                                                    if (brightness > 225)
                                                    {
                                                        index = 9;
                                                    }
                                                    else
                                                    {
                                                        index = 8;
                                                    }
                                                }
                                                else
                                                {
                                                    index = 7;
                                                }
                                            }
                                            else
                                            {
                                                index = 6;
                                            }
                                        }
                                        else
                                        {
                                            index = 5;
                                        }
                                    }
                                    else
                                    {
                                        index = 4;
                                    }
                                }
                                else
                                {
                                    index = 3;
                                }
                            }
                            else
                            {
                                index = 2;
                            }
                        }
                        else
                        {
                            index = 1;
                        }
                    }
                    else
                    {
                        index = 0;
                    }
                    pictureInAscii = pictureInAscii + AsciiChars[index];
                }
                else
                {
                    y++;
                    //webBrowser1.DocumentText = "<pre>" + "<Font size = 1>" + pictureInAscii + "<p></p>" + "<pre>";
                    //webBrowser1.DocumentText = "<html><body>" + "<pre>" + "<Font size = 1>" + pictureInAscii + "<p></p>" + "<pre>" + "</body></html>";
                    StreamWriter sw = new StreamWriter(Application.StartupPath +"\\Picture\\" +"picture" + ".txt", true);
                    sw.WriteLine(pictureInAscii);
                    sw.Close();
                    pictureInAscii = " ";
                    if (y > picture.Height - 1)
                    {
                        
                        StreamReader sr = new StreamReader(Application.StartupPath + "\\Picture\\" + "picture" + ".txt");
                        richTextBox1.Text = sr.ReadToEnd();
                        sr.Close();
                        break;
                    }
                    x = 0;
                }
                x++;
            }
        }
    }
}
