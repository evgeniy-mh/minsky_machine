package minsky_machine;

import minsky_machine.command.TwoCMCommand;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

public class TwoCMProgram {
    @ElementList(name = "listing")
    ArrayList<TwoCMCommand> program=new ArrayList<>();

    public List<TwoCMCommand> getProgram() {
        return program;
    }

    public void setProgram(ArrayList<TwoCMCommand> program) {
        this.program = program;
    }
}
