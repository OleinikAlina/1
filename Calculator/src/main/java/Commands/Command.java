package Commands;

import Exceptions.CalculatorEx;

public abstract class Command {
    public abstract void doCommand(String[] arg,  Context<Double> context) throws CalculatorEx;
}

