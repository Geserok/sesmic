package com.seismic.seismic.services;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class AppFlags {

    private boolean stopStartFlag = false;
    private int speed;
    @Getter
    private double[] xMin = new double[5];
    @Getter
    private double[] xMax = new double[5];

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = 1000 * 60/speed;
    }

    public void addXmin(int graphNumber, double scaleValue) {
        xMin[graphNumber] = scaleValue;
    }
    public void addXmax(int graphNumber, double scaleValue) {
        xMax[graphNumber] = scaleValue;
    }

    public boolean getStopStartFlag() {
        return stopStartFlag;
    }

    public void setStopStartFlag(boolean stopStartFlag) {
        this.stopStartFlag = stopStartFlag;
    }
}
