package Commands;

import Exceptions.ArgumentException;
import Exceptions.CalculatorEx;
import Exceptions.StackException;

public class CommandDefine extends Command {
    public void doCommand(String[] arg,  Context<Double> context) throws CalculatorEx {
        if(3 != arg.length){
            throw new ArgumentException("too much(few) arguments");
        }
        if(-1 == context.addDefinition(arg[1], arg[2])){
            throw new StackException("redefinition");
        }
    }
}
