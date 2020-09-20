package Tetris.TetrisModel;

import Figure.*;
import Rating.Rating;
import Tetris.TetrisView.FigureField;
import Tetris.TetrisView.ScoreField;
import Tetris.TetrisView.TetrisField;
import Window.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TetrisModel extends SwingWorker<Integer, Integer> {
    TetrisField tetrisField;
    FigureField figureField;
    public static Figure currentFigure;
    public static Figure nextFigure;
    Factory factoryObj;
    static public boolean gameIsOver;
    boolean figureIsFixed;
    Window myWindow;
    final int LEFT = -1;
    final int RIGHT = 1;
    public boolean isDownPressed;
    public boolean isUpPressed;
    public boolean isLeftPressed;
    public boolean isRightPressed;
    public boolean isPause;
    int score;
    String username;
    ScoreField scoreField;
    Timer timer1;

    public TetrisModel(TetrisField tetrisField, FigureField figureField, Window myWindow, String username){
        this.username = username;
        this.tetrisField = tetrisField;
        this.figureField = figureField;
        this.myWindow = myWindow;
        scoreField = new ScoreField(myWindow);
        gameIsOver = false;
        figureIsFixed = false;
        factoryObj = new Factory();
        isDownPressed = false;
        isRightPressed = false;
        isPause = false;
        isLeftPressed = false;
        isUpPressed = false;
        score = 0;
    }

    public Integer doInBackground() {
        int delay1 = 300;
        int delay2 = 50;
        timer1 = new Timer(delay1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown();
            }
        });
        myWindow.update(myWindow.getGraphics());
        currentFigure = factoryObj.buildFigure();
        timer1.start();
        while(!gameIsOver){
            figureIsFixed = false;
            nextFigure = factoryObj.buildFigure();
            figureField.refresh();
            figureField.update(figureField.getGraphics());
            tetrisField.refresh();
            tetrisField.update(tetrisField.getGraphics());
            while(true){
                score += 100 * tetrisField.checkLine();
                scoreField.updateScore(score);
                while(isPause){
                    tetrisField.setVisible(false);
                    timer1.stop();
                }
                tetrisField.setVisible(true);
                if(figureIsFixed){
                    break;
                }
                tetrisField.update(tetrisField.getGraphics());
                if(!isDownPressed) {
                    timer1.setDelay(delay1);
                }else{
                    isDownPressed = false;
                    timer1.setDelay(delay2);
                }
                if(!timer1.isRunning()) {
                    timer1.start();
                }
            }
            try {
                currentFigure = (Figure) Class.forName(nextFigure.getClass().getName()).newInstance();
            }catch (NullPointerException | IllegalAccessException | InstantiationException |ClassNotFoundException ex){
                throw new RuntimeException(ex);
            }
        }
        timer1.stop();
        Rating.addResult(score, username);
        return 42;
    }

    public void done(){
        tetrisField.refresh();
    }


    void gameOver(){
        for(Block block : currentFigure.getListOfBlocks()){
            block.fixBlock();
        }
        figureIsFixed = true;
        gameIsOver = true;
    }

    void moveDown(){
        if(canFigureMoveDown()) {
            for (Block block : currentFigure.getListOfBlocks()) {
                block.setY(block.getY() + 1);
            }
        }
        else{
            for (Block block : currentFigure.getListOfBlocks()) {
                block.fixBlock();
            }
            figureIsFixed = true;
        }
        if(!tetrisField.refresh()){
            gameOver();
        }
    }

    public void boost(){
        isDownPressed = true;
    }

    public void pause(){
        isPause = !isPause;
    }

    public void moveLeft(){
        if(canFigureMoveSideward(LEFT)){
            for (Block block : currentFigure.getListOfBlocks()) {
                block.setX(block.getX() - 1);
            }
        }
        if(!tetrisField.refresh()){
            gameOver();
        }
    }

    public void moveRight(){
        if(canFigureMoveSideward(RIGHT)){
            for (Block block : currentFigure.getListOfBlocks()) {
                block.setX(block.getX() + 1);
            }
        }
        if(!tetrisField.refresh()){
            gameOver();
        }
    }

    public void turnAround(){
        if(canFigureMoveAround()){
            currentFigure.turnOver();
            if(!tetrisField.refresh()){
                gameOver();
            }
        }

    }

    boolean canFigureMoveAround(){
        ArrayList<Coord> list = currentFigure.coordinatesAfterTurning();
        for(Coord pair : list){
            if(pair.getX() < 0
                    || pair.getX() >= tetrisField.amountOfCols
                    || pair.getY() < 0
                    || pair.getY() >= tetrisField.amountOfRows
                    || tetrisField.field[pair.getY()][pair.getX()].getImmobilityStatus()){
                return false;
            }
        }
        return true;
    }

    boolean canFigureMoveDown(){
        for(Block block : currentFigure.getListOfBlocks()){
            if(block.getY() + 1 >= tetrisField.amountOfRows ||
                    tetrisField.field[block.getY() + 1][block.getX()].getImmobilityStatus()){
                return false;
            }
        }
        return true;
    }

    boolean canFigureMoveSideward(int direction){
        for(Block block : currentFigure.getListOfBlocks()){
            if(block.getX() + direction < 0 || block.getX() + direction >= tetrisField.amountOfCols){
                return false;
            }
            else{
                if(tetrisField.field[block.getY()][block.getX() + direction].getImmobilityStatus()){//в else потому, что если == 0, будет плохо
                    return false;
                }
            }
        }
        return true;
    }

}

