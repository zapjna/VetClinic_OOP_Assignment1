package model;

public class Owner extends ClinicPerson {
    private String phone;
    private int numberOfPets;

    public Owner() {
        super();
        this.phone = "Not provided";
        this.numberOfPets = 0;
    }

    public Owner(int ownerId, String name, String phone, int numberOfPets) {
        super(ownerId, name);
        setPhone(phone);
        setNumberOfPets(numberOfPets);
    }

    public String getPhone() { return phone; }
    public int getNumberOfPets() { return numberOfPets; }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty())
            throw new IllegalArgumentException("Phone cannot be empty.");
        this.phone = phone.trim();
    }

    public void setNumberOfPets(int numberOfPets) {
        if (numberOfPets < 0)
            throw new IllegalArgumentException("Number of pets cannot be negative.");
        this.numberOfPets = numberOfPets;
    }

    public void addPet() {
        numberOfPets++;
    }

    public boolean isFrequentClient() {
        return numberOfPets >= 3;
    }

    @Override
    public String getRoleInfo() {
        return "Owner{id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", numberOfPets=" + numberOfPets +
                '}';
    }

    @Override
    public String toString() {
        return getRoleInfo();
    }
}