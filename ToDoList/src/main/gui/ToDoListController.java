package main.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ToDoListController {
    @FXML
    private ListView<String> listToDo;
    @FXML
    private ListView<String> listInProgress;
    @FXML
    private ListView<String> listDone;

    @FXML
    public void initialize() {
        setupSelectionListeners();
    }

    private final TodoService todoService;

    public ToDoListController() {
        todoService = new TodoService();
    }

    public void createNewTodoItem(ActionEvent actionEvent) {
        String newItem = todoService.createItem();
        if (newItem != null && !newItem.trim().isEmpty()) {
            listToDo.getItems().add(newItem);
        }
    }

    public void editItemInList(ActionEvent actionEvent) {
        ListView<String> activeList = null;
        String selectedItem;

        if ((selectedItem = listToDo.getSelectionModel().getSelectedItem()) != null) {
            activeList = listToDo;
        } else if ((selectedItem = listInProgress.getSelectionModel().getSelectedItem()) != null) {
            activeList = listInProgress;
        } else if ((selectedItem = listDone.getSelectionModel().getSelectedItem()) != null) {
            activeList = listDone;
        }

        if (selectedItem != null) {
            String editedItem = todoService.editItem(selectedItem);
            if (editedItem != null && !editedItem.trim().isEmpty()) {
                int selectedIndex = activeList.getSelectionModel().getSelectedIndex();
                activeList.getItems().set(selectedIndex, editedItem);
            }
        }
    }

    public void moveToProgress(ActionEvent actionEvent) {
        moveItemHelper(listToDo, listInProgress);
    }

    public void moveBackToTodo(ActionEvent actionEvent) {
        moveItemHelper(listInProgress, listToDo);
    }

    public void moveToDone(ActionEvent actionEvent) {
        moveItemHelper(listInProgress, listDone);
    }

    public void moveBackToProgress(ActionEvent actionEvent) {
        moveItemHelper(listDone, listInProgress);
    }

    private void moveItemHelper(ListView<String> fromList, ListView<String> toList) {
        String selectedItem = fromList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            toList.getItems().add(selectedItem);
            fromList.getItems().remove(selectedItem);
        }
    }

    private void setupSelectionListeners() {
        addClearSelectionListener(listToDo, listInProgress, listDone);
        addClearSelectionListener(listInProgress, listToDo, listDone);
        addClearSelectionListener(listDone, listToDo, listInProgress);
    }

    private void addClearSelectionListener(ListView<String> selectedList, ListView<String>... otherLists) {
        selectedList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                clearSelections(otherLists);
            }
        });
    }

    private void clearSelections(ListView<String>... lists) {
        for (ListView<String> list : lists) {
            list.getSelectionModel().clearSelection();
        }
    }
}
