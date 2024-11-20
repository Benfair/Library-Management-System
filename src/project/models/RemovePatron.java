package project.models;

import project.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class RemovePatron {
    public void removePatron() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Patron ID to remove: ");
        int patronId = scanner.nextInt();

        String query = "DELETE FROM patrons WHERE patron_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, patronId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Patron removed successfully!");
            } else {
                System.out.println("No patron found with the given ID.");
            }
        } catch (Exception e) {
            System.out.println("Error removing patron: " + e.getMessage());
        }
    }
}
