package project.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import project.database.BookDAO;
import project.models.Book;

public class ViewBooks extends VBox {
    private TableView<Book> tableView;

    public ViewBooks() {
        this.setSpacing(10);

        tableView = new TableView<>();

        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<Book, Integer> copiesColumn = new TableColumn<>("Copies");
        copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copiesAvailable"));

        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, isbnColumn, copiesColumn);

        Button deleteButton = new Button("Delete Selected Book");
        deleteButton.setOnAction(event -> deleteSelectedBook());

        this.getChildren().addAll(tableView, deleteButton);

        loadBooks(); // Load books from the database into the TableView
    }

    // Load books into the TableView
    public void loadBooks() {
        ObservableList<Book> books = BookDAO.getAllBooks(); // Static method to fetch books
        tableView.setItems(books);
    }

    // Delete the selected book from the database and refresh the TableView
    private void deleteSelectedBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean isDeleted = BookDAO.deleteBook(selectedBook.getId()); // Call delete logic from the model
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Book deleted successfully.").showAndWait();
                loadBooks(); // Refresh the table
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the book.").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No book selected. Please select a book to delete.").showAndWait();
        }
    }
}
