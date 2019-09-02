package com.seismic.seismic.services;

import com.seismic.seismic.exceptions.BadPathException;
import com.seismic.seismic.exceptions.EndOfFileExceptions;
import com.seismic.seismic.frames.GraphPanel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CoordinateCreator {

    @Autowired
    GraphPanel graphPanel;

    private String path;

    private String sheet;

    private Map<Integer, List<Double>> xCoordinates = new HashMap<>();

    private List<Double> yCoordinates = new ArrayList<>();

    public double[] getXCoordinates(int numPoints, int paramNumbers) throws IOException {
        if (xCoordinates.isEmpty()) {
            readFromExcelX();
        }
        double[] newXcoordinates = new double[numPoints];
        for (int i = 0; i < numPoints; i++) {
            try {
                newXcoordinates[i] = xCoordinates.get(paramNumbers).get(i);
            } catch (IndexOutOfBoundsException e) {
                throw new EndOfFileExceptions();
            }
        }
        return newXcoordinates;
    }

    public double[] getYCoordinates(int numPoints) throws IOException {
        if (yCoordinates.isEmpty()) {
            readFromExcelY();
        }
        double[] newYcoordinates = new double[numPoints];
        for (int i = 0; i < numPoints; i++) {
            newYcoordinates[i] = yCoordinates.get(i);
        }
        return newYcoordinates;
    }

    private void readFromExcelX() throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(path));
        XSSFSheet myExcelSheet = myExcelBook.getSheet(sheet);
        for (int j = 1; j < 6; j++) {
            List<Double> xCoordinate = new ArrayList<>();
            int i = 1;
            if (xCoordinates.get(j) != null) {
                xCoordinate = xCoordinates.get(j);
            }
            while (true) {
                XSSFRow row = myExcelSheet.getRow(i);
                try {
                    xCoordinate.add(row.getCell(j).getNumericCellValue());
                    i++;
                } catch (NullPointerException e) {
                    break;
                }
            }
            xCoordinates.put(j, xCoordinate);
        }
    }

    private void readFromExcelY() throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(path));
        XSSFSheet myExcelSheet = myExcelBook.getSheet(sheet);
        int i = 0;
        while (true) {
            XSSFRow row = myExcelSheet.getRow(i + 1);
            try {
                this.yCoordinates.add(-row.getCell(0).getNumericCellValue());
                i++;
            } catch (NullPointerException e) {
                break;
            }
        }
    }

    public void updateData() {
        try {
            readFromExcelY();
            readFromExcelX();
            //graphPanel.updatePanel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLists() throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(path));
        List<String> listOfSheets = new ArrayList<>();
        int numberOfSheets = myExcelBook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            listOfSheets.add(myExcelBook.getSheetName(i));
        }
        return listOfSheets;
    }

    public void setPath(String path) throws BadPathException {
        this.path = path;
        try {
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(path));
            sheet = myExcelBook.getSheetName(0);
        } catch (Exception e) {
            throw new BadPathException();
        }
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }
}
