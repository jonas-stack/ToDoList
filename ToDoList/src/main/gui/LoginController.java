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

    public TextField lblLogin;
    public Button btnProceed;

    public void Proceed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ToDoListWindow.fxml"));// Create an FXMLLoader
        Parent root = loader.load();// Load the FXML file and obtain the root node

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("ToDoList");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
