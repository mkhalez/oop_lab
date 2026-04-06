package org.example.utils.implementation;

import org.example.product.Cargo;
import org.example.transport.Transport;
import org.example.utils.Calculator;

import java.util.Map;

public class CalculatorImpl implements Calculator {
    @Override
    public double calculate(Transport transport, Map<Cargo, Integer> cargos, double distance) {
        double result = 0.0;

        result = cargos.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getCostOfTransportationPerKg() * entry.getKey().getWeightUnitsKg() * entry.getValue())
                .sum();

        result += transport.getConsumptionPerKm() * distance;

        return result;
    }

}
