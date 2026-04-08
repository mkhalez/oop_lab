package org.example.utils;

import org.example.dto.Result;

import java.io.OutputStream;
import java.util.Map;

public interface Saver {
    void save(Map<String, Result> result, OutputStream os);
}
