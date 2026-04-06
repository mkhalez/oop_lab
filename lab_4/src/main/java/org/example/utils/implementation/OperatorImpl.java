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

import java.util.*;

public class OperatorImpl implements Operator {
    @Override
    public String requestFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter file name with info: ");
        return scanner.nextLine();
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

            Integer numberOf = askNumber(scanner);

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
    public Transport requestTransport(List<TransportInfo> transportInfos) {
        Scanner scanner = new Scanner(System.in);
        TransportInfo foundInfo = null;
        System.out.println("existing transport: ");
        for(var transportInfo : transportInfos) {
            System.out.println("name: " + transportInfo.name() + " | " +
                    "consumption: " + transportInfo.consumption() + " | " +
                    "speed: " + transportInfo.speed() + " | " +
                    "type: " + transportInfo.type());
        }
        System.out.print("enter transport name: ");

        while(true) {
            String name = scanner.nextLine();

            Optional<TransportInfo> result = transportInfos.stream()
                    .filter(info -> info.name().equals(name))
                    .findFirst();

            if(result.isPresent()) {
                foundInfo = result.get();
                break;
            }

            System.out.println("system doesn't have this transport? try again: ");
        }

        TransportCreator transportCreator = switch (foundInfo.name()) {
            case "Грузовик":
            case "Поезд":
                yield new LandTransportCreator();
            case "Танкер":
                yield new WaterTransportCreator();
            case "Самолет":
            case "Вертолет":
                yield new AirTransportCreator();
            default: throw new IllegalStateException("Unknown transport type: " + foundInfo.type());
        };


        return transportCreator.createTransport(foundInfo);

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

    private int askNumber(Scanner scanner) {
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
