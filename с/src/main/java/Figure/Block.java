package Figure;

import java.awt.*;

public class Block {
    int x;
    int y;
    boolean fillStatus;
    boolean immobilityStatus;
    Color color;

    public Block(int x, int y){
        this.x = x;
        this.y = y;
        fillStatus = false;
        immobilityStatus = false;
        color = Color.decode("#fff4a8");
    }


    public Block(Block other){
        this.x = other.x;
        this.y = other.y;
        this.fillStatus = other.fillStatus;
        this.immobilityStatus = other.immobilityStatus;
        this.color = other.color;
    }

    public Block(int x, int y, Color color){
        this.x = x;
        this.y = y;
        fillStatus = true;
        immobilityStatus = false;
        this.color = color;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean getFillStatus(){
        return fillStatus;
    }

    public boolean getImmobilityStatus(){
        return immobilityStatus;
    }

    public void fixBlock(){
        immobilityStatus = true;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void fillBlock(Color color){
        fillStatus = true;
        this.color = color;
    }

    public void toEmptyBlock(){
        fillStatus = false;
        immobilityStatus = false;
        color = Color.decode("#fff4a8");
    }


}
