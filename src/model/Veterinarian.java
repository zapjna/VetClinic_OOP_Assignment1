package model;

public class Veterinarian extends ClinicPerson {
    private String specialization;
    private int experience;

    public Veterinarian(int personId, String name, String specialization, int experience) {
        super(personId, name, "VETERINARIAN");
        this.specialization = specialization;
        this.experience = experience;
    }

    public String getSpecialization() { return specialization; }
    public int getExperience() { return experience; }

    @Override
    public String getRoleInfo() {
        return "Veterinarian: " + name + " (spec=" + specialization + ", exp=" + experience + " yrs)";
    }

    @Override
    public String toString() {
        return "Veterinarian{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experience=" + experience +
                '}';
    }
}