package project.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.models.Patron;

import java.sql.*;

public class PatronDAO {

    // Add a book to the database
    public static boolean addPatron(Patron patron) {
        String sql = "INSERT INTO patrons (name, email, contact) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patron.getName());
            stmt.setString(2, patron.getEmail());
            stmt.setString(3, patron.getContact());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the patron was added successfully
        } catch (SQLException e) {
            System.err.println("Error adding patron: " + e.getMessage());
        }
        return false;
    }

    // Fetch all patrons from the database
    public static ObservableList<Patron> getAllPatrons() {
        ObservableList<Patron> patrons = FXCollections.observableArrayList();
        String query = "SELECT * FROM patrons";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                patrons.add(new Patron(
                        resultSet.getInt("patron_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("contact"),
                        resultSet.getTimestamp("registration_date").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            System.err.println("Error retrieving patrons: " + e.getMessage());
        }
        return patrons;
    }

    // Delete a patron by ID
    public static boolean deletePatron(int id) {
        String sql = "DELETE FROM patrons WHERE patron_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting patron: " + e.getMessage());
        }
        return false; // Return false if the deletion failed
    }
}
