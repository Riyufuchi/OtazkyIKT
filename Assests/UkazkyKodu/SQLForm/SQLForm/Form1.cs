using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SQLite;

namespace SQLForm
{
    public partial class Form1 : Form
    {
        private DataSet dsg = new DataSet();
        private SQLiteConnection con;
        private SQLiteCommand sql_cmd;
        private EditForm ed;

        public Form1()
        {
            InitializeComponent();
            connection("ID");
        }

        #region Procedures
        private void connection(string order)
        {
            SetConnection();
            con.Open();
            SQLiteDataAdapter sda = new SQLiteDataAdapter(con.CreateCommand());
            switch(order)
            {
                case "ID": sda.SelectCommand.CommandText = @"SELECT * from artists order by artistId"; break;
                case "Name": sda.SelectCommand.CommandText = @"SELECT * from artists order by name"; break;
                default: sda.SelectCommand.CommandText = @"SELECT * from artists order by artistId"; break;
            }
            DataSet ds = new DataSet();
            sda.Fill(ds, "artists");
            con.Close();
            dataGridView1.DataSource = ds.Tables[0];
            ds.Dispose();
        }

        private void SetConnection()
        {
            con = new SQLiteConnection("Data Source=chinook.db;Version=3;New=False;Compress=True;");
        }
        #endregion

        #region Events
        #region Buttons
        private void edityUserToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ed = new EditForm(Convert.ToInt32(dataGridView1.CurrentRow.Cells[0].Value), dataGridView1.Rows[dataGridView1.CurrentCell.RowIndex].Cells[1].Value.ToString(), 2);
            ed.Show();
        }

        private void deleteDataToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ed = new EditForm(Convert.ToInt32(dataGridView1.CurrentRow.Cells[0].Value), dataGridView1.Rows[dataGridView1.CurrentCell.RowIndex].Cells[1].Value.ToString(), 1);
            ed.Show();
        }

        private void newDataToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ed = new EditForm(Convert.ToInt32(dataGridView1.Rows[dataGridView1.RowCount - 1].Cells[0].Value) + 1, "", 3);
            ed.Show();
        }

        private void nameToolStripMenuItem_Click(object sender, EventArgs e)
        {
            connection("Name");
        }

        private void iDToolStripMenuItem_Click(object sender, EventArgs e)
        {
            connection("ID");
        }
        #endregion
        #region Form
        private void Form1_Activated(object sender, EventArgs e)
        {
            if (ed != null)
            {
                SetConnection();
                con.Open();
                sql_cmd = con.CreateCommand();

                sql_cmd.CommandText = ed.getQuestion;

                sql_cmd.ExecuteNonQuery();
                con.Close();
                connection("ID");
            }
        }
        #endregion
        #endregion
    }
}
