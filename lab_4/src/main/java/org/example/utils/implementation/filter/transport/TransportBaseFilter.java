package org.example.utils.implementation.filter.transport;

import org.example.dto.CargoInfo;
import org.example.dto.TransportInfo;
import org.example.utils.implementation.filter.CargoFilter;
import org.example.utils.implementation.filter.TransportFilter;

import java.util.List;

public abstract class TransportBaseFilter implements TransportFilter {
    protected int max;
    protected int min;
    protected TransportFilter nextFilter;

    public TransportBaseFilter(int max, int min) {
        this.max = max;
        this.min = min;
    }

    public void setNextFilter(TransportFilter filter) {
        nextFilter = filter;
    }
}
