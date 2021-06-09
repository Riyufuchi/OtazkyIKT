/**
 * Trida reprezentujici sekretarku
 * @author Pavel Micka
 */
public class Skladnik extends Employee{

    public Skladnik(String name, int age) {
        super(name, age);
    }

    /**
     * Zde je implementace prace zamestnance (v tomto pripade skladnika)
     */
    @Override
    public void work() {
        System.out.println("Usilovne pracuji, tahám bedny a plním kamiony.");
    }

}

