package org.example.transport.type;

import org.example.transport.Transport;

public class AirTransport implements Transport {
    private String name;
    private double consumption;
    private double speed;

    public AirTransport(String name, double consumption, double speed) {
        this.name = name;
        this.consumption = consumption;
        this.speed = speed;
    }

    @Override
    public double getConsumptionPerKm() {
        return consumption;
    }

    @Override
    public double getSpeedKilometersPerHour() {
        return speed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "air type";
    }
}
