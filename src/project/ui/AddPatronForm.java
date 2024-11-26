package project.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import project.models.Patron;
import project.database.PatronDAO;

public class AddPatronForm extends VBox {
    private final ViewPatrons viewPatrons;

    public AddPatronForm(ViewPatrons viewPatrons) {
        this.viewPatrons = viewPatrons;

        this.setPadding(new Insets(20));
        this.setSpacing(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label contactLabel = new Label("Contact:");
        TextField contactField = new TextField();

        Label messageLabel = new Label();

        Button btnSave = new Button("Save");
        btnSave.setOnAction(event -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String contact = contactField.getText();

            // Validate inputs
            if (name.isEmpty() || email.isEmpty() || contact.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "All fields are required.").showAndWait();
                return;
            }

            // Create Patron object (omit registrationDate)
            Patron newPatron = new Patron(0, name, email, contact, null);

            // Save to database
            boolean isAdded = PatronDAO.addPatron(newPatron);

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Patron added successfully!").showAndWait();

                // Clear the form fields
                nameField.clear();
                emailField.clear();
                contactField.clear();

                // Refresh the TableView
                viewPatrons.loadPatrons();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add the patron.").showAndWait();
            }
        });

        this.getChildren().addAll(
                nameLabel, nameField,
                emailLabel, emailField,
                contactLabel, contactField,
                btnSave, messageLabel
        );
    }
}
