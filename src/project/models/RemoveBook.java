package project.models;

import project.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class RemoveBook {

    public void removeBook() {
        try (Connection connection = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter Book ID to Remove:");
            int bookId = scanner.nextInt();

            String query = "DELETE FROM books WHERE book_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book removed successfully!");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to remove book.");
        }
    }
}
