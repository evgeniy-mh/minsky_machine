package minsky_machine.minsky_executor;

import javafx.beans.property.SimpleIntegerProperty;
import minsky_machine.command.ConditionalGoto;
import minsky_machine.command.CounterPlus;
import minsky_machine.command.Goto;
import minsky_machine.command.TwoCMCommand;

import java.util.ArrayList;
import java.util.List;

public class ProgramExecutor {
    public List<TwoCMCommand> program;
    public String startState;

    public SimpleIntegerProperty AValue = new SimpleIntegerProperty();
    public SimpleIntegerProperty BValue = new SimpleIntegerProperty();
    public String currentState;
    public List<String> executionHistory = new ArrayList<>();
    public OnStop onStopCallback;

    public void Run(){
        currentState = startState;
        executionHistory.clear();
        boolean stop=false;
        while (!stop){
            TwoCMCommand currentCommand=program.stream().filter(c->c.startState.equals(currentState)).findFirst().orElse(null);
            executionHistory.add(String.format(
                    "Состояние %s A:%s, B:%s",
                    currentState,
                    AValue.get(),
                    BValue.get())
            );

            System.out.println("Executing: "+currentState);
            switch (currentCommand.type){
                case Counter_plus:
                    switch (((CounterPlus)currentCommand).counter){
                        case A:
                            AValue.set(AValue.get() + 1);
                            break;
                        case B:
                            BValue.set(BValue.get() + 1);
                            break;
                    }
                    this.currentState=((CounterPlus)currentCommand).nextState;
                    break;
                case Conditional_goto:
                    switch (((ConditionalGoto)currentCommand).counterToCheck){
                        case A:
                            if (AValue.get() > 0) {
                                AValue.set(AValue.get() - 1);
                                currentState=((ConditionalGoto)currentCommand).nextState1;
                            }else{
                                currentState=((ConditionalGoto)currentCommand).nextState2;
                            }
                            break;
                        case B:
                            if (BValue.get() > 0) {
                                BValue.set(BValue.get() - 1);
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
        onStopCallback.onStop();
    }
}
