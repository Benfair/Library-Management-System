package project.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ViewTransactions extends VBox {

    public ViewTransactions() {
        // Create buttons for managing transactions
        Button issueBookButton = new Button("Issue Book");
        Button returnBookButton = new Button("Return Book");

        // Set actions for buttons (you can add logic later)
        issueBookButton.setOnAction(e -> {
            // Logic to issue a book (you can open a form or dialog)
        });

        returnBookButton.setOnAction(e -> {
            // Logic to return a book
        });

        // Add buttons to the layout
        getChildren().addAll(issueBookButton, returnBookButton);
    }
}
