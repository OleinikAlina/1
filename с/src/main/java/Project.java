import FileWorker.FileWorker;
import Window.Menu;

public class Project {
    public static void main(String[] args){
        FileWorker.setPathAndFileName(args[0], args[1]);
        new Menu();
    }
}
