package org.example.utils.implementation.saver;

import org.example.dto.Result;
import org.example.utils.Saver;

import java.io.*;
import java.util.Map;

public class CsvSaver implements Saver {
    @Override
    public void save(Map<String, Result> result, OutputStream os) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
            writer.write("transport;price;speed");
            writer.newLine();

            for(var entry : result.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue().price() + ";" + entry.getValue().speed());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
