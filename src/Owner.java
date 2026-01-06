public class Owner extends Person {
    private String phone;
    private String address;
    private int petsCount;

    public Owner(String name, int age, String phone, String address, int petsCount) {
        super(name, age);
        setPhone(phone);
        setAddress(address);
        setPetsCount(petsCount);
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getPetsCount() { return petsCount; }
    public void setPetsCount(int petsCount) {
        if (petsCount < 0) this.petsCount = 0;
        else this.petsCount = petsCount;
    }

    public boolean hasMultiplePets() { return petsCount > 1; }

    @Override
    public String getRole() { return "Owner"; }

    @Override
    public void work() {
        System.out.println(name + " is taking care of their pets.");
    }

    @Override
    public String toString() {
        return "Owner{name='" + name + "', age=" + age + ", phone='" + phone + "', address='" + address + "', petsCount=" + petsCount + "}";
    }
}
