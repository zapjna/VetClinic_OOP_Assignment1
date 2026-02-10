package database;

import model.ClinicPerson;
import model.Owner;
import model.Veterinarian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicPersonDAO {

    // -------- CREATE --------
    public void insertOwner(String name, String phone, int numberOfPets) {
        String sql = """
                INSERT INTO public.clinic_person (name, person_type, phone, number_of_pets)
                VALUES (?, 'OWNER', ?, ?)
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setInt(3, numberOfPets);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertVeterinarian(String name, String specialization, int experience) {
        String sql = """
                INSERT INTO public.clinic_person (name, person_type, specialization, experience)
                VALUES (?, 'VETERINARIAN', ?, ?)
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, specialization);
            stmt.setInt(3, experience);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // -------- READ (POLYMORPHIC) --------
    public List<ClinicPerson> getAllPeople() {
        String sql = "SELECT * FROM public.clinic_person ORDER BY person_id";
        List<ClinicPerson> people = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                people.add(mapRowToPerson(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public List<Owner> getOwners() {
        String sql = "SELECT * FROM public.clinic_person WHERE person_type='OWNER' ORDER BY person_id";
        List<Owner> owners = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                owners.add(new Owner(
                        rs.getInt("person_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getInt("number_of_pets")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return owners;
    }

    public List<Veterinarian> getVeterinarians() {
        String sql = "SELECT * FROM public.clinic_person WHERE person_type='VETERINARIAN' ORDER BY person_id";
        List<Veterinarian> vets = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vets.add(new Veterinarian(
                        rs.getInt("person_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getInt("experience")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vets;
    }

    public ClinicPerson getPersonById(int id) {
        String sql = "SELECT * FROM public.clinic_person WHERE person_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return mapRowToPerson(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Owner getOwnerById(int id) {
        ClinicPerson p = getPersonById(id);
        return (p instanceof Owner) ? (Owner) p : null;
    }

    public Veterinarian getVeterinarianById(int id) {
        ClinicPerson p = getPersonById(id);
        return (p instanceof Veterinarian) ? (Veterinarian) p : null;
    }

    // -------- UPDATE --------
    public boolean updateOwner(int id, String name, String phone, int numberOfPets) {
        String sql = """
                UPDATE public.clinic_person
                SET name=?, phone=?, number_of_pets=?
                WHERE person_id=? AND person_type='OWNER'
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setInt(3, numberOfPets);
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVeterinarian(int id, String name, String specialization, int experience) {
        String sql = """
                UPDATE public.clinic_person
                SET name=?, specialization=?, experience=?
                WHERE person_id=? AND person_type='VETERINARIAN'
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, specialization);
            stmt.setInt(3, experience);
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // -------- DELETE --------
    public boolean deletePerson(int id) {
        String sql = "DELETE FROM public.clinic_person WHERE person_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // -------- SEARCH --------
    // ILIKE with %wildcards%
    public List<ClinicPerson> searchByName(String keyword) {
        String sql = "SELECT * FROM public.clinic_person WHERE name ILIKE ? ORDER BY person_id";
        List<ClinicPerson> people = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                people.add(mapRowToPerson(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    // BETWEEN (range) for vets
    public List<Veterinarian> searchVetsByExperienceRange(int min, int max) {
        String sql = """
                SELECT * FROM public.clinic_person
                WHERE person_type='VETERINARIAN' AND experience BETWEEN ? AND ?
                ORDER BY person_id
                """;
        List<Veterinarian> vets = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vets.add(new Veterinarian(
                        rs.getInt("person_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getInt("experience")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vets;
    }

    // >= X for vets
    public List<Veterinarian> searchVetsByMinExperience(int minExp) {
        String sql = """
                SELECT * FROM public.clinic_person
                WHERE person_type='VETERINARIAN' AND experience >= ?
                ORDER BY person_id
                """;
        List<Veterinarian> vets = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, minExp);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vets.add(new Veterinarian(
                        rs.getInt("person_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getInt("experience")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vets;
    }

    // -------- mapper (polymorphism) --------
    private ClinicPerson mapRowToPerson(ResultSet rs) throws SQLException {
        int id = rs.getInt("person_id");
        String name = rs.getString("name");
        String type = rs.getString("person_type");

        if ("OWNER".equalsIgnoreCase(type)) {
            return new Owner(
                    id,
                    name,
                    rs.getString("phone"),
                    rs.getInt("number_of_pets")
            );
        } else {
            return new Veterinarian(
                    id,
                    name,
                    rs.getString("specialization"),
                    rs.getInt("experience")
            );
        }
    }
}