package org.example.utils.implementation.saver;

import org.example.dto.Result;
import org.example.utils.Saver;

import java.io.OutputStream;
import java.util.Map;
import java.util.stream.Collectors;

public class EncryptionDecoratorSaver extends DecoratorSaver{
    private static final int CHANGE_NUMBER = 5;
    public EncryptionDecoratorSaver(Saver saver, String fileName) {
        super(saver, fileName);
    }

    @Override
    public void save(Map<String, Result> result, OutputStream os) {
        var newResult = result.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> shifting(entry.getKey()),
                        entry -> new Result(entry.getValue().price() + CHANGE_NUMBER, entry.getValue().speed() + CHANGE_NUMBER)
                ));
        super.save(newResult, os);
    }

    private String shifting(String value) {
        StringBuilder builder = new StringBuilder();
        for(var c : value.toCharArray()) {
            builder.append((char)(c + CHANGE_NUMBER));
        }
        return builder.toString();
    }
}
