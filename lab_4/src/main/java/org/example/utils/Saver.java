package org.example.utils;

import java.io.OutputStream;
import java.util.Map;

public interface Saver {
    void save(Map<String, Double> result, OutputStream os);
}
