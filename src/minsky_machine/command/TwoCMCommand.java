package minsky_machine.command;

import minsky_machine.CommandType;

public class TwoCMCommand {
    public CommandType type;

    public String startState;

    public TwoCMCommand(String startState) {
        this.startState = startState;
    }
}
