package org.example.utils.implementation.filter.transport;

import org.example.dto.CargoInfo;
import org.example.dto.TransportInfo;
import org.example.utils.implementation.filter.CargoFilter;
import org.example.utils.implementation.filter.TransportFilter;
import org.example.utils.implementation.filter.cargo.CargoBaseFilter;

import java.util.List;

public class FilterConsumption extends TransportBaseFilter {
    public FilterConsumption(int max, int min) {
        super(max, min);
    }

    @Override
    public List<TransportInfo> filterOut(List<TransportInfo> transportInfos) {
        var newTransportInfos =  transportInfos.stream()
                .filter(transport -> transport.consumption() >= min && transport.consumption() <= max)
                .toList();
        return nextFilter == null ? newTransportInfos : nextFilter.filterOut(newTransportInfos);
    }
}
