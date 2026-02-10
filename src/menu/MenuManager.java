package menu;

import database.ClinicPersonDAO;
import exception.InvalidInputException;
import model.ClinicPerson;
import model.Owner;
import model.Veterinarian;

import java.util.List;
import java.util.Scanner;

public class MenuManager {

    private final Scanner scanner = new Scanner(System.in);
    private final ClinicPersonDAO dao = new ClinicPersonDAO(); // DB only source of truth

    public void displayMenu() {
        System.out.println("""
                ===== VET CLINIC MENU (Week 8) =====
                1. Add Owner
                2. Add Veterinarian
                3. View All People (DB)
                4. View Owners (DB)
                5. View Veterinarians (DB)
                6. Update Owner (SAFE)
                7. Update Veterinarian (SAFE)
                8. Delete Person (SAFE)
                9. Search by Name (ILIKE %...%)
                10. Search Vets by Experience Range (BETWEEN)
                11. Search Vets by Min Experience (>= X)
                12. Polymorphism Demo (DB data)
                0. Exit
                """);
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();

            try {
                int choice = readInt("Enter choice: ");

                switch (choice) {
                    case 1 -> addOwner();
                    case 2 -> addVeterinarian();
                    case 3 -> viewAllPeople();
                    case 4 -> viewOwners();
                    case 5 -> viewVeterinarians();
                    case 6 -> updateOwnerSafe();
                    case 7 -> updateVeterinarianSafe();
                    case 8 -> deletePersonSafe();
                    case 9 -> searchByName();
                    case 10 -> searchByExperienceRange();
                    case 11 -> searchByMinExperience();
                    case 12 -> polymorphismDemoFromDB();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice!");
                }

            } catch (InvalidInputException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
        System.out.println("Program finished.");
    }

    // ---------- CREATE ----------
    private void addOwner() throws InvalidInputException {
        System.out.println("\n--- ADD OWNER ---");
        String name = readString("Name: ");
        String phone = readString("Phone: ");
        int pets = readInt("Number of pets: ");

        dao.insertOwner(name, phone, pets);
        System.out.println("✅ Owner inserted into DB.");
    }

    private void addVeterinarian() throws InvalidInputException {
        System.out.println("\n--- ADD VETERINARIAN ---");
        String name = readString("Name: ");
        String spec = readString("Specialization: ");
        int exp = readInt("Experience years: ");

        dao.insertVeterinarian(name, spec, exp);
        System.out.println("✅ Veterinarian inserted into DB.");
    }

    // ---------- READ ----------
    private void viewAllPeople() {
        System.out.println("\n--- ALL PEOPLE (DB) ---");
        List<ClinicPerson> people = dao.getAllPeople();
        if (people.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        people.forEach(System.out::println);
    }

    private void viewOwners() {
        System.out.println("\n--- OWNERS (DB) ---");
        List<Owner> owners = dao.getOwners();
        if (owners.isEmpty()) {
            System.out.println("No owners found.");
            return;
        }
        owners.forEach(System.out::println);
    } private void viewVeterinarians() {
        System.out.println("\n--- VETERINARIANS (DB) ---");
        List<Veterinarian> vets = dao.getVeterinarians();
        if (vets.isEmpty()) {
            System.out.println("No veterinarians found.");
            return;
        }
        vets.forEach(System.out::println);
    }

    // ---------- UPDATE (SAFE) ----------
    private void updateOwnerSafe() throws InvalidInputException {
        System.out.println("\n--- UPDATE OWNER (SAFE) ---");
        int id = readInt("Enter owner ID: ");

        Owner existing = dao.getOwnerById(id);
        if (existing == null) {
            System.out.println("No OWNER found with ID: " + id);
            return;
        }

        System.out.println("Current: " + existing);
        System.out.println("Press Enter to keep current value.");

        System.out.print("New name [" + existing.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = existing.getName();

        System.out.print("New phone [" + existing.getPhone() + "]: ");
        String phone = scanner.nextLine().trim();
        if (phone.isEmpty()) phone = existing.getPhone();

        System.out.print("New number of pets [" + existing.getNumberOfPets() + "]: ");
        String petsInput = scanner.nextLine().trim();
        int pets = petsInput.isEmpty() ? existing.getNumberOfPets() : Integer.parseInt(petsInput);

        boolean ok = dao.updateOwner(id, name, phone, pets);
        System.out.println(ok ? "✅ Owner updated." : "❌ Update failed.");
    }

    private void updateVeterinarianSafe() throws InvalidInputException {
        System.out.println("\n--- UPDATE VETERINARIAN (SAFE) ---");
        int id = readInt("Enter vet ID: ");

        Veterinarian existing = dao.getVeterinarianById(id);
        if (existing == null) {
            System.out.println("No VETERINARIAN found with ID: " + id);
            return;
        }

        System.out.println("Current: " + existing);
        System.out.println("Press Enter to keep current value.");

        System.out.print("New name [" + existing.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = existing.getName();

        System.out.print("New specialization [" + existing.getSpecialization() + "]: ");
        String spec = scanner.nextLine().trim();
        if (spec.isEmpty()) spec = existing.getSpecialization();

        System.out.print("New experience [" + existing.getExperience() + "]: ");
        String expInput = scanner.nextLine().trim();
        int exp = expInput.isEmpty() ? existing.getExperience() : Integer.parseInt(expInput);

        boolean ok = dao.updateVeterinarian(id, name, spec, exp);
        System.out.println(ok ? "✅ Veterinarian updated." : "❌ Update failed.");
    }

    // ---------- DELETE (SAFE) ----------
    private void deletePersonSafe() throws InvalidInputException {
        System.out.println("\n--- DELETE PERSON (SAFE) ---");
        int id = readInt("Enter person ID: ");

        ClinicPerson existing = dao.getPersonById(id);
        if (existing == null) {
            System.out.println("No person found with ID: " + id);
            return;
        }

        System.out.println("To delete: " + existing);
        String confirm = readString("Are you sure? (yes/no): ");
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Delete cancelled.");
            return;
        }

        boolean ok = dao.deletePerson(id);
        System.out.println(ok ? "✅ Deleted." : "❌ Delete failed.");
    }

    // ---------- SEARCH ----------
    private void searchByName() throws InvalidInputException {
        System.out.println("\n--- SEARCH BY NAME (ILIKE) ---");
        String keyword = readString("Keyword: ");

        List<ClinicPerson> results = dao.searchByName(keyword);
        if (results.isEmpty()) {
            System.out.println("No results.");
            return;
        }
        results.forEach(System.out::println);
    } private void searchByExperienceRange() throws InvalidInputException {
        System.out.println("\n--- SEARCH VETS BY EXPERIENCE RANGE (BETWEEN) ---");
        int min = readInt("Min exp: ");
        int max = readInt("Max exp: ");

        List<Veterinarian> results = dao.searchVetsByExperienceRange(min, max);
        if (results.isEmpty()) {
            System.out.println("No results.");
            return;
        }
        results.forEach(System.out::println);
    }

    private void searchByMinExperience() throws InvalidInputException {
        System.out.println("\n--- SEARCH VETS BY MIN EXPERIENCE (>= X) ---");
        int min = readInt("Min exp: ");

        List<Veterinarian> results = dao.searchVetsByMinExperience(min);
        if (results.isEmpty()) {
            System.out.println("No results.");
            return;
        }
        results.forEach(System.out::println);
    }

    // ---------- POLYMORPHISM FROM DB ----------
    private void polymorphismDemoFromDB() {
        System.out.println("\n--- POLYMORPHISM DEMO (DB DATA) ---");
        List<ClinicPerson> people = dao.getAllPeople();

        if (people.isEmpty()) {
            System.out.println("DB is empty. Add Owner/Vet first.");
            return;
        }

        System.out.println("Calling getRoleInfo() on ClinicPerson references:");
        for (ClinicPerson p : people) {
            System.out.println("• " + p.getRoleInfo());
        }

        System.out.println("✅ Same method name, different output => polymorphism.");
    }

    // ---------- INPUT HELPERS ----------
    private int readInt(String prompt) throws InvalidInputException {
        String s = readString(prompt);
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter a valid integer.");
        }
    }

    private String readString(String prompt) throws InvalidInputException {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException("Input cannot be empty.");
        }
        return input.trim();
    }
}