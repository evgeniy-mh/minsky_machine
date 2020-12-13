package minsky_machine.command;

import minsky_machine.minsky_executor.CommandType;

public class TwoCMCommand {
    public CommandType type;

    public String startState;

    public TwoCMCommand(String startState) {
        this.startState = startState;
    }
}
