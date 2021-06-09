/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */




/*******************************************************************************
 * Třída {@code Pohyb} je hlavní třídou projektu,
 * který ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Pohyb
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        Trojúhelník tr1 = new Trojúhelník();
       
        Elipsa kr1 = new Elipsa(100,50,50,50);
        for (int i = kr1.getY(); i<250; i++)
        {
          kr1.setPozice(kr1.getX(),i);
          IO.čekej(10);
        }
    }
}
