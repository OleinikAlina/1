package Listeners;

import Rating.Rating;
import Window.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RatingMouseL extends MouseAdapter {
    Window myWindow;
    public RatingMouseL(Window myWindow){
        this.myWindow = myWindow;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        new Rating();
        myWindow.dispose();
    }
}