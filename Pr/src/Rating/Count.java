package Rating;

import Listeners.MenuMouseL;
import Window.Window;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Count {
    public static Window myWindow;
    //static LinkedList<RatingPair> ratingList;
    String[] columnNames;
    static String[][] data;
    static JTable table;
    static File fileOfDoc;

    public Count(){
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        fileOfDoc = new File("C:\\Users\\oleyn\\IdeaProjects\\tetr", "rat.txt");
        myWindow = new Window("Главная",sSize.width/15, sSize.height/15, 5*sSize.width/7, 5*sSize.height/7, "#ffc8a8");
        JButton menu = myWindow.addButton("назад",40 ,520,140,30);
        myWindow.addLabel("Последние просмотренные файлы:", 50,8,1000,100,"#ffa8b4", 54);
        menu.addMouseListener(new MenuMouseL(myWindow));
        myWindow.setVisible(false);
        //ratingList = FileWorker.getRatingList();////
        data = new String[10][1];
        columnNames = new String[1];
        columnNames[0] = "Number";
        try{
            FileReader fr = new FileReader(fileOfDoc);
            Scanner scan = new Scanner(fr);
            for(int i = 0; i < 10; i++){
                if(scan.hasNextLine())
                    data[i][0] = scan.nextLine();
                else
                    data[i][0] = " ";
            }
        }catch(IOException | NumberFormatException e){
            throw new RuntimeException(e);
        }
        table = myWindow.addTable(data, columnNames);
    }
}
