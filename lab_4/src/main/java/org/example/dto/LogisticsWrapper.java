package org.example.dto;

import java.util.List;

public record LogisticsWrapper (
    List<CargoInfo> cargoes,
    List<TransportInfo> transports) {}
