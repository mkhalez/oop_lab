package org.example.utils.implementation.filter.cargo;

import org.example.utils.implementation.filter.CargoFilter;

public abstract class CargoBaseFilter implements CargoFilter {
    protected int max;
    protected int min;
    protected CargoFilter nextFilter;

    public CargoBaseFilter(int max, int min) {
        this.max = max;
        this.min = min;
    }

    public void setNextFilter(CargoFilter filter) {
        nextFilter = filter;
    }
}
