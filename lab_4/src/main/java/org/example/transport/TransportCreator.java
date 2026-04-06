package org.example.transport;

import org.example.dto.TransportInfo;

public interface TransportCreator {
    Transport createTransport(TransportInfo transportInfo);
}
