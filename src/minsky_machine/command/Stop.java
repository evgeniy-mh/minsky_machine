package minsky_machine.command;

public class Stop extends TwoCMCommand {
    public Stop(){
        super("");
    }

    public Stop(String startState) {
        super(startState);
    }

    @Override
    public String toString() {
        return String.format("%s→|", this.startState);
    }
}
