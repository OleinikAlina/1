package Commands;

import Exceptions.ArgumentException;
import Exceptions.CalculatorEx;

public class CommandPop extends Command {
    public void doCommand(String[] arg,  Context<Double> context) throws CalculatorEx {
        if(1 != arg.length){
            throw new ArgumentException("too much arguments");
        }
        context.pop();
    }
}
