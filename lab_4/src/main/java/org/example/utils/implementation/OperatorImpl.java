package org.example.utils.implementation;

import org.example.dto.CargoInfo;
import org.example.dto.TransportInfo;
import org.example.product.Cargo;
import org.example.product.CargoBuilder;
import org.example.transport.Transport;
import org.example.transport.TransportCreator;
import org.example.transport.creator.AirTransportCreator;
import org.example.transport.creator.LandTransportCreator;
import org.example.transport.creator.WaterTransportCreator;
import org.example.utils.Operator;
import org.example.utils.Reader;
import org.example.utils.implementation.reader.CsvReader;
import org.example.utils.implementation.reader.JsonReader;
import org.example.utils.implementation.reader.XmlReader;

import java.util.*;

public class OperatorImpl implements Operator {
    @Override
    public String requestFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter file name with info: ");
        return scanner.nextLine();
    }

    @Override
    public boolean enableFilter() {
        System.out.print("enter 1 to filter transport or 0 to skip: ");
        while (true) {
            int answer = askNumber();
            if(answer == 1) {
                return true;
            } else if (answer == 0) {
                return false;
            } else {
                System.out.print("incorrect input, try again: ");
            }
        }
    }

    @Override
    public int requestMaxConsumption() {
        System.out.print("enter max consumption: ");
        return askNumber();
    }

    @Override
    public int requestMinConsumption() {
        System.out.print("enter min consumption: ");
        return askNumber();
    }

    @Override
    public int requestMinSpeed() {
        System.out.print("enter min speed: ");
        return askNumber();
    }

    @Override
    public int requestMaxSpeed() {
        System.out.print("enter max speed: ");
        return askNumber();
    }

    @Override
    public String requestToSave() {
        System.out.print("enter name of file to save data or skip: ");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public boolean enableEncryption() {
        System.out.print("enter 1 to save with encryption or 0 to skip: ");
        while (true) {
            int answer = askNumber();
            if(answer == 1) {
                return true;
            } else if (answer == 0) {
                return false;
            } else {
                System.out.print("incorrect input, try again: ");
            }
        }
    }

    @Override
    public boolean enableZip() {
        System.out.print("enter 1 to save with zip or 0 to skip: ");
        while (true) {
            int answer = askNumber();
            if(answer == 1) {
                return true;
            } else if (answer == 0) {
                return false;
            } else {
                System.out.print("incorrect input, try again: ");
            }
        }
    }

    @Override
    public Reader getReader(String fileName) {
        if(fileName.endsWith(".json")) return new JsonReader();

        if(fileName.endsWith(".csv")) return new CsvReader();

        if(fileName.endsWith(".xml")) return new XmlReader();

        return null;
    }

    @Override
    public Map<Cargo, Integer> requestCargo(List<CargoInfo> cargoInfos) {
        Map<Cargo, Integer> map = new HashMap<>();
        CargoBuilder cargoBuilder = new CargoBuilder();
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter number of each cargo: ");
        for(var cargo : cargoInfos) {
            System.out.println("name: " + cargo.name() + " | "
                    + "cost of transportation per kg: " +  cargo.costOfTransportationPerKg() + " | "
                    + "weight units kg cargo: " + cargo.weightUnitsKg() + " | number of: ");

            Integer numberOf = askNumber();

            Cargo cargoObject = cargoBuilder.reset()
                    .name(cargo.name())
                    .weightUnitsKg(cargo.weightUnitsKg())
                    .costOfTransportationPerKg(cargo.costOfTransportationPerKg())
                    .build();

            map.put(cargoObject, numberOf);
        }
        return map;
    }

    @Override
    public List<Transport> requestTransport(List<TransportInfo> transportInfos) {
        System.out.println("existing transport: ");
        for(var transportInfo : transportInfos) {
            System.out.println("name: " + transportInfo.name() + " | " +
                    "consumption: " + transportInfo.consumption() + " | " +
                    "speed: " + transportInfo.speed() + " | " +
                    "type: " + transportInfo.type());
        }
        System.out.print("enter transport name: ");

        List<TransportInfo> resultInfo = getResultTransportInfo(transportInfos);

        List<Transport> totalResult = getTransport(resultInfo);

        return totalResult;

    }

    private List<Transport> getTransport(List<TransportInfo> resultInfo) {
        Map<String, TransportCreator> creators = new HashMap<>();
        List<Transport> result = new ArrayList<>();

        for(var transportInfo : resultInfo) {
            String type = transportInfo.type();
            TransportCreator creator = creators.get(type);

            if(creator == null) {
                creator = getTransportCreator(type);
                creators.put(type, creator);
            }

            Transport newTransport = creator.createTransport(transportInfo);
            result.add(newTransport);
        }

        return result;

    }

    private TransportCreator getTransportCreator(String type) {
        return switch (type) {
            case "Земля":
                yield new LandTransportCreator();
            case "Вода":
                yield new WaterTransportCreator();
            case "Воздух":
                yield new AirTransportCreator();
            default: throw new IllegalStateException("Unknown transport type: " + type);
        };
    }

    private List<TransportInfo> getResultTransportInfo(List<TransportInfo> transportInfos) {
        Scanner scanner = new Scanner(System.in);
        List<TransportInfo> resultInfo = new ArrayList<>();
        while(true) {
            String name = scanner.nextLine();

            if(name.isEmpty()) {
                resultInfo = transportInfos.stream()
                        .map(transportInfo ->
                                new TransportInfo(transportInfo.name(),
                                        transportInfo.consumption(),
                                        transportInfo.speed(),
                                        transportInfo.type()))
                        .toList();
                break;
            }

            Optional<TransportInfo> result = transportInfos.stream()
                    .filter(info -> info.name().equals(name))
                    .findFirst();

            if(result.isPresent()) {
                resultInfo.add(result.get());
                break;
            }

            System.out.println("system doesn't have this transport? try again: ");
        }

        return resultInfo;
    }

    @Override
    public double requestDistance() {
        System.out.print("enter distance: ");
        return askDoubleNumber(new Scanner(System.in));
    }

    private double askDoubleNumber(Scanner scanner) {
        while (true) {
            try {
                String numberStr = scanner.nextLine();
                return Double.parseDouble(numberStr);
            } catch (NumberFormatException e) {
                System.out.println("incorrect value, try again: ");
            }
        }
    }

    private int askNumber() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String numberStr = scanner.nextLine();
                return Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                System.out.println("incorrect value, try again: ");
            }
        }
    }

}
