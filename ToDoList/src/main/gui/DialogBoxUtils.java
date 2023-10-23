package main.gui;

import javafx.scene.control.TextInputDialog;

import java.util.Objects;

public class DialogBoxUtils {

    public static void configureTextInputDialog(TextInputDialog dialog, String title, String headerText, String contentText) {
        // Set the title of the dialog
        dialog.setTitle(title);

        // Set the header text of the dialog
        dialog.setHeaderText(headerText);

        // Set the content text of the dialog
        dialog.setContentText(contentText);

        // Add a stylesheet to the dialog pane to customize the appearance
        dialog.getDialogPane().getStylesheets().add(
                Objects.requireNonNull(
                        DialogBoxUtils.class.getResource("/main/css/AddAndEdit.css")
                ).toExternalForm()
        );
    }

}

