package project.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.DatabaseConnection;
//import project.ui.BookData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FetchBooks {
    public ObservableList fetchBooksForUI() {
        ObservableList books = FXCollections.observableArrayList();
        String query = "SELECT * FROM books";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                books.add(
                        rs.getInt("book_id")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching books: " + e.getMessage());
        }
        return books;
    }
}
