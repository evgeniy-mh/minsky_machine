package minsky_machine;

import minsky_machine.command.ConditionalGoto;
import minsky_machine.command.CounterPlus;
import minsky_machine.command.Goto;
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
            System.out.println("Executing: "+currentState);
            switch (currentCommand.type){
                case Counter_plus:
                    switch (((CounterPlus)currentCommand).counter){
                        case A:
                            AValue++;
                            break;
                        case B:
                            BValue++;
                            break;
                    }
                    this.currentState=((CounterPlus)currentCommand).nextState;
                    break;
                case Conditional_goto:
                    switch (((ConditionalGoto)currentCommand).counterToCheck){
                        case A:
                            if(AValue>0){
                                AValue--;
                                currentState=((ConditionalGoto)currentCommand).nextState1;
                            }else{
                                currentState=((ConditionalGoto)currentCommand).nextState2;
                            }
                            break;
                        case B:
                            if(BValue>0){
                                BValue--;
                                currentState=((ConditionalGoto)currentCommand).nextState1;
                            }else{
                                currentState=((ConditionalGoto)currentCommand).nextState2;
                            }
                            break;
                    }
                    break;
                case Goto:
                    currentState=((Goto)currentCommand).nextState;
                    break;
                case Stop:
                    stop=true;
                    break;
            }
        }
    }
}
