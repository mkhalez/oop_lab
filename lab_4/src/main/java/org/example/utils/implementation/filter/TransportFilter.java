package org.example.utils.implementation.filter;

import org.example.dto.CargoInfo;
import org.example.dto.TransportInfo;

import java.util.List;

public interface TransportFilter {
    List<TransportInfo> filterOut(List<TransportInfo> transportInfos);
    void setNextFilter(TransportFilter filter);
}
