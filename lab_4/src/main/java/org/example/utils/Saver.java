package org.example.utils;

import org.example.dto.Result;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface Saver {
    void save(List<Map.Entry<String, Result>> result, OutputStream os);
}
