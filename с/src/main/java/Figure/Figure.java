package Figure;

import java.util.ArrayList;

public abstract class Figure {
    ArrayList<Block> blocks;
    int status;

    public Figure(){
        blocks = new ArrayList<Block>();
        status = 0;
    }


    public abstract void turnOver();

    public abstract ArrayList<Coord> coordinatesAfterTurning();

    public ArrayList<Block> getListOfBlocks(){
        return blocks;
    }

}
