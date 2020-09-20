package Tetris.TetrisView;

import Window.Window;

import javax.swing.*;

public class ScoreField {
    Window myWindow;
    JLabel label;
    public ScoreField(Window myWindow){
        this.myWindow = myWindow;
        this.myWindow.addLabel("Score:", 375, 5, 100, 100, "#ff0000",30);
        label = this.myWindow.addLabel("0",370, 40, 100, 100, "#ff0000",40);
    }

    public void updateScore(int score){
        myWindow.updateLabel(label, Integer.toString(score));
    }
}
