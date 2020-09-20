package Rating;


import javax.swing.*;
import Window.Start;

public class UserName {


    public UserName() {
        Start start = new Start();
        String username = JOptionPane.showInputDialog("Введите имя");
        start.launch(username);
    }
}
