package project.ui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import project.models.IssueBook;

public class IssueBookWindow {

    public void show() {
        // Create a new Stage for Issue Book Window
        Stage stage = new Stage();

        // Create form components (labels, text fields, buttons)
        Label patronIdLabel = new Label("Enter Patron ID:");
        TextField patronIdField = new TextField();

        Label bookIdLabel = new Label("Enter Book ID:");
        TextField bookIdField = new TextField();

        Label issueDateLabel = new Label("Enter Issue Date (YYYY-MM-DD):");
        TextField issueDateField = new TextField();

        Button issueBookButton = new Button("Issue Book");

        // Button action to issue book
        issueBookButton.setOnAction(event -> {
            int patronId = Integer.parseInt(patronIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            String issueDate = issueDateField.getText();

            IssueBook issueBook = new IssueBook();
            issueBook.issueBook(); // Assuming this method handles the issue logic

            stage.close();
        });

        // Layout
        VBox layout = new VBox(10, patronIdLabel, patronIdField, bookIdLabel, bookIdField, issueDateLabel, issueDateField, issueBookButton);
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Issue Book");
        stage.show();
    }
}
