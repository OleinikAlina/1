package Window;

import Listeners.MenuMouseL;

import javax.swing.*;
import java.awt.*;

public class Start {
    Window myWindow;
    String fileName;

    public Start(){
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        myWindow = new Window("Tetris",0, 0, sSize.width, sSize.height, "#ffc8a8");
        JButton menu = myWindow.addButton("назад",sSize.width-200,sSize.height-150,140,30);
        menu.addMouseListener(new MenuMouseL(myWindow));
        myWindow.setVisible(true);
        fileName = "Username";
    }

    public void launch(String fileName){
        if(!fileName.equals("")){
            this.fileName = fileName;
        }
        //вывод файла на экран
        //tetrisModel.execute();
        //new Controller(myWindow, tetrisModel);
    }
}
