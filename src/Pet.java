public class Pet {
    private String name;
    private String species;
    private int age;
    private String healthStatus;

    public Pet(String name, String species, int age, String healthStatus) {
        setName(name);
        setSpecies(species);
        setAge(age);
        setHealthStatus(healthStatus);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = (name == null || name.isEmpty()) ? "Unknown" : name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = (species == null || species.isEmpty()) ? "Unknown" : species; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = (age < 0) ? 0 : age; }

    public String getHealthStatus() { return healthStatus; }
    public void setHealthStatus(String healthStatus) { this.healthStatus = (healthStatus == null || healthStatus.isEmpty()) ? "Healthy" : healthStatus; }

    public boolean isSick() { return healthStatus.equalsIgnoreCase("Sick"); }

    @Override
    public String toString() {
        return "Pet{name='" + name + "', species='" + species + "', age=" + age + ", healthStatus='" + healthStatus + "'}";
    }
}
