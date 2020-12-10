package Rating;


import javax.swing.*;

import Window.Start;

import java.awt.*;

public class LastFileName {


    public LastFileName() {
        String fileName = JOptionPane.showInputDialog("Введите имя файла");
        while(fileName.equals("")){//если файл не найден
            fileName = JOptionPane.showInputDialog("Файл не найден, попробуйте еще раз");
        }
        int result = JOptionPane.showConfirmDialog(null, "Хотите открыть файл?");
        if (result == 0){
            Start start = new Start();
            start.launch(fileName);
        }
    }
}
