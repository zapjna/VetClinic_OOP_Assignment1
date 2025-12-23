public class Owner {
    private String name;
    private String phone;
    private String address;
    private int petsCount;

    public Owner() {
        this.name = "Unknown";
        this.phone = "Unknown";
        this.address = "Unknown";
        this.petsCount = 0;
    }

    public Owner(String name, String phone, String address, int petsCount) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.petsCount = petsCount;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getPetsCount() { return petsCount; }
    public void setPetsCount(int petsCount) { this.petsCount = petsCount; }

    public void printInfo() { System.out.println(toString()); }
    public boolean hasMultiplePets() { return petsCount > 1; }

    @Override
    public String toString() {
        return "Owner{name='" + name + "', phone='" + phone + "', address='" + address + "', petsCount=" + petsCount + "}";
    }
}
