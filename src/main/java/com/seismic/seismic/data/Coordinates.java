package com.seismic.seismic.data;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Coordinates {

    private Map<Double, Double> first = new HashMap<>();
    private Map<Double, Double> second = new HashMap<>();
    private Map<Double, Double> third = new HashMap<>();
    private Map<Double, Double> forth = new HashMap<>();
    private Map<Double, Double> fifth = new HashMap<>();

    public void addCoordinate(Double yCoordinate, Double xCoordinate, Integer numberOfGraph) {
        if (numberOfGraph == 1) {
            first.put(yCoordinate, xCoordinate);
        } else if (numberOfGraph == 2) {
            second.put(yCoordinate, xCoordinate);
        } else if (numberOfGraph == 3) {
            third.put(yCoordinate, xCoordinate);
        } else if (numberOfGraph == 4) {
            forth.put(yCoordinate, xCoordinate);
        } else if (numberOfGraph == 5) {
            fifth.put(yCoordinate, xCoordinate);
        }
    }
}
