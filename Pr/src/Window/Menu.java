package Window;

import Listeners.ExitMouseL;
import Listeners.RatingMouseL;
import Listeners.LastFileNameMouseL;
import Listeners.CountMouseL;
import Listeners.DownloadMouseL;

import javax.swing.*;
import java.awt.*;

public class Menu {
    public static Window myWindow;
    public Menu(){
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        myWindow = new Window("Главная",sSize.width/15, sSize.height/15, 5*sSize.width/7, 5*sSize.height/7, "#ffc8a8");
        JButton teach = myWindow.addButton("поиск файла, который потом смотреть",sSize.width/12, 20*sSize.height/63,300,30);
        JButton watch = myWindow.addButton("Посмотреть список просмотренных файлов",sSize.width/12 ,5*sSize.height/21,300,30);
        JButton count = myWindow.addButton("Количество просмотров",5*sSize.width/12 ,20*sSize.height/63,300,30);
        JButton download = myWindow.addButton("Загрузить",5*sSize.width/12 ,5*sSize.height/21,300,30);
        JButton exit = myWindow.addButton("выход",4*sSize.width/7,4*sSize.height/7,140,30);
        myWindow.addLabel("Название платформы", 5*sSize.width/49,15,800,200, "#ffa8b4", 70);
        teach.addMouseListener(new LastFileNameMouseL(myWindow));
        watch.addMouseListener(new RatingMouseL(myWindow));
        count.addMouseListener(new CountMouseL(myWindow));
        download.addMouseListener(new DownloadMouseL(myWindow));
        exit.addMouseListener(new ExitMouseL());
        myWindow.setVisible(true);
    }
}
