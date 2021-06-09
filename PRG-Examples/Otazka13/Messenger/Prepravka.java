/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */




/*******************************************************************************
 * Instance třídy {@code Přepravka} představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Prepravka
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    public final String jmeno;
    public final String prijmeni;
    public final int rocnik;
    public final String obor;
    public final boolean intr;
    //public final Student st1;
   
    public Prepravka(String jmeno,String prijmeni,int rocnik,String obor,boolean intr)
    {
       this.jmeno = jmeno;
       this.prijmeni = prijmeni;
       this.rocnik = rocnik;
       this.obor = obor;
       this.intr = intr;       
    }
    
    /*public Prepravka(Student st1)
    {
        this.st1 = st1;
    }*/


}
