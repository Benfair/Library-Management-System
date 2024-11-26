package project.models;

import project.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Patron {
    public void viewPatrons() {
        String query = "SELECT * FROM patrons";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nPatrons:");
            System.out.println("-------------------------------------------");
            System.out.printf("%-10s %-20s %-20s %-10s\n", "Patron ID", "Name", "Email", "Contact");
            System.out.println("-------------------------------------------");

            while (rs.next()) {
                int patronId = rs.getInt("patron_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String contact = rs.getString("contact");

                System.out.printf("%-10d %-20s %-20s %-10s\n", patronId, name, email, contact);
            }
            System.out.println("-------------------------------------------");

        } catch (Exception e) {
            System.out.println("Error fetching patrons: " + e.getMessage());
        }
    }
}
