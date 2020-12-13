package minsky_machine;

import minsky_machine.command.TwoCMCommand;

import java.util.List;

public class ProgramExecutor {
    private List<TwoCMCommand> program;
    private int startAValue;
    private int startBValue;
    private String startState;

    public int AValue;
    public int BValue;
    public String currentState;


    public ProgramExecutor(List<TwoCMCommand> program, int startAValue, int startBValue, String startState) {
        this.program = program;
        this.startAValue = startAValue;
        this.startBValue = startBValue;
        this.AValue=startAValue;
        this.BValue=startBValue;
        this.startState=startState;
        this.currentState=startState;
    }

    public void Run(){
        boolean stop=false;
        while (!stop){

            TwoCMCommand currentCommand=program.stream().filter(c->c.startState.equals(currentState)).findFirst().orElse(null);

            switch (currentCommand.type){
                case Counter_plus:
//                    switch (((CounterPlus)currentCommand).counter){
//                        case A:
//                            AValue++;
//                            break;
//                        case B:
//                            BValue++;
//                            break;
//                    }
//                    this.currentState=((CounterPlus)currentCommand).nextState;
                    break;
                case Conditional_goto:
                    break;
                case Goto:
                    break;
                case Stop:
                    break;
            }

            stop=true;
        }
    }
}
