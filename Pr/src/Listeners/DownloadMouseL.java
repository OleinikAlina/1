package Listeners;

import Window.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DownloadMouseL extends MouseAdapter {
    Window myWindow;
    public DownloadMouseL(Window myWindow){
        this.myWindow = myWindow;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        String fileName = JOptionPane.showInputDialog("Введите полное имя файла");
        //передается в некоторую функцию
        String newNameFile = "new name!";// получаем новое имя файла
        JOptionPane.showMessageDialog(null, newNameFile);
        //myWindow.dispose();
    }
}
