package Window;

import Listeners.ExitMouseL;
import Listeners.RatingMouseL;
import Listeners.UserNameMouseL;
import Rating.Rating;

import javax.swing.*;

public class Menu {
    public static Window myWindow;
    int frameWidth = 400;
    int frameHeight = 300;
    public Menu(){
        new Rating();
        myWindow = new Window("Tetris",40, 20, frameWidth, frameHeight, "#ffc8a8");
        JButton start = myWindow.addButton("начать игру",(frameWidth / 2) - (180 / 2) , 180,180,30);
        JButton rating = myWindow.addButton("рейтинг",40 ,220,140,30);
        JButton exit = myWindow.addButton("выход",frameWidth - 40 - 140 ,220,140,30);
        myWindow.addLabel("Tetris", 50,20,200,100, "#ffa8b4", 70);
        start.addMouseListener(new UserNameMouseL(myWindow));
        rating.addMouseListener(new RatingMouseL(myWindow));
        exit.addMouseListener(new ExitMouseL());
        myWindow.setVisible(true);
    }
}
