package org.example.utils.implementation.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CargoInfo;
import org.example.dto.LogisticsWrapper;
import org.example.dto.TransportInfo;
import org.example.utils.Reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader implements Reader {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<CargoInfo> getCargoesInfo(String fileName) {
        File file = new File(fileName);
        try {
            return mapper.readValue(file, LogisticsWrapper.class).cargoes();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public List<TransportInfo> getTransportsInfo(String fileName) {
        File file = new File(fileName);
        try {
            return mapper.readValue(file, LogisticsWrapper.class).transports();
        } catch (IOException e) {
            return null;
        }
    }
}
