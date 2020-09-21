package Commands;

import Exceptions.CalculatorEx;

import java.util.HashMap;
import java.util.Stack;

public class Context<E> extends Stack<Double> {
    private HashMap<String, Double> mp;
    private final Class<Double> clazz;

    public Context(Class<Double> clazz){
        mp = new HashMap<>();
        this.clazz = clazz;
    }


    public int addDefinition(String x, String y) throws CalculatorEx {
        if(mp.containsKey(x)){
            return -1;
        }
        else{
            try {
                Double value = java.lang.Double.parseDouble(y);
                mp.put(x, value);
            }catch (NumberFormatException e) {
                System.err.println("Неверный формат строки!");
            }
        }
        return 1;
    }

    public String push(String x) throws CalculatorEx {
        try {
            if (mp.containsKey(x)) {
                super.push(mp.get(x));
            } else {
                Double value = java.lang.Double.parseDouble(x);
                super.push(value);
            }
        }catch (NumberFormatException ex){
            System.out.println("Неверный формат строки!");
        }
        return x;
    }
}
