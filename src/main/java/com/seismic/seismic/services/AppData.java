package com.seismic.seismic.services;

import com.seismic.seismic.data.Coordinates;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppData {

    @Autowired
    private Coordinates coordinates;

    @Getter
    private int yStart = 0;


    private List<Double> visibleYCoordinates = new ArrayList<>();
    private List<Double> yCoordinates = new ArrayList<>();
    private List<Double> xFirstCoordinates = new ArrayList<>();
    private List<Double> xSecondCoordinates = new ArrayList<>();
    private List<Double> xThirdCoordinates = new ArrayList<>();
    private List<Double> xForthCoordinates = new ArrayList<>();
    private List<Double> xFifthCoordinates = new ArrayList<>();

    public void addYCoordinate(Double yCoordinate) {
        yCoordinates.add(-yCoordinate);
    }

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

    public List<Double> getVisibleYCoordinates() {
        return visibleYCoordinates;
    }

    public void setVisibleYCoordinates(List<Double> visibleYCoordinates) {
        this.visibleYCoordinates = visibleYCoordinates;
    }
    public List<Double> getYCoordinatesRange(int start, int end) {
        return yCoordinates.subList(start, end);
    }

    public Double getYCoordinate(int index) {
        return yCoordinates.get(index);
    }

    public List<Double> getXCoordinatesForY(int index) {
        List<Double> xCoordinates = new ArrayList<>();
        xCoordinates.add(xFirstCoordinates.get(index));
        xCoordinates.add(xSecondCoordinates.get(index));
        xCoordinates.add(xThirdCoordinates.get(index));
        xCoordinates.add(xForthCoordinates.get(index));
        xCoordinates.add(xFifthCoordinates.get(index));
        return xCoordinates;
    }

    public void setyStart(int yStart) {
        if(yCoordinates.size() - 1 > yStart) {
            this.yStart = yStart;
        }
    }
}
