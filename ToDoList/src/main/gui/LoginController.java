package main.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    // TextField for login label
    public TextField lblLogin;
    // Button for proceeding to the next window
    public Button btnProceed;

    // Event handler for the proceed button
    public void Proceed(ActionEvent actionEvent) throws IOException {
        // Creates an FXMLLoader to load the ToDoListWindow
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ToDoListWindow.fxml"));
        // Load the FXML file and get the root node
        Parent root = loader.load();

        // Create a new stage for the ToDoListWindow
        Stage stage = new Stage();
        stage.setScene(new Scene(root)); // Set the scene to the loaded root
        stage.setTitle("ToDoList"); // Set the title of the stage
        stage.initModality(Modality.APPLICATION_MODAL); // Set the modality of the stage to application modal
        stage.show(); // Display the stage
    }
}

