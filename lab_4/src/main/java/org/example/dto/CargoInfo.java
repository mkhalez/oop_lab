package org.example.dto;

public record CargoInfo(String name,
                        Double weightUnitsKg,
                        double costOfTransportationPerKg) {
}
