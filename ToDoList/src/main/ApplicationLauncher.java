package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationLauncher extends Application {
    // Entry point for the application
    public static void main(String[] args) {
        Application.launch();
    }

    // Start method for the application
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the LoginWindow.fxml using an FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginWindow.fxml"));
        Parent root = loader.load(); // Load the root node
        primaryStage.setScene(new Scene(root)); // Set the scene to the loaded root
        primaryStage.setTitle("Login"); // Set the title of the stage
        primaryStage.show(); // Display the stage
    }
}

