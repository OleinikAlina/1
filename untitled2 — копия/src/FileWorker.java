import Exceptions.CalculatorEx;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileWorker {
    static String path;
    static String commandFileName;
    String fileName;
    Calculator calculatorObj;
    private static final Logger logger = Logger.getLogger(Calculator.class.getName());

    public FileWorker(){
        calculatorObj = new Calculator();
    }


    public static void setPathAndCommandFileName(String path, String commandFileName){
        FileWorker.path = path;
        FileWorker.commandFileName = commandFileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }


    public void readAndWork(){
        File file = new File(path,fileName);
        try{
            FileReader fr = new FileReader(file);
            Scanner scan = new Scanner(fr);
            while(scan.hasNextLine()){
                try {
                    calculatorObj.action(scan.nextLine());
                }
                catch(CalculatorEx ex ){
                    System.out.println(ex.getMessage());
                }
                catch(EmptyStackException ex){
                    System.out.println("Stack is empty");
                }
            }
            scan.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
