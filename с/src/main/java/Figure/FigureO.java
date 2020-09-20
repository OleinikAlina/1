package Figure;

import java.awt.*;
import java.util.ArrayList;


public class FigureO extends Figure{
    public FigureO(){
        blocks.add(new Block(4,0, Color.decode("#ff8fd5")));
        blocks.add(new Block(4,1, Color.decode("#ff8fd5")));
        blocks.add(new Block(5,0, Color.decode("#ff8fd5")));
        blocks.add(new Block(5,1, Color.decode("#ff8fd5")));
    }

    public ArrayList<Coord> coordinatesAfterTurning(){
        ArrayList<Coord> list = new ArrayList<Coord>();
        for(int i = 0; i < 4; i++){
            list.add(new Coord((blocks.get(i).getX()), blocks.get(i).getY()));
        }
        return list;
    }


    public void turnOver(){
        turn();
    }

    public void turn(){
    }
}
