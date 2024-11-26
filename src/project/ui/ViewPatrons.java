package project.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import project.database.PatronDAO;
import project.models.Patron;

import java.time.LocalDateTime;

public class ViewPatrons extends VBox {
    private TableView<Patron> tableView;

    public ViewPatrons() {
        this.setSpacing(10);

        tableView = new TableView<>();

        TableColumn<Patron, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Patron, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Patron, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Patron, String> contactColumn = new TableColumn<>("Contact");
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));

        TableColumn<Patron, LocalDateTime> registrationColumn = new TableColumn<>("Registration Date");
        registrationColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        tableView.getColumns().addAll(idColumn, nameColumn, emailColumn, contactColumn, registrationColumn);

        Button deleteButton = new Button("Delete Selected Patron");
        deleteButton.setOnAction(event -> deleteSelectedPatron());

        this.getChildren().addAll(tableView, deleteButton);

        loadPatrons(); // Load patrons from the database into the TableView
    }

    // Load patrons into the TableView
    public void loadPatrons() {
        ObservableList<Patron> patrons = PatronDAO.getAllPatrons(); // Static method to fetch patrons
        tableView.setItems(patrons);
    }

    // Delete the selected patron from the database and refresh the TableView
    private void deleteSelectedPatron() {
        Patron selectedPatron = tableView.getSelectionModel().getSelectedItem();
        if (selectedPatron != null) {
            boolean isDeleted = PatronDAO.deletePatron(selectedPatron.getId()); // Call delete logic from the model
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Patron deleted successfully.").showAndWait();
                loadPatrons(); // Refresh the table
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the book.").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Patron selected. Please select a patron to delete.").showAndWait();
        }
    }
}
