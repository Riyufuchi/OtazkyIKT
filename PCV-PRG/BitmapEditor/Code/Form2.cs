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
    public partial class Form2 : Form
    {
        private Bitmap btm;
        Color pixel;

        public Form2(Bitmap obr)
        {
            InitializeComponent();
            this.btm = obr;
            pictureBox1.Image = btm;
        }

        private void rightToolStripMenuItem_Click(object sender, EventArgs e)
        {
            btm.RotateFlip(RotateFlipType.Rotate270FlipNone);
            pictureBox1.Image = btm;
        }

        private void upwardsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            btm.RotateFlip(RotateFlipType.Rotate180FlipNone);
            pictureBox1.Image = btm;
        }

        private void leftToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            btm.RotateFlip(RotateFlipType.Rotate90FlipNone);
            pictureBox1.Image = btm;
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }
    }
}
