package project.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.models.Book;

import java.sql.*;

public class BookDAO {

    // Add a book to the database
    public static boolean addBook(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, copies_available) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setInt(4, book.getCopiesAvailable());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the book was added successfully
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
        return false;
    }

    // Fetch all books from the database
    public static ObservableList<Book> getAllBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        String query = "SELECT * FROM books";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getInt("copies_available")
                ));
            }
        } catch (Exception e) {
            System.err.println("Error retrieving books: " + e.getMessage());
        }
        return books;
    }

    // Delete a book by ID
    public static boolean deleteBook(int id) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
        return false; // Return false if the deletion failed
    }
}
