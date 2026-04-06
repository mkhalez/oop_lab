package org.example;

import org.example.dto.CargoInfo;
import org.example.dto.TransportInfo;
import org.example.product.Cargo;
import org.example.transport.Transport;
import org.example.utils.Calculator;
import org.example.utils.Operator;
import org.example.utils.Reader;

import java.util.List;
import java.util.Map;

public class LogisticsSystem {
    private Reader reader;
    private Operator operator;
    private Calculator calculator;

    public LogisticsSystem(Reader reader, Operator operator, Calculator calculator) {
        this.reader = reader;
        this.operator = operator;
        this.calculator = calculator;
    }

    public void run() {
        String fileName = operator.requestFileName();

        List<CargoInfo> existCargos = reader.getCargoesInfo(fileName);
        List<TransportInfo> existTransport = reader.getTransportsInfo(fileName);

        Map<Cargo, Integer> cargos = operator.requestCargo(existCargos);
        Transport transport = operator.requestTransport(existTransport);
        double distance = operator.requestDistance();

        Double result = calculator.calculate(transport, cargos, distance);

        System.out.println(result);
    }


}
