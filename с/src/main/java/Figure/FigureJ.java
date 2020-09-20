package Figure;

import java.awt.*;
import java.util.ArrayList;

public class FigureJ extends Figure{
    public FigureJ(){
        blocks.add(new Block(4,0, Color.decode("#c8a8ff")));
        blocks.add(new Block(4,1, Color.decode("#c8a8ff")));
        blocks.add(new Block(5,1, Color.decode("#c8a8ff")));
        blocks.add(new Block(6,1, Color.decode("#c8a8ff")));
    }

    public ArrayList<Coord> coordinatesAfterTurning(){
        ArrayList<Coord> list = new ArrayList<Coord>();
        int tmp = (status + 1) % 4;
        switch (tmp){
            case 0:
                list.add(new Coord((blocks.get(0).getX()), blocks.get(0).getY() - 2));
                list.add(new Coord((blocks.get(1).getX() - 1), blocks.get(1).getY() - 1));
                list.add(new Coord((blocks.get(2).getX()), blocks.get(2).getY()));
                list.add(new Coord((blocks.get(3).getX() + 1), blocks.get(3).getY() + 1));
                break;
            case 1:
                list.add(new Coord((blocks.get(0).getX() + 2), blocks.get(0).getY()));
                list.add(new Coord((blocks.get(1).getX() + 1), blocks.get(1).getY() - 1));
                list.add(new Coord((blocks.get(2).getX()), blocks.get(2).getY()));
                list.add(new Coord((blocks.get(3).getX() - 1), blocks.get(3).getY() + 1));
                break;
            case 2:
                list.add(new Coord((blocks.get(0).getX()), blocks.get(0).getY() + 2));
                list.add(new Coord((blocks.get(1).getX() + 1), blocks.get(1).getY() + 1));
                list.add(new Coord((blocks.get(2).getX()), blocks.get(2).getY()));
                list.add(new Coord((blocks.get(3).getX() - 1), blocks.get(3).getY() - 1));
                break;
            case 3:
                list.add(new Coord((blocks.get(0).getX() - 2), blocks.get(0).getY()));
                list.add(new Coord((blocks.get(1).getX() - 1), blocks.get(1).getY() + 1));
                list.add(new Coord((blocks.get(2).getX()), blocks.get(2).getY()));
                list.add(new Coord((blocks.get(3).getX() + 1), blocks.get(3).getY() - 1));
                break;
        }
        return list;
    }


    public void turnOver(){
        turn();
    }

    public void turn(){
        ArrayList<Coord> list = coordinatesAfterTurning();
        status = (status + 1) % 4;
        for(int i = 0; i < list.size(); i++){
            blocks.get(i).setX(list.get(i).getX());
            blocks.get(i).setY(list.get(i).getY());
        }
    }
}
