package project.models;

import project.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

public class IssueBook {
    public void issueBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Patron ID: ");
        int patronId = scanner.nextInt();

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();

        System.out.print("Enter Issue Date (YYYY-MM-DD): ");
        String issueDate = scanner.next();

        // Calculate due date (e.g., 14 days from the issue date)
        LocalDate issueDateParsed = LocalDate.parse(issueDate);
        LocalDate dueDate = issueDateParsed.plusDays(14);

        // Check if the book is available
        String checkQuery = "SELECT copies_available FROM books WHERE book_id = ?";
        String updateQuery = "UPDATE books SET copies_available = copies_available - 1 WHERE book_id = ?";
        String insertTransaction = "INSERT INTO transactions (patron_id, book_id, issue_date, due_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertTransaction)) {

            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int copiesAvailable = rs.getInt("copies_available");

                if (copiesAvailable > 0) {
                    // Update book availability
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();

                    // Insert transaction
                    insertStmt.setInt(1, patronId);
                    insertStmt.setInt(2, bookId);
                    insertStmt.setString(3, issueDate);
                    insertStmt.setString(4, dueDate.toString());

                    insertStmt.executeUpdate();
                    System.out.println("Book issued successfully! Due date: " + dueDate);
                } else {
                    System.out.println("Book is not available.");
                }
            } else {
                System.out.println("Book not found.");
            }
        } catch (Exception e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }
}
