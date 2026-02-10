package model;

public abstract class ClinicPerson {
    protected int personId;
    protected String name;
    protected String personType; // "OWNER" or "VETERINARIAN"

    public ClinicPerson(int personId, String name, String personType) {
        this.personId = personId;
        this.name = name;
        this.personType = personType;
    }

    public int getPersonId() { return personId; }
    public String getName() { return name; }
    public String getPersonType() { return personType; }

    public abstract String getRoleInfo();

    @Override
    public String toString() {
        return "ClinicPerson{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", personType='" + personType + '\'' +
                '}';
    }
}