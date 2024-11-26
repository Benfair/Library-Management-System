package project.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // Root layout
        BorderPane rootPane = new BorderPane();

        // Navigation buttons
        VBox navigationBar = new VBox(10);

        ViewBooks viewBooks = new ViewBooks(); // Create the ViewBooks instance
        AddBookForm addBookForm = new AddBookForm(viewBooks); // Pass ViewBooks to AddBookForm

        ViewPatrons viewPatrons = new ViewPatrons();
        AddPatronForm addPatronForm = new AddPatronForm(viewPatrons);

        // Welcome message (default view)
        StackPane welcomePane = new StackPane();
        Text welcomeText = new Text("Welcome to Library Management System");
        welcomeText.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        welcomePane.getChildren().add(welcomeText);

        navigationBar.getChildren().addAll(
                new Button("Add Books") {{
                    setOnAction(e -> rootPane.setCenter(addBookForm));
                }},
                new Button("View Books") {{
                    setOnAction(e -> rootPane.setCenter(viewBooks));
                }},
                new Button("Add Patrons") {{
                    setOnAction(e -> rootPane.setCenter(addPatronForm));
                }},
                new Button("View Patrons") {{
                    setOnAction(e -> rootPane.setCenter(viewPatrons));
                }}
        );

        rootPane.setLeft(navigationBar);
        rootPane.setCenter(welcomePane); // Default view

        Scene scene = new Scene(rootPane, 600, 400);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
