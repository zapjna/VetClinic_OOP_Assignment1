package model;

public abstract class ClinicPerson {
    protected int id;
    protected String name;

    public ClinicPerson() {
        this.id = 0;
        this.name = "Unknown";
    }

    public ClinicPerson(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive.");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name.trim();
    }


    public abstract String getRoleInfo();

    @Override
    public String toString() {
        return getRoleInfo();
    }
}