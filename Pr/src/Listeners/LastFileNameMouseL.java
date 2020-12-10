package Listeners;

import Rating.LastFileName;
import Window.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LastFileNameMouseL extends MouseAdapter {
    Window myWindow;
    public LastFileNameMouseL(Window myWindow){
        this.myWindow = myWindow;
    }

    @Override
    public void mouseClicked(MouseEvent event){
        new LastFileName();
        //myWindow.dispose();
    }
}
