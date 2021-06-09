/**
 * Trida reprezentujici sekretarku
 * @author Pavel Micka
 */
public class Secretary extends Employee{

    public Secretary(String name, int age) {
        super(name, age);
    }

    /**
     * Zde je implementace prace zamestnance (v tomto pripade sekretarky)
     */
    @Override
    public void work() {
        System.out.println("Usilovne pracuji, varim kafe, pripravuji materialy, pisu dopisy.");
    }

}

