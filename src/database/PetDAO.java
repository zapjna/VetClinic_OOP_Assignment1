package database;

import exception.InvalidInputException;
import model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {


    public void insertPet(Pet pet) {
        String sql = """
                INSERT INTO public.pet (name, species, age, owner_name)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getSpecies());
            stmt.setInt(3, pet.getAge());
            stmt.setString(4, pet.getOwnerName());

            stmt.executeUpdate();
            System.out.println("Pet inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getAllPets() {
        String sql = "SELECT * FROM public.pet";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("pet_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("species") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("owner_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Pet getPetById(int petId) {
        String sql = "SELECT * FROM public.pet WHERE pet_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, petId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pet(
                        rs.getInt("pet_id"),
                        rs.getString("name"),
                        rs.getString("species"),
                        rs.getInt("age"),
                        rs.getString("owner_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean updatePet(Pet pet) {
        String sql = """
                UPDATE public.pet
                SET name = ?, species = ?, age = ?, owner_name = ?
                WHERE pet_id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getSpecies());
            stmt.setInt(3, pet.getAge());
            stmt.setString(4, pet.getOwnerName());
            stmt.setInt(5, pet.getPetId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deletePet(int petId) {
        String sql = "DELETE FROM public.pet WHERE pet_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, petId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pet> searchPetsByName(String name) {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM public.pet WHERE name ILIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pets.add(new Pet(
                        rs.getInt("pet_id"),
                        rs.getString("name"),
                        rs.getString("species"),
                        rs.getInt("age"),
                        rs.getString("owner_name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }


    public List<Pet> searchPetsByAgeRange(int minAge, int maxAge) {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM public.pet WHERE age BETWEEN ? AND ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, minAge);
            stmt.setInt(2, maxAge);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pets.add(new Pet(
                        rs.getInt("pet_id"),
                        rs.getString("name"),
                        rs.getString("species"),
                        rs.getInt("age"),
                        rs.getString("owner_name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }


    public List<Pet> searchPetsByMinAge(int age) {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM public.pet WHERE age >= ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, age);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pets.add(new Pet(
                        rs.getInt("pet_id"),
                        rs.getString("name"),
                        rs.getString("species"),
                        rs.getInt("age"),
                        rs.getString("owner_name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }
}