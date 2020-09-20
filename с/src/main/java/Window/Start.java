package Window;

import Listeners.MenuMouseL;
import Tetris.TetrisController.Controller;
import Tetris.TetrisModel.TetrisModel;
import Tetris.TetrisView.FigureField;
import Tetris.TetrisView.TetrisField;

import javax.swing.*;

public class Start {
    Window myWindow;
    int frameWidth = 550;
    int frameHeight = 708;
    FigureField figureField;
    TetrisField tetrisField;
    String username;

    public Start(){
        myWindow = new Window("Tetris",40, 20, frameWidth, frameHeight, "#ffc8a8");
        JButton menu = myWindow.addButton("назад",380 ,620,140,30);
        menu.addMouseListener(new MenuMouseL(myWindow));
        tetrisField = (TetrisField) myWindow.addElement(new TetrisField(myWindow));
        figureField = (FigureField) myWindow.addElement(new FigureField());
        myWindow.setVisible(true);
        username = "Username";
    }

    public void launch(String username){
        if(!username.equals("")){
            this.username = username;
        }
        TetrisModel tetrisModel = new TetrisModel(tetrisField, figureField, myWindow, this.username);
        tetrisModel.execute();
        new Controller(myWindow, tetrisModel);
    }
}
