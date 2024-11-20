package project.models;

import project.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddPatron {
    public static void addPatron(String name, String email) {
        String query = "INSERT INTO patrons (name, email) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected + " patron(s) added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
