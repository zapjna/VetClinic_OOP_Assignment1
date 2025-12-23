public class Veterinarian {
    private String name;
    private String specialization;
    private int experienceYears;
    private boolean isAvailable;

    public Veterinarian() {
        this.name = "Unknown";
        this.specialization = "General";
        this.experienceYears = 0;
        this.isAvailable = true;
    }

    public Veterinarian(String name, String specialization, int experienceYears, boolean isAvailable) {
        this.name = name;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.isAvailable = isAvailable;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
    public boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

    public void printInfo() { System.out.println(toString()); }
    public boolean isExperienced() { return experienceYears >= 5; }

    @Override
    public String toString() {
        return "Veterinarian{name='" + name + "', specialization='" + specialization + "', experienceYears=" + experienceYears + ", isAvailable=" + isAvailable + "}";
    }
}
