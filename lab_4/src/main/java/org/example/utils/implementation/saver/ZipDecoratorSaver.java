package org.example.utils.implementation.saver;

import org.example.utils.Saver;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDecoratorSaver extends DecoratorSaver{
    public ZipDecoratorSaver(Saver saver, String fileName) {
        super(saver, fileName);
    }

    @Override
    public void save(Map<String, Double> result, OutputStream os) {
        ZipOutputStream zipOut = new ZipOutputStream(os);

        String jsonName = fileName.replace(".zip", ".json");
        try {
            zipOut.putNextEntry(new ZipEntry(jsonName));
            super.save(result, zipOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
