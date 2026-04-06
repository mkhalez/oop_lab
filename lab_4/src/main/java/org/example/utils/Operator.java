package org.example.utils;

import org.example.dto.CargoInfo;
import org.example.dto.TransportInfo;
import org.example.product.Cargo;
import org.example.transport.Transport;

import java.util.List;
import java.util.Map;

public interface Operator {
    String requestFileName();

    Map<Cargo, Integer> requestCargo(List<CargoInfo> cargoInfos);

    Transport requestTransport(List<TransportInfo> transportInfos);

    double requestDistance();
}
