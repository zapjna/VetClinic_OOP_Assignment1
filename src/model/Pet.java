package model;

public class Pet {
    private int petId;
    private String name;
    private String species;
    private int age;
    private String ownerName;

    public Pet(String name, String species, int age, String ownerName) {
        setName(name);
        setSpecies(species);
        setAge(age);
        setOwnerName(ownerName);
    }


    public Pet(int petId, String name, String species, int age, String ownerName) {
        setPetId(petId);
        setName(name);
        setSpecies(species);
        setAge(age);
        setOwnerName(ownerName);
    }

    public int getPetId() { return petId; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public String getOwnerName() { return ownerName; }

    public void setPetId(int petId) {
        if (petId <= 0) throw new IllegalArgumentException("Pet ID must be positive.");
        this.petId = petId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Pet name cannot be empty.");
        this.name = name.trim();
    }

    public void setSpecies(String species) {
        if (species == null || species.trim().isEmpty())
            throw new IllegalArgumentException("Species cannot be empty.");
        this.species = species.trim();
    }

    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative.");
        this.age = age;
    }

    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty())
            throw new IllegalArgumentException("Owner name cannot be empty.");
        this.ownerName = ownerName.trim();
    }

    public boolean isYoung() { return age < 2; }

    public String getLifeStage() {
        if (age < 2) return "Young";
        else if (age <= 7) return "Adult";
        else return "Senior";
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}