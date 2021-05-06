namespace BitmapEditor
{
    partial class Form1
    {
        /// <summary>
        /// Vyžaduje se proměnná návrháře.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Uvolněte všechny používané prostředky.
        /// </summary>
        /// <param name="disposing">hodnota true, když by se měl spravovaný prostředek odstranit; jinak false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Kód generovaný Návrhářem Windows Form

        /// <summary>
        /// Metoda vyžadovaná pro podporu Návrháře - neupravovat
        /// obsah této metody v editoru kódu.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.souborToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.otevřítToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.saveToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.nástrojeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.rotateToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.blackAndWhiteToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.sillueteToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.scaleToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.degresesOfGrayToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.cropToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.justCropToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.cropAndScaleToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.colorReplacmentToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.transparencyToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.colorColorationToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.specialToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.pictureToASCIIToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.hScrollBar1 = new System.Windows.Forms.HScrollBar();
            this.label1 = new System.Windows.Forms.Label();
            this.saveFileDialog1 = new System.Windows.Forms.SaveFileDialog();
            this.menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.souborToolStripMenuItem,
            this.nástrojeToolStripMenuItem,
            this.specialToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(784, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // souborToolStripMenuItem
            // 
            this.souborToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.otevřítToolStripMenuItem,
            this.saveToolStripMenuItem});
            this.souborToolStripMenuItem.Name = "souborToolStripMenuItem";
            this.souborToolStripMenuItem.Size = new System.Drawing.Size(37, 20);
            this.souborToolStripMenuItem.Text = "File";
            // 
            // otevřítToolStripMenuItem
            // 
            this.otevřítToolStripMenuItem.Name = "otevřítToolStripMenuItem";
            this.otevřítToolStripMenuItem.Size = new System.Drawing.Size(103, 22);
            this.otevřítToolStripMenuItem.Text = "Open";
            this.otevřítToolStripMenuItem.Click += new System.EventHandler(this.otevřítToolStripMenuItem_Click);
            // 
            // saveToolStripMenuItem
            // 
            this.saveToolStripMenuItem.Name = "saveToolStripMenuItem";
            this.saveToolStripMenuItem.Size = new System.Drawing.Size(103, 22);
            this.saveToolStripMenuItem.Text = "Save";
            this.saveToolStripMenuItem.Click += new System.EventHandler(this.saveToolStripMenuItem_Click);
            // 
            // nástrojeToolStripMenuItem
            // 
            this.nástrojeToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.rotateToolStripMenuItem,
            this.blackAndWhiteToolStripMenuItem,
            this.sillueteToolStripMenuItem,
            this.scaleToolStripMenuItem,
            this.degresesOfGrayToolStripMenuItem,
            this.cropToolStripMenuItem,
            this.colorReplacmentToolStripMenuItem,
            this.transparencyToolStripMenuItem,
            this.colorColorationToolStripMenuItem});
            this.nástrojeToolStripMenuItem.Name = "nástrojeToolStripMenuItem";
            this.nástrojeToolStripMenuItem.Size = new System.Drawing.Size(47, 20);
            this.nástrojeToolStripMenuItem.Text = "Tools";
            // 
            // rotateToolStripMenuItem
            // 
            this.rotateToolStripMenuItem.Name = "rotateToolStripMenuItem";
            this.rotateToolStripMenuItem.Size = new System.Drawing.Size(166, 22);
            this.rotateToolStripMenuItem.Text = "Rotate";
            this.rotateToolStripMenuItem.Click += new System.EventHandler(this.rotateToolStripMenuItem_Click);
            // 
            // blackAndWhiteToolStripMenuItem
            // 
            this.blackAndWhiteToolStripMenuItem.Name = "blackAndWhiteToolStripMenuItem";
            this.blackAndWhiteToolStripMenuItem.Size = new System.Drawing.Size(166, 22);
            this.blackAndWhiteToolStripMenuItem.Text = "Black and White";
            this.blackAndWhiteToolStripMenuItem.Click += new System.EventHandler(this.blackAndWhiteToolStripMenuItem_Click);
            // 
            // sillueteToolStripMenuItem
            // 
            this.sillueteToolStripMenuItem.Name = "sillueteToolStripMenuItem";
            this.sillueteToolStripMenuItem.Size = new System.Drawing.Size(166, 22);
            this.sillueteToolStripMenuItem.Text = "Silluete";
            this.sillueteToolStripMenuItem.Click += new System.EventHandler(this.sillueteToolStripMenuItem_Click);
            // 
            // scaleToolStripMenuItem
            // 
            this.scaleToolStripMenuItem.Name = "scaleToolStripMenuItem";
            this.scaleToolStripMenuItem.Size = new System.Drawing.Size(166, 22);
            this.scaleToolStripMenuItem.Text = "Scale";
            this.scaleToolStripMenuItem.Click += new System.EventHandler(this.scaleToolStripMenuItem_Click);
            // 
            // degresesOfGrayToolStripMenuItem
            // 
            this.degresesOfGrayToolStripMenuItem.Name = "degresesOfGrayToolStripMenuItem";
            this.degresesOfGrayToolStripMenuItem.Size = new System.Drawing.Size(166, 22);
            this.degresesOfGrayToolStripMenuItem.Text = "Degreses of Gray";
            this.degresesOfGrayToolStripMenuItem.Click += new System.EventHandler(this.degresesOfGrayToolStripMenuItem_Click);
            // 
            // cropToolStripMenuItem
            // 
            this.cropToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.justCropToolStripMenuItem,
            this.cropAndScaleToolStripMenuItem});
            this.cropToolStripMenuItem.Name = "cropToolStripMenuItem";
            this.cropToolStripMenuItem.Size = new System.Drawing.Size(166, 22);
            this.cropToolStripMenuItem.Text = "Crop";
            // 
            // justCropToolStripMenuItem
            // 
            this.justCropToolStripMenuItem.Name = "justCropToolStripMenuItem";
            this.justCropToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.justCropToolStripMenuItem.Text = "Just crop";
            this.justCropToolStripMenuItem.Click += new System.EventHandler(this.justCropToolStripMenuItem_Click);
            // 
            // cropAndScaleToolStripMenuItem
            // 
            this.cropAndScaleToolStripMenuItem.Name = "cropAndScaleToolStripMenuItem";
            this.cropAndScaleToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.cropAndScaleToolStripMenuItem.Text = "Crop and scale";
            this.cropAndScaleToolStripMenuItem.Click += new System.EventHandler(this.cropAndScaleToolStripMenuItem_Click);
            // 
            // colorReplacmentToolStripMenuItem
            // 
            this.colorReplacmentToolStripMenuItem.Name = "colorReplacmentToolStripMenuItem";
            this.colorReplacmentToolStripMenuItem.Size = new System.Drawing.Size(166, 22);
            this.colorReplacmentToolStripMenuItem.Text = "Color replacment";
            this.colorReplacmentToolStripMenuItem.Click += new System.EventHandler(this.colorReplacmentToolStripMenuItem_Click);
            // 
            // transparencyToolStripMenuItem
            // 
            this.transparencyToolStripMenuItem.Name = "transparencyToolStripMenuItem";
            this.transparencyToolStripMenuItem.Size = new System.Drawing.Size(166, 22);
            this.transparencyToolStripMenuItem.Text = "Transparency";
            this.transparencyToolStripMenuItem.Click += new System.EventHandler(this.transparencyToolStripMenuItem_Click);
            // 
            // colorColorationToolStripMenuItem
            // 
            this.colorColorationToolStripMenuItem.Name = "colorColorationToolStripMenuItem";
            this.colorColorationToolStripMenuItem.Size = new System.Drawing.Size(166, 22);
            this.colorColorationToolStripMenuItem.Text = "Color coloration";
            this.colorColorationToolStripMenuItem.Click += new System.EventHandler(this.colorColorationToolStripMenuItem_Click);
            // 
            // specialToolStripMenuItem
            // 
            this.specialToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.pictureToASCIIToolStripMenuItem});
            this.specialToolStripMenuItem.Name = "specialToolStripMenuItem";
            this.specialToolStripMenuItem.Size = new System.Drawing.Size(56, 20);
            this.specialToolStripMenuItem.Text = "Special";
            // 
            // pictureToASCIIToolStripMenuItem
            // 
            this.pictureToASCIIToolStripMenuItem.Name = "pictureToASCIIToolStripMenuItem";
            this.pictureToASCIIToolStripMenuItem.Size = new System.Drawing.Size(156, 22);
            this.pictureToASCIIToolStripMenuItem.Text = "Picture to ASCII";
            this.pictureToASCIIToolStripMenuItem.Click += new System.EventHandler(this.pictureToASCIIToolStripMenuItem_Click);
            // 
            // openFileDialog1
            // 
            this.openFileDialog1.FileName = "openFileDialog1";
            // 
            // pictureBox1
            // 
            this.pictureBox1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left)));
            this.pictureBox1.BackColor = System.Drawing.Color.White;
            this.pictureBox1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pictureBox1.Location = new System.Drawing.Point(10, 28);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(300, 484);
            this.pictureBox1.TabIndex = 1;
            this.pictureBox1.TabStop = false;
            this.pictureBox1.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pictureBox1_MouseDown);
            this.pictureBox1.MouseMove += new System.Windows.Forms.MouseEventHandler(this.pictureBox1_MouseMove);
            this.pictureBox1.MouseUp += new System.Windows.Forms.MouseEventHandler(this.pictureBox1_MouseUp);
            // 
            // pictureBox2
            // 
            this.pictureBox2.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.pictureBox2.BackColor = System.Drawing.Color.White;
            this.pictureBox2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pictureBox2.Location = new System.Drawing.Point(409, 27);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(323, 405);
            this.pictureBox2.TabIndex = 2;
            this.pictureBox2.TabStop = false;
            // 
            // hScrollBar1
            // 
            this.hScrollBar1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.hScrollBar1.LargeChange = 5;
            this.hScrollBar1.Location = new System.Drawing.Point(449, 519);
            this.hScrollBar1.Maximum = 259;
            this.hScrollBar1.Name = "hScrollBar1";
            this.hScrollBar1.Size = new System.Drawing.Size(323, 21);
            this.hScrollBar1.TabIndex = 0;
            this.hScrollBar1.Value = 147;
            this.hScrollBar1.ValueChanged += new System.EventHandler(this.hScrollBar1_ValueChanged);
            // 
            // label1
            // 
            this.label1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(606, 540);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(25, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "147";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 561);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.hScrollBar1);
            this.Controls.Add(this.pictureBox2);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.MinimumSize = new System.Drawing.Size(400, 300);
            this.Name = "Form1";
            this.ShowIcon = false;
            this.Text = "Bitmap Editro V3";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.SizeChanged += new System.EventHandler(this.Form1_SizeChanged);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem souborToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem otevřítToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem nástrojeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem blackAndWhiteToolStripMenuItem;
        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.PictureBox pictureBox2;
        private System.Windows.Forms.HScrollBar hScrollBar1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ToolStripMenuItem sillueteToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem rotateToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem scaleToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem degresesOfGrayToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem cropToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem justCropToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem cropAndScaleToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem specialToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem pictureToASCIIToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem colorReplacmentToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem transparencyToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem colorColorationToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem saveToolStripMenuItem;
        private System.Windows.Forms.SaveFileDialog saveFileDialog1;
    }
}

