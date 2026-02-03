package model;

import interfaces.Treatable;

public class Veterinarian extends ClinicPerson implements Treatable {
    private String specialization;
    private int experience;

    public Veterinarian() {
        super();
        this.specialization = "General";
        this.experience = 0;
    }

    public Veterinarian(int vetId, String name, String specialization, int experience) {
        super(vetId, name);
        setSpecialization(specialization);
        setExperience(experience);
    }

    public String getSpecialization() { return specialization; }
    public int getExperience() { return experience; }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty())
            throw new IllegalArgumentException("Specialization cannot be empty.");
        this.specialization = specialization.trim();
    }

    public void setExperience(int experience) {
        if (experience < 0)
            throw new IllegalArgumentException("Experience cannot be negative.");
        this.experience = experience;
    }

    @Override
    public boolean canTreat(Pet pet) {
        return specialization.equalsIgnoreCase(pet.getSpecies());
    }

    public boolean isExperienced() {
        return experience >= 5;
    }

    @Override
    public String getRoleInfo() {
        return "Veterinarian{id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experience=" + experience +
                '}';
    }

    @Override
    public String toString() {
        return getRoleInfo();
    }
}