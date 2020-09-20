package Tetris.TetrisView;

import Figure.Block;
import Figure.Figure;
import Tetris.TetrisModel.TetrisModel;

import javax.swing.*;
import java.awt.*;

public class FigureField extends JPanel {
    final int amountOfCols = 4;
    final int amountOfRows = 4;
    final int sizeOfBlock = 34;

    Block[][] field;


    public FigureField(){
        field = new Block[amountOfRows][amountOfCols];
        for(int i = 0; i < amountOfRows; i++){
            for(int j = 0; j < amountOfCols; j++){
                field[i][j] = new Block(i, j);
            }
        }
        super.setBounds(380 ,200, 136,136);
        super.setVisible(true);
        repaint();
    }

    public void refresh(){
        changeFigure(TetrisModel.nextFigure);
    }

    public void changeFigure(Figure figure){
        for(int i = 0; i < amountOfRows; i++){
            for(int j = 0; j < amountOfCols; j++){
                field[i][j].toEmptyBlock();
            }
        }
        for(Block block : figure.getListOfBlocks()){
            field[block.getY()][block.getX() - 4] = block;
        }
        repaint();
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
