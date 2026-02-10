package model;

public class Owner extends ClinicPerson {
    private String phone;
    private int numberOfPets;

    public Owner(int personId, String name, String phone, int numberOfPets) {
        super(personId, name, "OWNER");
        this.phone = phone;
        this.numberOfPets = numberOfPets;
    }

    public String getPhone() { return phone; }
    public int getNumberOfPets() { return numberOfPets; }

    @Override
    public String getRoleInfo() {
        return "Owner: " + name + " (pets=" + numberOfPets + ", phone=" + phone + ")";
    }

    @Override
    public String toString() {
        return "Owner{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", numberOfPets=" + numberOfPets +
                '}';
    }
}