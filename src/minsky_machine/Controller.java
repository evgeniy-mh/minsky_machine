package minsky_machine;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import minsky_machine.command.ConditionalGoto;
import minsky_machine.command.CounterPlus;
import minsky_machine.command.Goto;
import minsky_machine.command.Stop;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

public class Controller {
    private Stage stage;
    private Serializer serializer = new Persister();

    @FXML
    public Button loadProgramButton;

    @FXML
    public ListView programList;

    public Controller(Stage stage){
        this.stage=stage;
    }

    public void loadProgramButton_OnAction(){
        File programFile = showOpenProgramFileDialog();
        TwoCMProgram twoCMProgram=parseProgramFile(programFile);
        programList.setItems(FXCollections.observableList(twoCMProgram.program));
        //testXMLParsing();
    }

    private File showOpenProgramFileDialog(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл с программой 2сМ");
        return fileChooser.showOpenDialog(this.stage);
    }

    private TwoCMProgram parseProgramFile(File file){
        try {
            TwoCMProgram twoCMProgram = this.serializer.read(TwoCMProgram.class, file);
            return twoCMProgram;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void testXMLParsing(){
        TwoCMProgram program=new TwoCMProgram();
        program.program.add(new ConditionalGoto("q0",Counter.A,"q1","q2"));
        program.program.add(new CounterPlus("q1",Counter.A,"q5"));
        program.program.add(new Goto("q1","q2"));
        program.program.add(new Stop("q1"));

        programList.setItems(FXCollections.observableList(program.program));

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
