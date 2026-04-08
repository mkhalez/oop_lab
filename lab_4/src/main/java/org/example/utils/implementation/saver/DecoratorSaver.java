package org.example.utils.implementation.saver;

import org.example.utils.Saver;

import java.io.OutputStream;
import java.util.Map;

public abstract class DecoratorSaver implements Saver {
    private Saver saver;
    protected String fileName;

    public DecoratorSaver(Saver saver, String fileName) {
        this.saver = saver;
        this.fileName = fileName;
    }

    @Override
    public void save(Map<String, Double> result, OutputStream os) {
        saver.save(result, os);
    }
}
