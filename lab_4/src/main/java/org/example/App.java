package org.example;

import org.example.utils.Calculator;
import org.example.utils.Operator;
import org.example.utils.Saver;
import org.example.utils.implementation.CalculatorImpl;
import org.example.utils.implementation.OperatorImpl;
import org.example.utils.implementation.saver.JsonSaver;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        Calculator calculator = new CalculatorImpl();
        Operator operator = new OperatorImpl();

        LogisticsSystem logisticsSystem = new LogisticsSystem(operator, calculator);
        logisticsSystem.run();
    }
}
