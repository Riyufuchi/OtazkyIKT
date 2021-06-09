/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */




/*******************************************************************************
 * Instance třídy {@code Student} představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Student
{
    private  String jmeno;
    private  String prijmeni;
    private  int rocnik;
    private  String obor;
    private  boolean intr;
   
    public Student()
    {
    }
    public Student(String jmeno, String prijmeni, int rocnik, String obor, boolean intr)
    {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        if(rocnik >4)
            {this.rocnik = 4;}
        else
            {this.rocnik=rocnik;}
        this.obor = obor;
        if(intr == true || intr == false)
        {this.intr = intr;}
        else
        {this.intr = false;}
    }
    /*public Prepravka getStudent()
    {
      return new Prepravka(this);
    }*/
    
    public Prepravka getStudent()
    {
      return new Prepravka(jmeno,prijmeni,rocnik,obor,intr);
    }

}
