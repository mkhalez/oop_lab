package org.example.utils.implementation.saver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Result;
import org.example.utils.Saver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class JsonSaver implements Saver {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public void save(Map<String, Result> result, OutputStream os) {
        try {
            mapper.writeValue(os, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
