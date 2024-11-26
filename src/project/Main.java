package project;

import project.database.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Database connection failed!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
