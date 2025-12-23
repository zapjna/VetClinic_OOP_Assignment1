public class Pet {
    private String name;
    private String species;
    private int age;
    private String healthStatus;

    public Pet() {
        this.name = "Unknown";
        this.species = "Unknown";
        this.age = 0;
        this.healthStatus = "Healthy";
    }

    public Pet(String name, String species, int age, String healthStatus) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.healthStatus = healthStatus;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public int getAge() { return age; }
    public void setAge(int age) { if(age>=0) this.age = age; }
    public String getHealthStatus() { return healthStatus; }
    public void setHealthStatus(String healthStatus) { this.healthStatus = healthStatus; }

    public void printInfo() { System.out.println(toString()); }
    public boolean isSick() { return !healthStatus.equalsIgnoreCase("Healthy"); }

    @Override
    public String toString() {
        return "Pet{name='" + name + "', species='" + species + "', age=" + age + ", healthStatus='" + healthStatus + "'}";
    }
}
