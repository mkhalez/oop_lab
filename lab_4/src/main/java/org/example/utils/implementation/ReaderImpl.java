package org.example.utils.implementation;

import org.example.dto.CargoInfo;
import org.example.dto.TransportInfo;
import org.example.utils.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderImpl implements Reader {
    @Override
    public List<CargoInfo> getCargoesInfo(String fileName) {
        List<CargoInfo> result = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] properties = line.split(";");
                if(properties[0].equals("cargo")) {
                    result.add(new CargoInfo(properties[1], Double.parseDouble(properties[2]), Double.parseDouble(properties[3])));
                }
            }

        } catch (Exception e) {
            return List.of();
        }
        return result;
    }

    @Override
    public List<TransportInfo> getTransportsInfo(String fileName) {
        List<TransportInfo> result = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] properties = line.split(";");
                String[] nameAndType = properties[1].split(" ");
                if(properties[0].equals("transport")) {
                    result.add(new TransportInfo(
                            nameAndType[0],
                            Double.parseDouble(properties[4]),
                            Double.parseDouble(properties[5]),
                            nameAndType[1].substring(1, nameAndType[1].length() - 1)));
                }
            }
        } catch (Exception e) {
            return List.of();
        }
        return result;
    }
}
