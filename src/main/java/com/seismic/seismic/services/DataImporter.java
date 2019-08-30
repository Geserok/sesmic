package com.seismic.seismic.services;

import com.seismic.seismic.data.Coordinates;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;

public class DataImporter {

    @Setter
    private Coordinates coordinates;

    private boolean firstLineFlag = true;

    public void importData(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            int symbol;
            StringBuilder row = new StringBuilder();
            while ((symbol = stream.read()) != -1) {
                row.append((char) symbol);
                if ((char) symbol == '\n') {
                    if (firstLineFlag) {
                        firstLineFlag = false;
                    } else {
                        parseRow(row.toString());
                    }
                    row = new StringBuilder();
                }
            }
            System.out.println(coordinates.getFirst());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRow(String row) {
        String[] array = row.split("\\s+");
        Double yCoordinate = Double.parseDouble(array[0]);
        for (int columnNumber = 1; columnNumber < array.length; columnNumber++) {
            Double xCoordinate = Double.parseDouble(array[columnNumber]);
            coordinates.addCoordinate(yCoordinate, xCoordinate, columnNumber);
        }
    }
}
