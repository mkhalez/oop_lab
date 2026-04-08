package org.example.utils.implementation.filter;

import org.example.dto.CargoInfo;

import java.util.List;

public interface CargoFilter {
    List<CargoInfo> filterOut(List<CargoInfo> cargoInfos);
    void setNextFilter(CargoFilter filter);
}
