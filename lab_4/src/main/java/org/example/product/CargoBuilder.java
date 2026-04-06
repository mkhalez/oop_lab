package org.example.product;

public class CargoBuilder {
    private Cargo cargo = new Cargo();

    public CargoBuilder name(String name) {
        cargo.setName(name);
        return this;
    }

    public CargoBuilder weightUnitsKg(double weightUnitsKg) {
        cargo.setWeightUnitsKg(weightUnitsKg);
        return this;
    }

    public CargoBuilder costOfTransportationPerKg(double costOfTransportationPerKg) {
        cargo.setCostOfTransportationPerKg(costOfTransportationPerKg);
        return this;
    }

    public CargoBuilder reset() {
        cargo = new Cargo();
        return this;
    }

    public Cargo build() {
        return new Cargo(cargo.getWeightUnitsKg(), cargo.getCostOfTransportationPerKg(), cargo.getName());
    }

}
