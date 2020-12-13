package minsky_machine.command;

public class Goto extends TwoCMCommand {
    String nextState;

    public  Goto(){
        super("");
    }

    public Goto(String startState, String nextState) {
        super(startState);
        this.nextState = nextState;
    }

    @Override
    public String toString() {
        return String.format("%s→%s", this.startState, this.nextState);
    }
}
