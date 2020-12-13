package minsky_machine.command;

import minsky_machine.Counter;

public class CounterPlus extends TwoCMCommand {
    Counter counter;
    String nextState;

    public CounterPlus(){
        super("");
    }

    public CounterPlus(String startState, Counter counter, String nextState) {
        super(startState);
        this.counter = counter;
        this.nextState = nextState;
    }

    @Override
    public String toString() {
        return String.format("%s%s+→%s", this.startState, this.counter, this.nextState);
    }
}
