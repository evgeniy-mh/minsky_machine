package minsky_machine;

import minsky_machine.command.TwoCMCommand;

import java.util.ArrayList;
import java.util.List;

public class TwoCMProgram {
    enum CommandType {
        Counter_plus,
        Conditional_goto,
        Goto,
        Stop
    }

    List<TwoCMCommand> program=new ArrayList<>();
}
