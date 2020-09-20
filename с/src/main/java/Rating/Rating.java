package Rating;

import Listeners.MenuMouseL;
import FileWorker.*;
import Window.Window;

import javax.swing.*;
import java.util.*;

public class Rating {
    public static Window myWindow;
    int frameWidth = 600;
    int frameHeight = 600;
    static LinkedList<RatingPair> ratingList;
    String[] columnNames;
    static String[][] data;
    static JTable table;

    public Rating(){
        myWindow = new Window("Tetris",40, 20, frameWidth, frameHeight, "#ffc8a8");
        JButton menu = myWindow.addButton("назад",40 ,520,140,30);
        myWindow.addLabel("TOP 5:", 50,10,250,100,"#ffa8b4", 54);
        menu.addMouseListener(new MenuMouseL(myWindow));
        myWindow.setVisible(false);
        ratingList = FileWorker.getRatingList();////
        data = new String[5][3];
        columnNames = new String[3];
        columnNames[0] = "Number";
        columnNames[1] = "Username";
        columnNames[2] =  "Score";
        for(int i = 0; i < 5; i++){
            data[i][0] = Integer.toString(i + 1);
            data[i][1] = ratingList.get(i).getUsername();
            data[i][2] = Integer.toString(ratingList.get(i).getScore());
        }
        table = myWindow.addTable(data, columnNames);
    }

    static void upgrade(){
        ratingList = FileWorker.getRatingList();
        for(int i = 0; i < 5; i++){
            data[i][0] = Integer.toString(i + 1);
            data[i][1] = ratingList.get(i).getUsername();
            data[i][2] = Integer.toString(ratingList.get(i).getScore());
        }
        myWindow.upgradeTable(table, data, 5, 2);
    }

    public static void addResult(int result, String name){
        RatingPair ratingPair = new RatingPair();
        ratingPair.setData(result, name);
        ratingList.add(ratingPair);
        Collections.sort(ratingList, new Comparator<RatingPair>() {////
            @Override
            public int compare(RatingPair x, RatingPair y) {
                if(x.getScore() <= y.getScore()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        });
        ratingList.remove(5);
        FileWorker.setRewriteRatingFile(ratingList);////
        upgrade();
    }
}
