package menu;

import database.PetDAO;
import model.Pet;

import java.util.List;
import java.util.Scanner;

public class MenuManager {

    private final Scanner scanner = new Scanner(System.in);
    private final PetDAO petDAO = new PetDAO();

    public void displayMenu() {
        System.out.println("""
                ===== VET CLINIC MENU =====
                1. Add Pet
                2. View All Pets
                3. Update Pet
                4. Delete Pet
                5. Search Pet by Name
                6. Search Pets by Age Range
                7. Search Pets with Age >= X
                8. Add Owner
                9. View All Owners
                10. Add Veterinarian
                11. View All Veterinarians
                0. Exit
                """);
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addPet();
                case 2 -> petDAO.getAllPets();
                case 3 -> updatePet();
                case 4 -> deletePet();
                case 5 -> searchByName();
                case 6 -> searchByAgeRange();
                case 7 -> searchByMinAge();
                case 8 -> addOwner();
                case 9 -> viewAllOwners();
                case 10 -> addVeterinarian();
                case 11 -> viewAllVeterinarians();
                case 0 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }


    // ===== MENU ACTIONS =====

    private void addPet() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Species: ");
        String species = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Owner name: ");
        String owner = scanner.nextLine();

        Pet pet = new Pet(name, species, age, owner);
        petDAO.insertPet(pet);
    }

    private void updatePet() {
        System.out.print("Enter pet ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New name: ");
        String name = scanner.nextLine();

        System.out.print("New species: ");
        String species = scanner.nextLine();

        System.out.print("New age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("New owner name: ");
        String owner = scanner.nextLine();

        Pet pet = new Pet(id, name, species, age, owner);
        petDAO.updatePet(pet);
    }

    private void deletePet() {
        System.out.print("Enter pet ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            petDAO.deletePet(id);
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private void searchByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        List<Pet> pets = petDAO.searchPetsByName(name);
        pets.forEach(System.out::println);
    }

    private void searchByAgeRange() {
        System.out.print("Min age: ");
        int min = Integer.parseInt(scanner.nextLine());

        System.out.print("Max age: ");
        int max = Integer.parseInt(scanner.nextLine());

        List<Pet> pets = petDAO.searchPetsByAgeRange(min, max);
        pets.forEach(System.out::println);
    }

    private void searchByMinAge() {
        System.out.print("Enter minimum age: ");
        int age = Integer.parseInt(scanner.nextLine());

        List<Pet> pets = petDAO.searchPetsByMinAge(age);
        pets.forEach(System.out::println);
    }

    private void addOwner() {
        System.out.println("Add Owner (Week 8 placeholder)");
    }

    private void viewAllOwners() {
        System.out.println("View All Owners (Week 8 placeholder)");
    }

    private void addVeterinarian() {
        System.out.println("Add Veterinarian (Week 8 placeholder)");
    }

    private void viewAllVeterinarians() {
        System.out.println("View All Veterinarians (Week 8 placeholder)");
    }

}