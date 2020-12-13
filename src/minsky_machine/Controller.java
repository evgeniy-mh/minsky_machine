package minsky_machine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import minsky_machine.command.ConditionalGoto;
import minsky_machine.command.CounterPlus;
import minsky_machine.command.TwoCMCommand;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    public Button loadProgramButton;

    @FXML
    public ListView programList;

    public void loadProgramButton_OnAction(){
        System.out.println("click");

        TwoCMProgram program=new TwoCMProgram();
        program.program.add(new ConditionalGoto("q0",Counter.A,"q1","q2"));
        program.program.add(new CounterPlus("q1",Counter.A,"q5"));

        programList.setItems(FXCollections.observableList(program.program));
    }
}
