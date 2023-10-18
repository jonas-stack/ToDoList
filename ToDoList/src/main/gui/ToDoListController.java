package main.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;

import java.util.Objects;
import java.util.Optional;

public class ToDoListController {
    @FXML
    public Button btnRight1;
    @FXML
    public Button btnLeft1;
    @FXML
    public Button btnRight2;
    @FXML
    public Button btnLeft2;
    @FXML
    public Button btnNewToDo;

    public ListView<String> listToDo;
    public ListView<String> listInProgress;
    public ListView<String> listDone;

    public void createNewTodoItem(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Todo Item");
        dialog.setHeaderText("Add a new Todo item");
        dialog.setContentText("Enter your todo item:");
        dialog.getDialogPane().getStylesheets().add(
                Objects.requireNonNull(
                        getClass().getResource("/main/css/AddAndEdit.css")
                ).toExternalForm()
        );
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(item -> listToDo.getItems().add(item));

        setListDoubleClickHandler(listToDo);
        setListDoubleClickHandler(listInProgress);
        setListDoubleClickHandler(listDone);
    }

    private void setListDoubleClickHandler(ListView<String> listView) {
        listView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                editItemInList(listView);
            }
        });
    }

    @FXML
    public void editItemInList(ListView<String> listView) {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            TextInputDialog dialog = new TextInputDialog(selectedItem);
            dialog.setTitle("Edit Todo Item");
            dialog.setHeaderText("Edit the selected Todo item");
            dialog.setContentText("Edit your todo item:");
            dialog.getDialogPane().getStylesheets().add(
                    Objects.requireNonNull(
                            getClass().getResource("/main/css/AddAndEdit.css")
                    ).toExternalForm()
            );
            Optional<String> result = dialog.showAndWait();
            editBySelectedState(result, listView);
        }
    }

    private void editBySelectedState(Optional<String> result, ListView<String> listView) {
        result.ifPresent(editedItem -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                listView.getItems().set(selectedIndex, editedItem);
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
