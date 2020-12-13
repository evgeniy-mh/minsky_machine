package minsky_machine.command;

public class Goto extends TwoCMCommand {
    String nextState;

    public Goto(String startState, String nextState) {
        super(startState);
        this.nextState = nextState;
    }
}
