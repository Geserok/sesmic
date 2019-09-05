package com.seismic.seismic.data;

import com.seismic.seismic.services.AppFlags;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Component
public class Coordinates {

    @Autowired
    private AppFlags appFlags;

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

    public List<Double> getXCoordinates(int graphNumber, int currentCoordinate) {
        List<Double> shortListOfCoordinates = new ArrayList<>();
        for (int i = 0; i < currentCoordinate; i++) {
            switch (graphNumber) {
                case 0 : {
                    shortListOfCoordinates.add(xFirstCoordinates.get(i));
                    break;
                }
                case 1 : {
                    shortListOfCoordinates.add(xSecondCoordinates.get(i));
                    break;
                }
                case 2 : {
                    shortListOfCoordinates.add(xThirdCoordinates.get(i));
                    break;
                }
                case 3 : {
                    shortListOfCoordinates.add(xForthCoordinates.get(i));
                    break;
                }
                case 4 : {
                    shortListOfCoordinates.add(xFifthCoordinates.get(i));
                    break;
                }
            }
        }
        return prepareCoordinates(shortListOfCoordinates, graphNumber);
    }

    public List<Double> getYCoordinates(int currentCoordinate) {
        List<Double> shortListOfCoordinates = new ArrayList<>();
        for (int i = 0; i < currentCoordinate; i++) {
            shortListOfCoordinates.add(-yCoordinates.get(i));
        }
        return shortListOfCoordinates;
    }

    private List<Double> prepareCoordinates(List<Double> xCoordinates, int graphNumber) {
        Optional<Double> min = xCoordinates.stream().min(Double::compareTo);
        Optional<Double> max = xCoordinates.stream().max(Double::compareTo);
        appFlags.addXmax(graphNumber, max.get());
        appFlags.addXmin(graphNumber, min.get());
        List<Double> collect = new ArrayList<>();
        if (min.get() > 0) {
            collect = xCoordinates.stream().map(a -> a - min.get()).collect(Collectors.toList());
        } else if (min.get() < 0) {
            collect = xCoordinates.stream().map(a -> a - min.get()).collect(Collectors.toList());
        }
        Optional<Double> maxBound = collect.stream().max(Double::compareTo);
        double scale = 100 / (maxBound.get() + 1);
        return collect.stream().map(a -> (a * scale) + graphNumber * 100 + 100 * (graphNumber - 1)).collect(Collectors.toList());
    }
}
