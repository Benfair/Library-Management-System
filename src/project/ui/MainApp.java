package project.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // Root layout
        BorderPane rootPane = new BorderPane();

        // Navigation buttons
        VBox navigationBar = new VBox(10);
        ViewBooks viewBooks = new ViewBooks(); // Create the ViewBooks instance
        AddBookForm addBookForm = new AddBookForm(viewBooks); // Pass ViewBooks to AddBookForm

        navigationBar.getChildren().addAll(
                new javafx.scene.control.Button("Add Books") {{
                    setOnAction(e -> rootPane.setCenter(addBookForm));
                }},
                new javafx.scene.control.Button("View Books") {{
                    setOnAction(e -> rootPane.setCenter(viewBooks));
                }}
        );

        rootPane.setLeft(navigationBar);
        rootPane.setCenter(addBookForm); // Default view

        Scene scene = new Scene(rootPane, 600, 400);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
