/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */




/*******************************************************************************
 * Třída {@code Test} je hlavní třídou projektu,
 * který ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Test
{

    public static void main(String[] args)
    {
        Student st1 = new Student("Josef","Vomáčka",3,"IT",true);
        Prepravka messenger = st1.getStudent();
        vypisStudenta(messenger);
    }
    
    public static void vypisStudenta(Prepravka messenger)
    {    
        System.out.println("Jméno studenta: " + messenger.jmeno);
        System.out.println("Přijmení studenta: " + messenger.prijmeni);
        System.out.println("Ročník studenta: " + messenger.rocnik);
        System.out.println("Obor studenta: " + messenger.obor);
        System.out.println("Je student na intru? " + messenger.intr);
    }
}
