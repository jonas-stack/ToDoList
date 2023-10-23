package main.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;

import java.util.Optional;

public class ToDoListController {

    @FXML
    private Button btnNewToDo;

    @FXML
    private ListView<String> listToDo;

    @FXML
    private ListView<String> listInProgress;

    @FXML
    private ListView<String> listDone;

    // Event handler for creating a new todo item
    public void createNewTodoItem(ActionEvent actionEvent) {
        // Create and configure a TextInputDialog for adding a new todo item
        TextInputDialog dialog = new TextInputDialog();
        configureTextInputDialog(dialog, "New Todo Item", "Add a new Todo item", "Enter your todo item:");
        // Show the dialog and handle the result
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(item -> listToDo.getItems().add(item)); // Add the item to the todo list
        setListDoubleClickHandler(listToDo);
        setListDoubleClickHandler(listInProgress);
        setListDoubleClickHandler(listDone);
    }

    // Configures the TextInputDialog with the provided parameters
    private void configureTextInputDialog(TextInputDialog dialog, String title, String headerText, String contentText) {
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setContentText(contentText);
    }

    // Set double click handler for a list
    private void setListDoubleClickHandler(ListView<String> listView) {
        listView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                editItemInList(listView);
            }
        });
    }

    // Event handler for editing an item in the list
    public void editItemInList(ListView<String> listView) {
        // Get the selected item and open a TextInputDialog for editing
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            TextInputDialog dialog = new TextInputDialog(selectedItem);
            configureTextInputDialog(dialog, "Edit Todo Item", "Edit the selected Todo item", "Edit your todo item:");
            // Show the dialog and handle the result
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && !result.get().isBlank()) {
                editBySelectedState(result, listView); // Edit the item based on the result
            } else {
                listView.getItems().remove(selectedItem); // Remove the item if the result is blank
            }
        }
    }

    // Edits the item in the list based on the selected state
    private void editBySelectedState(Optional<String> result, ListView<String> listView) {
        result.ifPresent(editedItem -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                listView.getItems().set(selectedIndex, editedItem); // Update the item in the list
            }
        });
    }

    // Moves an item from the source list to the destination list
    public void moveItem(ListView<String> sourceList, ListView<String> destinationList) {
        String selectedItem = sourceList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            destinationList.getItems().add(selectedItem); // Add the item to the destination list
            sourceList.getItems().remove(selectedItem); // Remove the item from the source list
        }
    }

    // Event handlers for moving items between lists
    public void moveToProgress(ActionEvent actionEvent) {
        moveItem(listToDo, listInProgress);
    }

    public void moveBackToTodo(ActionEvent actionEvent) {
        moveItem(listInProgress, listToDo);
    }

    public void moveToDone(ActionEvent actionEvent) {
        moveItem(listInProgress, listDone);
    }

    public void moveBackToProgress(ActionEvent actionEvent) {
        moveItem(listDone, listInProgress);
    }
}


