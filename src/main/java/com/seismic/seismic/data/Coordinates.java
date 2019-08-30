package com.seismic.seismic.data;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class Coordinates {

    private List<Double> xFirstCoordinates = new ArrayList<>();
    private List<Double> xSecondCoordinates = new ArrayList<>();
    private List<Double> xThirdCoordinates = new ArrayList<>();
    private List<Double> xForthCoordinates = new ArrayList<>();
    private List<Double> xFifthCoordinates = new ArrayList<>();
    private List<Double> yCoordinates = new ArrayList<>();

    public void addXCoordinate(Double xCoordinate, Integer numberOfGraph) {
        if (numberOfGraph == 1) {
            xFirstCoordinates.add(xCoordinate);
        } else if (numberOfGraph == 2) {
            xSecondCoordinates.add(xCoordinate);
        } else if (numberOfGraph == 3) {
            xThirdCoordinates.add(xCoordinate);
        } else if (numberOfGraph == 4) {
            xForthCoordinates.add(xCoordinate);
        } else if (numberOfGraph == 5) {
            xFifthCoordinates.add(xCoordinate);
        }
    }
    public void addYCoordinate(Double yCoordinate) {
        yCoordinates.add(yCoordinate);
    }
}
