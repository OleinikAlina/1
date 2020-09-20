package Tetris.TetrisController;

import Tetris.TetrisModel.TetrisModel;
import Window.Window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Controller {
    public Controller(Window myWindow, TetrisModel tetrisModel){
        myWindow.requestFocus();
        myWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    tetrisModel.turnAround();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    tetrisModel.boost();
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    tetrisModel.moveLeft();
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    tetrisModel.moveRight();
                }
                if(e.getKeyCode() == KeyEvent.VK_P){
                    tetrisModel.pause();
                }
            }
        });
    }
}
