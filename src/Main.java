import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Pet> pets = new ArrayList<>();

        // Sample objects
        people.add(new Owner("Alice", 30, "123456", "123 Street", 2));
        people.add(new Veterinarian("Dr. Smith", 40, "Surgery"));

        pets.add(new Pet("Buddy", "Dog", 3, "Healthy"));
        pets.add(new Pet("Kitty", "Cat", 2, "Sick"));

        // Simple Menu
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View all people");
            System.out.println("2. View all pets");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("=== People ===");
                    for (Person p : people) {
                        System.out.println(p);
                        p.work(); // Polymorphism
                    }
                    break;
                case 2:
                    System.out.println("=== Pets ===");
                    for (Pet pet : pets) {
                        System.out.println(pet + ", Is sick? " + pet.isSick());
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}
