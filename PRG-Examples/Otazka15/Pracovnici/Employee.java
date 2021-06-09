
public abstract class Employee {
    protected String name;
    protected int age;

    /**
     * Abstraktni tridy samozrejme maji konstruktor, aby sly zinicializovat
     * jejich fieldy
     * @param name jmeno zamestnance
     * @param age vek zamestnance
     */
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Necha zamestnance pracovat.
     * Tato metoda je abstraktni - nema telo, jeji implementace bude vynucena
     * v prvni neabstraktni tride, ktera oddedi tuto tridu.
     */
    public abstract void work();

    /**
     * Abstrakni tridy taktez mohou mit neabstraktni metody
     */
    public void sayHello(){
        System.out.println("Dobry den, jmenuji se " + name + " a je mi " + age + " let.");
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}

