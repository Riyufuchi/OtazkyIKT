/**
 * Trida reditele 
 * @author malejpavouk
 */
public class Director extends Employee {
    private Employee[] employees;

    public Director(String name, int age, Employee[] employees) {
        super(name, age); //volame konstruktor predka
        this.employees = employees;
    }

    @Override //rikame prekladaci, ze prekryvame metodu
    public void work() {
        System.out.println("Prave ridim tyto lidi: ");
        for (int i = 0; i < employees.length; i++) {
            if (i != 0) { //pred prvnim zamestnancem neni ve vypisu carka
                System.out.print(", ");
            }
            System.out.print(employees[i].getName());
        }
        System.out.println(""); //nakonec odradkujeme
    }

    /**
     * @return the employees
     */
    public Employee[] getEmployees() {
        return employees;
    }

    /**
     * @param employees the employees to set
     */
    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }
}
