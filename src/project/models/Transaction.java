package project.models;

import project.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Transaction {
    public void viewTransactions() {
        String query = "SELECT * FROM transactions";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nTransactions:");
            System.out.println("-------------------------------------------------------------");
            System.out.printf("%-15s %-15s %-10s %-15s %-15s %-15s\n", "Transaction ID", "Patron ID", "Book ID", "Issue Date", "Due Date", "Return Date");
            System.out.println("-------------------------------------------------------------");

            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                int patronId = rs.getInt("patron_id");
                int bookId = rs.getInt("book_id");
                String issueDate = rs.getString("issue_date");
                String dueDate = rs.getString("due_date");
                String returnDate = rs.getString("return_date");

                System.out.printf("%-15d %-15d %-10d %-15s %-15s %-15s\n", transactionId, patronId, bookId, issueDate, dueDate, returnDate);
            }
            System.out.println("-------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
        }
    }
}
