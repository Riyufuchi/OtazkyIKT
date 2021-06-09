/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */



public class Test
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args) {
        
    Employee em1 = new Secretary("Karolina Krasna", 18);
    Employee em2 = new Secretary("Petra Hezka", 20);
    Employee em3 = new Skladnik("Josef Silný",45);
    Employee[] array = {em1, em2, em3}; //vytvorime pole dvou zamestnancu
    
    //Objekt reditele
    Employee dir = new Director("Petr Podnikatel", 35, array);

    //Pole techto zamestnancu
    Employee[] array2 = {em1, em2, em3, dir};
    
    //for each cyklus pro pruchod pres vsechny prvky kolekce (pole)
    for (Employee employee : array2) {
        /*
         * Pomoci polymorfismu se zavola vzdy ta spravna metoda
         */
        employee.sayHello();
        employee.work();
        System.out.println(employee.getName()); //odradkujeme po kazdem zamestnance
    }
}
}
