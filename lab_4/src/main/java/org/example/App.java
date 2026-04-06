package org.example;

import org.example.utils.Calculator;
import org.example.utils.Operator;
import org.example.utils.Reader;
import org.example.utils.implementation.CalculatorImpl;
import org.example.utils.implementation.OperatorImpl;
import org.example.utils.implementation.ReaderImpl;

import java.sql.Connection;

public class App
{
    public static void main( String[] args )
    {
        Calculator calculator = new CalculatorImpl();
        Operator operator = new OperatorImpl();
        Reader reader = new ReaderImpl();

        LogisticsSystem logisticsSystem = new LogisticsSystem(reader, operator, calculator);
        logisticsSystem.run();
    }
}
