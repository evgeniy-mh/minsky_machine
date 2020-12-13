package minsky_machine.command;

import minsky_machine.Counter;

public class ConditionalGoto extends TwoCMCommand {
    public Counter counterToCheck;
    public String nextState1;
    public String nextState2;

    public ConditionalGoto(){
        super("");
    }

    public ConditionalGoto(String startState, Counter counterToCheck, String nextState1, String nextState2) {
        super(startState);
        this.counterToCheck = counterToCheck;
        this.nextState1 = nextState1;
        this.nextState2 = nextState2;
    }

    @Override
    public String toString() {
        return String.format("%s(%s>0)?(%s-;→%s):(→%s)",
                this.startState,
                this.counterToCheck,
                this.counterToCheck,
                this.nextState1,
                this.nextState2);
    }
}
