package Tetris.TetrisView;

import Figure.Block;
import Figure.Figure;
import Tetris.TetrisModel.TetrisModel;
import Window.Window;

import javax.swing.*;
import java.awt.*;

public class TetrisField extends JPanel {
    public static final int amountOfCols = 10;
    public static final int amountOfRows = 20;
    int sizeOfBlock = 34;
    public static Block[][] field;
    Window myWindow;

    public TetrisField(Window window){
        myWindow = window;
        field = new Block[amountOfRows][amountOfCols];
        for(int i = 0; i < amountOfRows; i++){
            for(int j = 0; j < amountOfCols; j++){
                field[i][j] = new Block(i, j);
            }
        }
        super.setBounds(0 ,0, 340,708);
        super.setVisible(true);
        repaint();
    }

    public boolean refresh(){
        if(TetrisModel.gameIsOver){
            myWindow.addLabel("Game over", 50, 10, 350, 300, "#ff0000", 55);
            return true;
        }else {
            return changePositionOfTheFigure(TetrisModel.currentFigure);
        }
    }

    public boolean changePositionOfTheFigure(Figure figure){
        boolean status = true;
        for(int i = 0; i < amountOfRows; i++){
            for(int j = 0; j < amountOfCols; j++){
                if(!field[i][j].getImmobilityStatus()) {
                    field[i][j].toEmptyBlock();
                }
            }
        }
        for(Block block : figure.getListOfBlocks()){
            if(!field[block.getY()][block.getX()].getImmobilityStatus()) {
                field[block.getY()][block.getX()] = new Block(block);
            }
            else{
                field[block.getY()][block.getX()].setColor(Color.lightGray);
                status = false;
            }
        }
        repaint();
        return status;
    }

    public int checkLine(){
        int multiplier = 0;
        int controlLine;
        for(int i = 0; i < amountOfRows; i++){
            controlLine = amountOfCols;
            for(int j = 0; j < amountOfCols; j++){
                if(field[i][j].getImmobilityStatus()) {
                    controlLine--;
                }
            }
            if(controlLine == 0){
                multiplier++;
                deleteLine(i);
                i--;
            }
        }
        return multiplier * multiplier;
    }

    void deleteLine(int numberOfRow){
        if(numberOfRow > 0) {
            for (int i = 0; i < amountOfCols; i++) {
                field[numberOfRow][i].toEmptyBlock();
            }
        }
        for(int i = numberOfRow - 1; i >= 0; i--){
            for(int j = 0; j < amountOfCols; j++){
                field[i][j].setY(field[i][j].getY() + 1);
                field[i + 1][j] = new Block(field[i][j]);
            }
        }
    }

    @Override
    public void paint(Graphics g){
        for(int i = 0; i < amountOfCols; i++){
            for(int j = 0; j < amountOfRows; j++){
                g.setColor(field[j][i].getColor());
                g.fill3DRect(i * sizeOfBlock ,j * sizeOfBlock, sizeOfBlock, sizeOfBlock, true);
            }
        }
    }
}
