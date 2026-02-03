package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabaseConnection {

    public static void main(String[] args) {

        Connection conn = DatabaseConnection.getConnection();

        if (conn == null) {
            System.out.println("❌ Connection is NULL. Check URL/USER/PASSWORD.");
            return;
        }

        System.out.println("✅ Connection object created.");

        // 1) Print DB name (catalog)
        try {
            System.out.println("DB name (catalog): " + conn.getCatalog());
        } catch (SQLException e) {
            System.out.println("❌ Cannot read catalog: " + e.getMessage());
        }

        // 2) Check schema + table existence + count rows
        try {
            System.out.println("Current schema: " + querySingleString(conn, "SELECT current_schema();"));

            int tableExists = querySingleInt(conn,
                    "SELECT COUNT(*) FROM information_schema.tables " +
                            "WHERE table_schema='public' AND table_name='pet';");

            System.out.println("public.pet exists: " + (tableExists == 1 ? "YES ✅" : "NO ❌"));

            if (tableExists == 1) {
                int count = querySingleInt(conn, "SELECT COUNT(*) FROM public.pet;");
                System.out.println("Rows in public.pet: " + count);
            }

        } catch (SQLException e) {
            System.out.println("❌ SQL test failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
            System.out.println("🔒 Connection closed.");
        }
    }

    // Helper: returns 1 string value
    private static String querySingleString(Connection conn, String sql) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            return rs.getString(1);
        }
    }

    // Helper: returns 1 int value
    private static int querySingleInt(Connection conn, String sql) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }
}