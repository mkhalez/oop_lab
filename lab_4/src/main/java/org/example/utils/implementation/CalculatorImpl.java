package org.example.utils.implementation;

import org.example.product.Cargo;
import org.example.transport.Transport;
import org.example.utils.Calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorImpl implements Calculator {
    @Override
    public Map<String, Double> calculate(List<Transport> transports, Map<Cargo, Integer> cargos, double distance) {
        Map<String, Double> map = new HashMap<>();

        for(var transport : transports) {
            double result = 0.0;

            result = cargos.entrySet().stream()
                    .mapToDouble(entry -> entry.getKey().getCostOfTransportationPerKg() * entry.getKey().getWeightUnitsKg() * entry.getValue())
                    .sum();

            result += transport.getConsumptionPerKm() * distance;

            System.out.println(transport.getName() + " result: " + result);

            map.put(transport.getName(), result);
        }

        return map;
    }

}
