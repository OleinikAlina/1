package Commands;

import Exceptions.ArgumentException;
import Exceptions.ArithmeticException;
import Exceptions.CalculatorEx;

public class CommandSqrt extends Command {
    public void doCommand(String[] arg,  Context<Double> context) throws CalculatorEx {
        if(1 != arg.length){
            throw new ArgumentException("too much arguments");
        }
        else{
            double x = context.pop();
            if(x < 0){
                context.push(x);
                throw new ArithmeticException("can't get root of a negative number");
            }
            else{
                context.push(Math.sqrt(x));
            }
        }
    }
}
