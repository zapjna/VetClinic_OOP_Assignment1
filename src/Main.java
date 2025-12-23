public class Main {
    public static void main(String[] args) {
        Pet pet1 = new Pet("Buddy", "Dog", 3, "Healthy");
        Pet pet2 = new Pet();

        Owner owner1 = new Owner("Alice", "123456", "123 Street", 2);
        Veterinarian vet1 = new Veterinarian("Dr. Smith", "Surgery", 7, true);

        pet1.printInfo();
        pet2.printInfo();
        owner1.printInfo();
        vet1.printInfo();

        System.out.println("Is pet sick? " + pet1.isSick());
        System.out.println("Does owner have multiple pets? " + owner1.hasMultiplePets());
        System.out.println("Is veterinarian experienced? " + vet1.isExperienced());
    }
}
