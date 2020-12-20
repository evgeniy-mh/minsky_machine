package minsky_machine;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import minsky_machine.command.*;
import minsky_machine.minsky_executor.CommandType;
import minsky_machine.minsky_executor.Counter;
import minsky_machine.minsky_executor.ProgramExecutor;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

public class Controller {
    private Stage stage;
    private Serializer serializer = new Persister();
    private TwoCMProgram twoCMProgram;
    private ProgramExecutor twoCMExecutor;

    @FXML
    public Label ACounterValue;

    @FXML
    public Label BCounterValue;

    @FXML
    public TextField AStartValueTextField;

    @FXML
    public TextField BStartValueTextField;

    @FXML
    public ListView programListView;

    @FXML
    public ListView executionHistoryListView;

    public Controller(Stage stage){
        this.stage=stage;
    }

    public void initialize() {
        twoCMExecutor = new ProgramExecutor();
        twoCMExecutor.onStopCallback = () -> {
            executionHistoryListView.getItems().addAll(twoCMExecutor.executionHistory);
        };

        executionHistoryListView.refresh();

        AStartValueTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            ACounterValue.textProperty().setValue(newValue);
        });

        BStartValueTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            BCounterValue.textProperty().setValue(newValue);
        });

        twoCMExecutor.AValue.addListener((observable, oldValue, newValue) -> {
            ACounterValue.textProperty().setValue(newValue.toString());
        });

        twoCMExecutor.BValue.addListener((observable, oldValue, newValue) -> {
            BCounterValue.textProperty().setValue(newValue.toString());
        });
    }

    public void loadProgramButton_OnAction(){
        File programFile = showOpenProgramFileDialog();
        this.twoCMProgram=parseProgramFile(programFile);
        programListView.setItems(FXCollections.observableList(twoCMProgram.program));
        twoCMExecutor.program = twoCMProgram.program;
    }

    public void startProgramButton_OnAction(){
        twoCMExecutor.startState = "q1";
        executionHistoryListView.getItems().clear();
        twoCMExecutor.AValue.set(Integer.parseInt(AStartValueTextField.textProperty().get()));
        twoCMExecutor.BValue.set(Integer.parseInt(BStartValueTextField.textProperty().get()));
        twoCMExecutor.Run();
    }

    private File showOpenProgramFileDialog(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл с программой 2сМ");
        return fileChooser.showOpenDialog(this.stage);
    }

    private TwoCMProgram parseProgramFile(File file){
        try {
            TwoCMProgram twoCMProgram = this.serializer.read(TwoCMProgram.class, file);
            for(TwoCMCommand command: twoCMProgram.program){
                switch (command.getClass().getSimpleName()){
                    case "ConditionalGoto":
                        command.type = CommandType.Conditional_goto;
                        break;
                    case "CounterPlus":
                        command.type=CommandType.Counter_plus;
                        break;
                    case "Goto":
                        command.type=CommandType.Goto;
                        break;
                    case "Stop":
                        command.type=CommandType.Stop;
                        break;
                }
            }
            return twoCMProgram;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void testXMLParsing(){
        TwoCMProgram program=new TwoCMProgram();
        program.program.add(new ConditionalGoto("q0", Counter.A, "q1", "q2"));
        program.program.add(new CounterPlus("q1",Counter.A,"q5"));
        program.program.add(new Goto("q1","q2"));
        program.program.add(new Stop("q1"));

        programListView.setItems(FXCollections.observableList(program.program));

        Serializer serializer = new Persister();
        File result = new File("example.xml");

        try {
            serializer.write(program, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            TwoCMProgram read = serializer.read(TwoCMProgram.class, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
