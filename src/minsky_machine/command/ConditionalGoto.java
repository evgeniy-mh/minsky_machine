package minsky_machine.command;

import minsky_machine.Counter;

public class ConditionalGoto extends TwoCMCommand {
    Counter counterToCheck;
    public String nextState1;
    String nextState2;

    public ConditionalGoto(){
        super("");
    }

    public ConditionalGoto(String startState, Counter counterToCheck, String nextState1, String nextState2) {
        super(startState);
        this.counterToCheck = counterToCheck;
        this.nextState1 = nextState1;
        this.nextState2 = nextState2;
    }
}
