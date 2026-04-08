package org.example.utils.implementation.saver;

import org.example.dto.Result;
import org.example.utils.Saver;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EncryptionDecoratorSaver extends DecoratorSaver{
    private static final int CHANGE_NUMBER = 5;
    public EncryptionDecoratorSaver(Saver saver, String fileName) {
        super(saver, fileName);
    }

    @Override
    public void save(List<Map.Entry<String, Result>> result, OutputStream os) {
        List<Map.Entry<String, Result>> newResult = result.stream()
                .map(entry -> Map.entry(
                        shifting(entry.getKey()),
                        new Result(
                                entry.getValue().price() + CHANGE_NUMBER,
                                entry.getValue().speed() + CHANGE_NUMBER
                        )
                ))
                .toList();
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
