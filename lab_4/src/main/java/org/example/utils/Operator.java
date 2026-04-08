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

    List<Transport> requestTransport(List<TransportInfo> transportInfos);

    double requestDistance();

    Reader getReader(String fileName);

    String requestToSave();

    boolean enableEncryption();

    boolean enableZip();

    boolean enableFilter();

    int requestMaxConsumption();
    int requestMinConsumption();

    int requestMinSpeed();
    int requestMaxSpeed();

    boolean enableSort();
    boolean enableSortByPrice();
    boolean enableSortBySpeed();

}
