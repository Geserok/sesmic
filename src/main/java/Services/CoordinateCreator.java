package Services;

import Exceptions.EndOfFileExceptions;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class CoordinateCreator {

    public double[] getXCoordinates(int numPoints, int paramNumbers) throws IOException {
        return readFromExcelX("C:\\Users\\Zstudent\\IdeaProjects\\sesmic\\src\\main\\resources\\data.xlsx", numPoints, paramNumbers);
    }

    public double[] getYCoordinates(int numPoints) throws IOException {
        return readFromExcelY("C:\\Users\\Zstudent\\IdeaProjects\\sesmic\\src\\main\\resources\\data.xlsx", numPoints);
    }

    private double[] readFromExcelX(String file, int numPoints, int paramNumber) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet myExcelSheet = myExcelBook.getSheet("Sheet1");
        double[] xCoordinates = new double[numPoints];
        for (int i = 1; i < numPoints; i++) {
            XSSFRow row = myExcelSheet.getRow(i + 1);
            try {
                xCoordinates[i] = row.getCell(paramNumber).getNumericCellValue();
            } catch (NullPointerException e) {
                throw new EndOfFileExceptions();
            }

        }
        return xCoordinates;
    }

    private double[] readFromExcelY(String file, int numPoints) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet myExcelSheet = myExcelBook.getSheet("Sheet1");
        double[] yCoordinates = new double[numPoints];
        for (int i = 0; i < numPoints; i++) {
            XSSFRow row = myExcelSheet.getRow(i + 1);
            try {
                yCoordinates[i] = -row.getCell(0).getNumericCellValue();
            } catch (NullPointerException e) {
                throw new EndOfFileExceptions();
            }
        }
        return yCoordinates;
    }

}
