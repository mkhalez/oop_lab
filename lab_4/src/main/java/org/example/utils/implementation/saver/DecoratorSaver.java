package org.example.utils.implementation.saver;

import org.example.dto.Result;
import org.example.utils.Saver;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public abstract class DecoratorSaver implements Saver {
    private Saver saver;
    protected String fileName;

    public DecoratorSaver(Saver saver, String fileName) {
        this.saver = saver;
        this.fileName = fileName;
    }

    @Override
    public void save(List<Map.Entry<String, Result>> result, OutputStream os) {
        saver.save(result, os);
    }
}
