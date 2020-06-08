import Commands.*;
import Exceptions.CalculatorEx;
import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class JUnit extends Assert {
    Context<Double> context;
    String data;
    String command;
    String[] substr;
    Command currentCommand;
    Factory factoryObj;

    public JUnit(){
        context = new Context<>(Double.class);
        factoryObj = new Factory();
    }

    void act() throws CalculatorEx {
        substr = data.split("\\s+");
        command = substr[0];
        currentCommand = factoryObj.buildCommand(command);
        currentCommand.doCommand(substr, context);
    }

    @Test
    public void test(){
        try {
            data = "DEFINE a 9";
            act();
            data = "PUSH a";
            act();
            assertEquals(9.0, CommandPrint.getValue(context), 0);
            data = "PUSH 7";
            act();
            assertEquals(7.0, CommandPrint.getValue(context), 0);
            data = "*";
            act();
            assertEquals(63.0, CommandPrint.getValue(context), 0);
            data = "PUSH 1";
            act();
            data = "+";
            act();
            assertEquals(64.0, CommandPrint.getValue(context), 0);
            data = "SQRT";
            act();
            assertEquals(8.0, CommandPrint.getValue(context), 0);

        }
        catch (CalculatorEx ex ){
            System.out.println(ex.getMessage());
        }
        catch (EmptyStackException ex){
            System.out.println("Stack is empty");
        }
    }
}
