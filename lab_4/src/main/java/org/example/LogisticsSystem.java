package org.example;

import org.example.dto.CargoInfo;
import org.example.dto.Result;
import org.example.dto.TransportInfo;
import org.example.product.Cargo;
import org.example.transport.Transport;
import org.example.utils.Calculator;
import org.example.utils.Operator;
import org.example.utils.Reader;
import org.example.utils.Saver;
import org.example.utils.implementation.filter.TransportFilter;
import org.example.utils.implementation.filter.sorting.SortStrategy;
import org.example.utils.implementation.filter.sorting.implementation.SortByPrice;
import org.example.utils.implementation.filter.sorting.implementation.SortBySpeed;
import org.example.utils.implementation.filter.transport.FilterConsumption;
import org.example.utils.implementation.filter.transport.FilterSpeed;
import org.example.utils.implementation.saver.CsvSaver;
import org.example.utils.implementation.saver.EncryptionDecoratorSaver;
import org.example.utils.implementation.saver.JsonSaver;
import org.example.utils.implementation.saver.ZipDecoratorSaver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class LogisticsSystem {
    private Reader reader;
    private Operator operator;
    private Calculator calculator;

    public LogisticsSystem(Operator operator, Calculator calculator) {
        this.operator = operator;
        this.calculator = calculator;
    }

    public void run() throws IOException {
        String fileName = operator.requestFileName();
        Reader reader = operator.getReader(fileName);

        List<CargoInfo> existCargos = reader.getCargoesInfo(fileName);
        List<TransportInfo> existTransport = reader.getTransportsInfo(fileName);

        if(operator.enableFilter()) {
            existTransport = filterTransport(existTransport);
        }

        Map<Cargo, Integer> cargos = operator.requestCargo(existCargos);
        List<Transport> transports = operator.requestTransport(existTransport);
        double distance = operator.requestDistance();

        List<Map.Entry<String, Result>> result = calculator.calculate(transports, cargos, distance).entrySet().stream().toList();
        result = new ArrayList<>(result);
        while (true) {
            String fileNameToSave = operator.requestToSave();

            if(!fileNameToSave.isEmpty() && !saveResult(fileNameToSave, result)) {
                System.out.print("error with filename, try again: ");
                continue;
            }
            break;
        }
    }

    private List<TransportInfo> filterTransport(List<TransportInfo> existTransport){
        int maxSpeed = operator.requestMaxSpeed();
        int minSpeed = operator.requestMinSpeed();
        int minConsumption = operator.requestMinConsumption();
        int maxConsumption = operator.requestMaxConsumption();

        TransportFilter consumptionFilter = new FilterConsumption(maxConsumption, minConsumption);
        TransportFilter speedFilter = new FilterSpeed(maxSpeed, minSpeed);
        consumptionFilter.setNextFilter(speedFilter);

        return consumptionFilter.filterOut(existTransport);
    }

    private List<Map.Entry<String, Result>> getSortedResult( List<Map.Entry<String, Result>> entries) {
        List<SortStrategy> strategies = new ArrayList<>();

        if(operator.enableSortByPrice()) strategies.add(new SortByPrice());
        if(operator.enableSortBySpeed()) strategies.add(new SortBySpeed());

        Comparator<Map.Entry<String, Result>> combined = strategies.stream()
                .map(SortStrategy::getComparator)
                .reduce(Comparator::thenComparing)
                .orElse((a,b) -> 0);

        entries.sort(combined);

        return entries;
    }


    private boolean saveResult(String fileNameToSave, List<Map.Entry<String, Result>> result) throws IOException {
        Saver saver;
        if (fileNameToSave.endsWith("json")) {
            saver = new JsonSaver();
        } else if (fileNameToSave.endsWith("csv")) {
            saver = new CsvSaver();
        } else {
            return false;
        }

        boolean enableEncryption = operator.enableEncryption();
        boolean enableZip = operator.enableZip();

        if(enableEncryption) saver = new EncryptionDecoratorSaver(saver, fileNameToSave);
        if(enableZip) {
            saver = new ZipDecoratorSaver(saver, fileNameToSave);
            fileNameToSave = fileNameToSave.replace("json", "zip");
            fileNameToSave = fileNameToSave.replace("csv", "zip");
        }

        if(operator.enableSort()) {
            getSortedResult(result);
        }
        try(FileOutputStream fos = new FileOutputStream(fileNameToSave)) {
            saver.save(result, fos);
        }
        return true;
    }


}
