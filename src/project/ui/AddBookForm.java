package project.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import project.models.Book;
import project.database.BookDAO;

public class AddBookForm extends VBox {
    private final ViewBooks viewBooks;

    public AddBookForm(ViewBooks viewBooks) {
        this.viewBooks = viewBooks;

        this.setPadding(new Insets(20));
        this.setSpacing(10);

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();

        Label authorLabel = new Label("Author:");
        TextField authorField = new TextField();

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField();

        Label copiesLabel = new Label("Copies Available:");
        TextField copiesField = new TextField();

        Button btnSave = new Button("Save");
        btnSave.setOnAction(event -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            int copiesAvailable = Integer.parseInt(copiesField.getText());

            Book newBook = new Book(0, title, author, isbn, copiesAvailable);
            boolean isAdded = BookDAO.addBook(newBook);

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Book added successfully!").showAndWait();

                // Clear the form fields
                titleField.clear();
                authorField.clear();
                isbnField.clear();
                copiesField.clear();

                // Refresh the TableView
                viewBooks.loadBooks();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add the book.").showAndWait();
            }
        });

        this.getChildren().addAll(
                titleLabel, titleField,
                authorLabel, authorField,
                isbnLabel, isbnField,
                copiesLabel, copiesField,
                btnSave
        );
    }
}
