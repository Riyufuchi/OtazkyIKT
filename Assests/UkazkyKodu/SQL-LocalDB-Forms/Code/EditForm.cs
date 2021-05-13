using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SQLForm
{
    public partial class EditForm : Form
    {
        private string question = "";
        public string[] data = new string[2];

        public EditForm(int ID, string name, int type)
        {
            InitializeComponent();
            setUpButtons(type);
            data[0] = textBox1.Text = Convert.ToString(ID);
            textBox1.Enabled = false;
            data[1] = textBox2.Text = name;
            if (type == 1)
            {
                textBox2.Enabled = false;
            }
        }

        #region Events
        #region Edit
        private void ResetButton_Click(object sender, EventArgs e)
        {
            textBox2.Text = data[1];
        }

        private void EditButton_Click(object sender, EventArgs e)
        {
            data[1] = textBox2.Text;
            question = "UPDATE artists SET Name = '" + data[1] + "' WHERE artistId = " + data[0] + ";";
            EditForm.ActiveForm.Close();
        }
        #endregion
        #region Delete
        private void AbortButton_Click(object sender, EventArgs e)
        {
            EditForm.ActiveForm.Close();
        }

        private void DelButton_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Are you sure?", "Delete", MessageBoxButtons.OKCancel, MessageBoxIcon.Question) == System.Windows.Forms.DialogResult.OK)
            {
                question = "DELETE FROM artists WHERE artistId = '" + data[0] + "'";
                EditForm.ActiveForm.Close();
            }
        }
        #endregion
        #region Add
        private void AddButton_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Are you sure?", "Add", MessageBoxButtons.OKCancel, MessageBoxIcon.Question) == System.Windows.Forms.DialogResult.OK)
            {
                if (textBox2.Text != "")
                {
                    data[1] = textBox2.Text;
                    question = "INSERT INTO artists (name) VALUES ('" + data[1] + "')";
                    EditForm.ActiveForm.Close();
                }
                else
                {
                    MessageBox.Show("Name colon must contain text.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }

        private void CancelButton_Click(object sender, EventArgs e)
        {
            EditForm.ActiveForm.Close();
        }
        #endregion
        #endregion

        #region Methods
        private void setUpButtons(int type)
        {
            if (type == 1)
            {
                this.Text = "Delete";
                Button tlacitko = new Button();
                tlacitko.Width = 75;
                tlacitko.Height = 37;
                tlacitko.Location = new Point(103, 61);
                tlacitko.Name = "DelButton";
                tlacitko.Show();
                tlacitko.Parent = this;
                tlacitko.Text = "Delete";
                tlacitko.Click += new EventHandler(DelButton_Click);
                tlacitko.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) | System.Windows.Forms.AnchorStyles.Right)));

                Button tlacitko2 = new Button();
                tlacitko2.Width = 75;
                tlacitko2.Height = 37;
                tlacitko2.Location = new Point(15, 61);
                tlacitko2.Name = "AbortButton";
                tlacitko2.Show();
                tlacitko2.Parent = this;
                tlacitko2.Text = "Cancel";
                tlacitko2.Click += new EventHandler(AbortButton_Click);
            }

            if (type == 2)
            {
                Button tlacitko = new Button();
                tlacitko.Width = 75;
                tlacitko.Height = 37;
                tlacitko.Location = new Point(103, 61);
                tlacitko.Name = "EditButton";
                tlacitko.Show();
                tlacitko.Parent = this;
                tlacitko.Text = "Edit";
                tlacitko.Click += new EventHandler(EditButton_Click);
                tlacitko.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) | System.Windows.Forms.AnchorStyles.Right)));

                Button tlacitko2 = new Button();
                tlacitko2.Width = 75;
                tlacitko2.Height = 37;
                tlacitko2.Location = new Point(15, 61);
                tlacitko2.Name = "ResetButton";
                tlacitko2.Show();
                tlacitko2.Parent = this;
                tlacitko2.Text = "Reset";
                tlacitko2.Click += new EventHandler(ResetButton_Click);
            }

            if (type == 3)
            {
                this.Text = "Add";
                Button tlacitko = new Button();
                tlacitko.Width = 75;
                tlacitko.Height = 37;
                tlacitko.Location = new Point(103, 61);
                tlacitko.Name = "AddButton";
                tlacitko.Show();
                tlacitko.Parent = this;
                tlacitko.Text = "Add";
                tlacitko.Click += new EventHandler(AddButton_Click);
                tlacitko.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) | System.Windows.Forms.AnchorStyles.Right)));

                Button tlacitko2 = new Button();
                tlacitko2.Width = 75;
                tlacitko2.Height = 37;
                tlacitko2.Location = new Point(15, 61);
                tlacitko2.Name = "CloseButton";
                tlacitko2.Show();
                tlacitko2.Parent = this;
                tlacitko2.Text = "Cancel";
                tlacitko2.Click += new EventHandler(CancelButton_Click);

            }
        }
        #endregion 

        public string getQuestion
        {
            get
            {
                return this.question;
            }
        }
    }
}

