package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitMouseL extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent event) {
        System.exit(0);
    }
}
