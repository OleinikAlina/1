package Commands;

import Exceptions.ArgumentException;
import Exceptions.CalculatorEx;

import java.util.EmptyStackException;

public class CommandMultiplication extends Command {
    public void doCommand(String[] arg,  Context<Double> context) throws CalculatorEx {
        double y;
        double x;
        if(1 != arg.length){
            throw new ArgumentException("too much arguments");
        }
        else{
            x = context.pop();
            try{
                y = context.pop();
                context.push(y * x);
            }catch(EmptyStackException ex){
                context.push(x);
                throw new ArgumentException("too few elements for division");
            }
        }
    }
}
