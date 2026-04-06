package org.example.product;

public class Cargo {
    private double weightUnitsKg;
    private double costOfTransportationPerKg;
    private String name;

    public Cargo(double weightUnitsKg, double costOfTransportationPerKg, String name) {
        this.weightUnitsKg = weightUnitsKg;
        this.costOfTransportationPerKg = costOfTransportationPerKg;
        this.name = name;
    }

    public Cargo() {}

    public double getWeightUnitsKg() {
        return weightUnitsKg;
    }

    public void setWeightUnitsKg(double weightUnitsKg) {
        this.weightUnitsKg = weightUnitsKg;
    }

    public double getCostOfTransportationPerKg() {
        return costOfTransportationPerKg;
    }

    public void setCostOfTransportationPerKg(double costOfTransportationPerKg) {
        this.costOfTransportationPerKg = costOfTransportationPerKg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
