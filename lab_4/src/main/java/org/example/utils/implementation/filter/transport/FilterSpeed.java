package org.example.utils.implementation.filter.transport;

import org.example.dto.TransportInfo;

import java.util.List;

public class FilterSpeed extends TransportBaseFilter{
    public FilterSpeed(int max, int min) {
        super(max, min);
    }

    @Override
    public List<TransportInfo> filterOut(List<TransportInfo> transportInfos) {
        var newTransportInfos =  transportInfos.stream()
                .filter(transport -> transport.speed() >= min && transport.speed() <= max)
                .toList();
        return nextFilter == null ? newTransportInfos : nextFilter.filterOut(newTransportInfos);
    }
}
