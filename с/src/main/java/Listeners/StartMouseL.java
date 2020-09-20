package Listeners;

import Window.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class StartMouseL extends MouseAdapter {

    Window myWindow;
    public StartMouseL(Window myWindow){
        this.myWindow = myWindow;
    }

    @Override
    public void mouseClicked(MouseEvent event){
        new Start();
        myWindow.dispose();
    }
}
