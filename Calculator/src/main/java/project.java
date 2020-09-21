
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class project {
    private static final Logger logger = Logger.getLogger(Calculator.class.getName());

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int num;
        FileWorker.setPathAndCommandFileName(args[0], args[1]);
        try{
            LogManager.getLogManager().readConfiguration(project.class.getResourceAsStream("/log.properties"));
        }catch (IOException ex){
            System.err.println("Not found logger");
        }
        do{
            System.out.println("1 = console input; 2 - file input");
            while(!in.hasNextInt()){
                System.out.println("bad type");
                in.next();
                System.out.println("&&&&&");
            }
            num = in.nextInt();
            if(num != 1 && num != 2){
                System.out.println("bad value");
            }
        }while(num != 1 && num != 2);
        if (num == 2) {
            FileWorker fileObj = new FileWorker();
            fileObj.setFileName(args[2]);
            fileObj.readAndWork();
        } else {
                ConsoleWorker consoleObj = new ConsoleWorker();
                consoleObj.startWorkingWithConsole();
        }
    }
}
