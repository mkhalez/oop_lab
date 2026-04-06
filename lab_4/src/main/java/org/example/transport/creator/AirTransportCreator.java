package org.example.transport.creator;

import org.example.dto.TransportInfo;
import org.example.transport.Transport;
import org.example.transport.TransportCreator;
import org.example.transport.type.AirTransport;

public class AirTransportCreator implements TransportCreator {
    @Override
    public Transport createTransport(TransportInfo transportInfo) {
        return new AirTransport(transportInfo.name(), transportInfo.consumption(), transportInfo.speed());
    }
}
