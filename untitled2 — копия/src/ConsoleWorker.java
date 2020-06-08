import Exceptions.CalculatorEx;
import Exceptions.ExitException;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ConsoleWorker {
    Scanner in;
    Calculator calculatorObj;
    private static final Logger logger = Logger.getLogger(Calculator.class.getName());

    public ConsoleWorker(){
        in = new Scanner(System.in);
        calculatorObj = new Calculator();
    }

    public void startWorkingWithConsole(){
        do{
            try {
                calculatorObj.action(in.nextLine());
            }
            catch (ExitException ex){
                logger.fine("exit");
                System.out.println(ex.getMessage());
                break;
            }
            catch (CalculatorEx ex ){
                logger.fine("Exceptions.CalculatorEx");
                System.out.println(ex.getMessage());
            }
            catch (EmptyStackException ex){
                logger.fine("EmptyStackException");
                System.out.println("Stack is empty");
            }
        }while(true);
    }
}
