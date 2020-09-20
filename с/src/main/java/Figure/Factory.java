package Figure;

import java.util.HashMap;
import java.util.Random;


public class Factory {
    HashMap<Integer, String> correspondence;
    int count;
    Random random;

    public Factory(){
        random = new Random();
        correspondence = new HashMap<>();
        correspondence.put(0, "Figure.FigureI");
        correspondence.put(1, "Figure.FigureJ");
        correspondence.put(2, "Figure.FigureL");
        correspondence.put(3, "Figure.FigureO");
        correspondence.put(4, "Figure.FigureS");
        correspondence.put(5, "Figure.FigureT");
        correspondence.put(6, "Figure.FigureZ");
    }

    public Figure buildFigure(){
        count = random.nextInt(7);
        Figure currentFigure = null;
        String correspondingFigure = correspondence.get(count);
        try {
            currentFigure = (Figure) Class.forName(correspondingFigure).newInstance();
        } catch (NullPointerException |ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }catch (InstantiationException | IllegalAccessException ex){
            throw new RuntimeException("Another Problem");
        }
        return currentFigure;
    }

    /*public Figure buildFigure(int count){
        Figure currentFigure = null;
        String correspondingFigure = correspondence.get(count);
        try {
            currentFigure = (Figure) Class.forName(correspondingFigure).newInstance();
            method = Class.forName(correspondingFigure).getDeclaredMethod("turnOver");
        } catch (NullPointerException | NoSuchMethodException |ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }catch (InstantiationException | IllegalAccessException ex){
            throw new RuntimeException("Another Problem");
        }
        return currentFigure;
    }*/
}
