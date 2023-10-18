package main.gui;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class TodoService {
    public String createItem() {
        TextInputDialog dialog = createDialogHelper("New Todo Item", "Add a new Todo item", "Enter your todo item:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public String editItem(String item) {
        TextInputDialog dialog = createDialogHelper("Edit Todo Item", "Edit the selected Todo item", "Edit your todo item:");
        dialog.getEditor().setText(item);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(item);  // return original item if nothing was edited
    }

    private TextInputDialog createDialogHelper(String title, String header, String content) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        return dialog;
    }
}
