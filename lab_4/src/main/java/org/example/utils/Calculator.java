package org.example.utils;

import org.example.product.Cargo;
import org.example.transport.Transport;
import java.util.List;
import java.util.Map;

public interface Calculator {
    double calculate(Transport transport, Map<Cargo, Integer> cargos, double distance);
}
