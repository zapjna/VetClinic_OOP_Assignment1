public class Veterinarian extends Person {
    private String specialization;
    private boolean isAvailable;

    public Veterinarian(String name, int age, String specialization) {
        super(name, age);
        this.specialization = specialization;
        this.isAvailable = true;
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String getRole() { return "Veterinarian"; }

    @Override
    public void work() {
        System.out.println(name + " is treating pets in " + specialization + ".");
    }

    @Override
    public String toString() {
        return "Veterinarian{name='" + name + "', age=" + age + ", specialization='" + specialization + "', isAvailable=" + isAvailable + "}";
    }
}
