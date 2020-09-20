package Listeners;

import Rating.UserName;
import Window.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserNameMouseL extends MouseAdapter {
    Window myWindow;
    public UserNameMouseL(Window myWindow){
        this.myWindow = myWindow;
    }

    @Override
    public void mouseClicked(MouseEvent event){
        new UserName();
        myWindow.dispose();
    }
}
