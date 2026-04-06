package org.example.utils;

import org.example.dto.CargoInfo;
import org.example.dto.TransportInfo;

import java.util.List;

public interface Reader {
    public List<CargoInfo> getCargoesInfo(String fileName);

    public List<TransportInfo> getTransportsInfo(String fileName);
}
