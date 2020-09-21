package Commands;

import Exceptions.ArgumentException;
import Exceptions.CalculatorEx;

public class CommandPush extends Command{
    public void doCommand(String[] arg,  Context<Double> context) throws CalculatorEx {
        if(2 != arg.length){
            throw new ArgumentException("too much(few) arguments");
        }
        context.push(arg[1]);
    }
}

