package project.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ViewPatrons extends VBox {
    public ViewPatrons() {
        this.setSpacing(10);

        TableView<Patron> tableView = new TableView<>();

        TableColumn<Patron, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Patron, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(nameColumn, emailColumn);

        // Sample data
        ObservableList<Patron> patrons = FXCollections.observableArrayList(
                new Patron("John Doe", "john.doe@example.com"),
                new Patron("Jane Smith", "jane.smith@example.com")
        );

        tableView.setItems(patrons);

        this.getChildren().addAll(tableView);
    }

    // Patron model class
    public static class Patron {
        private String name;
        private String email;

        public Patron(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
