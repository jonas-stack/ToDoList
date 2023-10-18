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
    public Button btnEdit;
    public Button btnNewToDo;

    public void createNewTodoItem(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Todo Item");
        dialog.setHeaderText("Add a new Todo item");
        dialog.setContentText("Enter your todo item:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(item -> listToDo.getItems().add(item));
    }

    public void editItemInList(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        String selectedItem = listToDo.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        dialog.setTitle("Edit Todo Item");
        dialog.setHeaderText("Edit the selected Todo item");
        dialog.setContentText("Edit your todo item:");
        dialog.getEditor().setText(selectedItem);
        Optional<String> result = dialog.showAndWait();
        editBySelectedState(result);
    }

    private void editBySelectedState(Optional<String> result) {
        System.out.println(result);
        result.ifPresent(editedItem -> {
            // Logic to handle the edited item
            System.out.println("Edited item: " + editedItem);
            // Update the item in the list
            int selectedIndex = listToDo.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                listToDo.getItems().set(selectedIndex, editedItem);
            }
        });
    }


    public void moveToProgress(ActionEvent actionEvent) {
        String selectedItem = listToDo.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listInProgress.getItems().add(selectedItem);
            listToDo.getItems().remove(selectedItem);
        }
    }

    public void moveBackToTodo(ActionEvent actionEvent) {
        String selectedItem = listInProgress.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listToDo.getItems().add(selectedItem);
            listInProgress.getItems().remove(selectedItem);
        }
    }

    public void moveToDone(ActionEvent actionEvent) {
        String selectedItem = listInProgress.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listDone.getItems().add(selectedItem);
            listInProgress.getItems().remove(selectedItem);
        }
    }

    public void moveBackToProgress(ActionEvent actionEvent) {
        String selectedItem = listDone.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listInProgress.getItems().add(selectedItem);
            listDone.getItems().remove(selectedItem);
        }
    }
}
