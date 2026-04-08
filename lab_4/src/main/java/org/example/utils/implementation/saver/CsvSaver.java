package org.example.utils.implementation.saver;

import org.example.utils.Saver;

import java.io.*;
import java.util.Map;

public class CsvSaver implements Saver {
    @Override
    public void save(Map<String, Double> result, OutputStream os) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
            writer.write("transport, result");
            writer.newLine();

            for(var entry : result.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
