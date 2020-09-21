import Commands.*;
import Exceptions.CommandException;
import Exceptions.ExitException;
import Exceptions.CalculatorEx;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

public class Factory {
    HashMap<String, String> correspondence;
    private static final Logger logger = Logger.getLogger(Calculator.class.getName());

    public Factory(){
        correspondence = new HashMap<>();
        File file = new File(FileWorker.path, FileWorker.commandFileName);
        try {
            FileReader fr = new FileReader(file);
            Scanner scan = new Scanner(fr);
            String data;
            String[] substr;
            while(scan.hasNextLine()){
                data = scan.nextLine();
                substr = data.split("\\s+");
                try {
                    if (substr.length != 2) {
                        throw new CommandException("too few(much) arguments");
                    }
                    correspondence.put(substr[0], substr[1]);
                }catch (CommandException e){
                    logger.fine("File Problem");
                    System.out.println(e.getMessage());
                }
            }
        }catch(IOException e){
            logger.severe("bad things");
            throw new RuntimeException(e);
        }
    }

    public Command buildCommand(String command) throws CalculatorEx {
        Command currentCommand = null;
        String correspondingCommand = correspondence.get(command);
        if(command.equalsIgnoreCase("exit")){
            throw new ExitException("_exit");
        }else {
            try {
                Object obj = Class.forName(correspondingCommand).newInstance();
                currentCommand = (Command) obj;
            } catch (NullPointerException | ClassNotFoundException ex) {
                throw new CommandException("Commands.Command " + command  + " not exist");
            }
            catch (InstantiationException | IllegalAccessException ex){
                throw new CommandException("problem with newInstance");
            }
        }
        return currentCommand;
    }
}
