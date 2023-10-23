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
        // Create a new TextInputDialog
        TextInputDialog dialog = new TextInputDialog();
        // Configure the TextInputDialog using the DialogBoxUtils class
        DialogBoxUtils.configureTextInputDialog(dialog, "New Todo Item", "Add a new Todo item", "Enter your todo item:");
        // Show the dialog and wait for the user's input
        Optional<String> result = dialog.showAndWait();
        // If the result is present (i.e., the user entered something)...
        result.ifPresent(item -> listToDo.getItems().add(item));

        // Set double-click handler for the lists
        setListDoubleClickHandler(listToDo);
        setListDoubleClickHandler(listInProgress);
        setListDoubleClickHandler(listDone);
    }


    // Event handler for editing an item in the list
    public void editItemInList(ListView<String> listView) {
        // Get the selected item from the list view
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        // If an item is selected...
        if (selectedItem != null) {
            // Create a TextInputDialog with the selected item as the default value
            TextInputDialog dialog = new TextInputDialog(selectedItem);
            // Configure the TextInputDialog using the DialogBoxUtils class
            DialogBoxUtils.configureTextInputDialog(dialog, "Edit Todo Item", "Edit the selected Todo item", "Edit your todo item:");
            // Show the dialog and wait for the user's input
            Optional<String> result = dialog.showAndWait();

            // If the result is present and not blank...
            if (result.isPresent() && !result.get().isBlank()) {
                // Call the editBySelectedState method to edit the item in the list view
                editBySelectedState(result, listView);
            } else { // If the result is blank or not present...
                // Remove the selected item from the list view
                listView.getItems().remove(selectedItem);
            }
        }
    }


    // This method sets the double-click handler for the provided ListView of strings.
    private void setListDoubleClickHandler(ListView<String> listView) {
        listView.setOnMouseClicked(mouseEvent -> {
            // Check if the mouse event is a double-click by verifying the button and click count.
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                // If it's a double-click, call the editItemInList method, passing the ListView.
                editItemInList(listView);
            }
        });
    }


    // This method is responsible for updating the selected item in the list based on the provided result.
    private void editBySelectedState(Optional<String> result, ListView<String> listView) {
        // If the result is present, update the item in the list.
        result.ifPresent(editedItem -> {
            // Get the selected index.
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            // If a valid index is obtained, update the item in the list.
            if (selectedIndex >= 0) {
                listView.getItems().set(selectedIndex, editedItem);
            }
        });
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

    public void moveItem(ListView<String> sourceList, ListView<String> destinationList) {
        // Get the selected item from the source list
        String selectedItem = sourceList.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedItem != null) {
            // Add the selected item to the destination list
            destinationList.getItems().add(selectedItem);

            // Remove the selected item from the source list
            sourceList.getItems().remove(selectedItem);
        }
    }

}




