package project.models;

import project.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

public class ReturnBook {
    public void returnBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Transaction ID: ");
        int transactionId = scanner.nextInt();

        String fetchTransactionQuery = "SELECT book_id, due_date FROM transactions WHERE transaction_id = ?";
        String updateTransactionQuery = "UPDATE transactions SET return_date = CURRENT_DATE WHERE transaction_id = ?";
        String updateBookCopiesQuery = "UPDATE books SET copies_available = copies_available + 1 WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement fetchTransactionStmt = conn.prepareStatement(fetchTransactionQuery);
             PreparedStatement updateTransactionStmt = conn.prepareStatement(updateTransactionQuery);
             PreparedStatement updateBookCopiesStmt = conn.prepareStatement(updateBookCopiesQuery)) {

            // Fetch book_id and due_date from the transaction
            fetchTransactionStmt.setInt(1, transactionId);
            ResultSet rs = fetchTransactionStmt.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                LocalDate currentDate = LocalDate.now();

                // Update return_date in the transaction
                updateTransactionStmt.setInt(1, transactionId);
                updateTransactionStmt.executeUpdate();

                // Increment book copies
                updateBookCopiesStmt.setInt(1, bookId);
                updateBookCopiesStmt.executeUpdate();

                if (currentDate.isAfter(dueDate)) {
                    System.out.println("Book returned late! Due date was: " + dueDate);
                } else {
                    System.out.println("Book returned on time! Due date was: " + dueDate);
                }
            } else {
                System.out.println("Transaction not found.");
            }
        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}
