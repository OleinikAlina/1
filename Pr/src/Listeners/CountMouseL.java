package Listeners;

import Rating.Count;
import Window.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CountMouseL extends MouseAdapter {
    Window myWindow;
    public CountMouseL(Window myWindow){
        this.myWindow = myWindow;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Count.myWindow.setVisible(true);
        myWindow.dispose();
    }
}
