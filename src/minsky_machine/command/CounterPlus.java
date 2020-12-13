package minsky_machine.command;

import minsky_machine.Counter;

public class CounterPlus extends TwoCMCommand {
    Counter counter;
    String nextState;

    public CounterPlus(String startState, Counter counter, String nextState) {
        super(startState);
        this.counter = counter;
        this.nextState = nextState;
    }

    @Override
    public String toString() {
        return this.startState+this.counter+"+ -> "+this.nextState;
    }
}
