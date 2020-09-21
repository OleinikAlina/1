import Commands.*;
import Exceptions.CalculatorEx;

import java.util.logging.Logger;

public class Calculator {
    String command;
    String[] substr;
    Command currentCommand;
    Context<Double> context;
    Factory factoryObj;
    private static final Logger logger = Logger.getLogger(Calculator.class.getName());

    public Calculator(){
        context = new Context<>(Double.class);
        factoryObj = new Factory();
    }

    public void action(String data) throws CalculatorEx {
        if (!data.contains("#")){
            substr = data.split("\\s+");
            command = substr[0];
            for (int i = 0; i < substr.length; i++) {
                System.out.print(substr[i] + "; i = " + i + " ");
            }
            System.out.println(" ");
            currentCommand = factoryObj.buildCommand(command);
            //System.out.println(currentCommand);
            currentCommand.doCommand(substr, context);
        }
    }
}
