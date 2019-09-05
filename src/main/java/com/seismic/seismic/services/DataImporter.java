package com.seismic.seismic.services;

import com.seismic.seismic.data.Coordinates;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class DataImporter {

    @Autowired
    private Coordinates coordinates;
    @Autowired
    private AppData appData;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRow(String row) {
        String[] array = row.split("\\s+");
        coordinates.addYCoordinate(Double.parseDouble(array[0]));
        appData.addYCoordinate(Double.parseDouble(array[0]));
        for (int columnNumber = 1; columnNumber < array.length; columnNumber++) {
            Double xCoordinate = Double.parseDouble(array[columnNumber]);
            coordinates.addXCoordinate(xCoordinate, columnNumber);
            appData.addXCoordinate(xCoordinate, columnNumber);
        }
    }
}
