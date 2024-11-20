package project.models;

import project.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddBook {

    public void addBook(String title, String author, String isbn, int copiesAvailable) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO books (title, author, isbn, copies_available) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, isbn);
            preparedStatement.setInt(4, copiesAvailable);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected <= 0) {
                throw new Exception("Failed to add book");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to add book: " + e.getMessage());
        }
    }
}
