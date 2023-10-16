package main.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ToDoListController {
    @FXML
    public Button moveRight1, moveLeft1, moveRight2, moveLeft2;
    @FXML
    public ListView listToDo;
    @FXML
    public ListView listInProgress;
    @FXML
    public ListView listDone;


    public TextField toDo1,toDo2,toDo3,toDo4,toDo5,toDo6,toDo7,toDo8,toDo9,toDo10,toDo11,toDo12,toDo13,toDo14,toDo15;
    public TextField inProgress1,inProgress2,inProgress3,inProgress4,inProgress5,inProgress6,inProgress7,inProgress8,inProgress9,inProgress10,inProgress11,inProgress12,inProgress13,inProgress14,inProgress15;
    public TextField done1,done2,done3,done4,done5,done6,done7,done8,done9,done10,done11,done12,done13,done14,done15;

    public void moveR1(ActionEvent actionEvent) {


        String textToMove1 = toDo1.getText();
        inProgress1.setText(textToMove1);
        toDo1.setText("");

        String textToMove2 = toDo2.getText();
        inProgress2.setText(textToMove2);
        toDo2.setText("");

        String textToMove3 = toDo3.getText();
        inProgress3.setText(textToMove3);
        toDo3.setText("");
    }

    public void moveL1(javafx.event.ActionEvent actionEvent) {


        String textToMove1 = inProgress1.getText();
        toDo1.setText(textToMove1);
        inProgress1.setText("");

        String textToMove2 = inProgress2.getText();
        toDo2.setText(textToMove2);
        inProgress2.setText("");

        String textToMove3 = inProgress3.getText();
        toDo3.setText(textToMove3);
        inProgress3.setText("");
    }

    public void moveR2(javafx.event.ActionEvent actionEvent) {


        String textToMove1 = inProgress1.getText();
        done1.setText(textToMove1);
        inProgress1.setText("");

        String textToMove2 = inProgress2.getText();
        done2.setText(textToMove2);
        inProgress2.setText("");

        String textToMove3 = inProgress3.getText();
        done3.setText(textToMove3);
        inProgress3.setText("");

    }

    public void moveL2(javafx.event.ActionEvent actionEvent) {


        String textToMove1 = done1.getText();
        inProgress1.setText(textToMove1);
        done1.setText("");

        String textToMove2 = done2.getText();
        inProgress2.setText(textToMove2);
        done2.setText("");

        String textToMove3 = done3.getText();
        inProgress3.setText(textToMove3);
        done3.setText("");
    }
}
