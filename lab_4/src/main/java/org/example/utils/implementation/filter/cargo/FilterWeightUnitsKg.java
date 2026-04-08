package org.example.utils.implementation.filter.cargo;

import org.example.dto.CargoInfo;
import org.example.utils.implementation.filter.CargoFilter;

import java.util.List;

public class FilterWeightUnitsKg extends CargoBaseFilter {
    public FilterWeightUnitsKg(int max, int min) {
        super(max, min);
    }

    @Override
    public List<CargoInfo> filterOut(List<CargoInfo> cargoInfos) {
        var newCargoInfos =  cargoInfos.stream().filter(cargo -> cargo.weightUnitsKg() >= min && cargo.weightUnitsKg() <= max).toList();
        return nextFilter == null ? newCargoInfos : nextFilter.filterOut(newCargoInfos);
    }
}
