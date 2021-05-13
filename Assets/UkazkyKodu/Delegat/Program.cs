using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UkolOM
{
    class Program
    {

        static void Main(string[] args)
        {
            Auto Tesla = new Auto();
            Tesla.Troubeni += new Delegat(Trub);

            Console.WriteLine("Barva: " + Tesla.getBarva());

            Console.WriteLine("Počet dveří: " + Tesla.getPocetDveri());
            Tesla.setPocetDeri(4);
            Console.WriteLine("Počet dveří: " + Tesla.getPocetDveri());

            Console.WriteLine("Svití světla: " + Tesla.SvitiSvetla);
            Tesla.SvitiSvetla = true;
            Console.WriteLine("Svití světla: " + Tesla.SvitiSvetla);

            Console.WriteLine(" ");
            Console.WriteLine("Stiskni Enter pro zatroubeni");
            ConsoleKeyInfo CKI;
            CKI = Console.ReadKey();
            switch(CKI.Key)
            {
                case ConsoleKey.Enter: Tesla.Zatroubeni(true); break;
                default: Console.Clear(); break;
            }

            Console.ReadKey(true);
        }

        static void Trub()
        {
            Console.WriteLine("Troubeni");
            Console.Beep(320, 1000);
        }

    }
}
