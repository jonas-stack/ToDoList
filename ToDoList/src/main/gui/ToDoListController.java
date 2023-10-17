package main.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class ToDoListController {
    @FXML
    public Button btnRight1, btnLeft1, btnRight2, btnLeft2;

    public ListView<String> listToDo;
    public ListView<String> listInProgress;
    public ListView<String> listDone;

    public void moveR1(ActionEvent actionEvent) {
        String selectedItem = listToDo.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listInProgress.getItems().add(selectedItem);
            listToDo.getItems().remove(selectedItem);
        }
    }

    public void moveL1(ActionEvent actionEvent) {
        String selectedItem = listInProgress.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listToDo.getItems().add(selectedItem);
            listInProgress.getItems().remove(selectedItem);
        }
    }

    public void moveR2(ActionEvent actionEvent) {
        String selectedItem = listInProgress.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listDone.getItems().add(selectedItem);
            listInProgress.getItems().remove(selectedItem);
        }
    }

    public void moveL2(ActionEvent actionEvent) {
        String selectedItem = listDone.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listInProgress.getItems().add(selectedItem);
            listDone.getItems().remove(selectedItem);
        }
    }

    public void createNewTodoItem(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Todo Item");
        dialog.setHeaderText("Add a new Todo item");
        dialog.setContentText("Enter your todo item:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(item -> listToDo.getItems().add(item));
    }
}
