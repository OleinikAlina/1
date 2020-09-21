package Commands;

import Exceptions.ArgumentException;
import Exceptions.CalculatorEx;

public class CommandPrint extends Command {
    public void doCommand(String[] arg,  Context<Double> context) throws CalculatorEx {
        if(1 != arg.length){
            throw new ArgumentException("too much arguments");
        }
        double x = context.pop();
        System.out.println(x);
        context.push(x);
    }
    public static double getValue(Context<Double> context){
        double x = context.pop();
        context.push(x);
        return x;
    }
}
