package com.seismic.seismic.services;

import org.springframework.stereotype.Component;

@Component
public class AppFlags {

    private boolean stopStartFlag = false;
    private int speed;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = 1000 * 60/speed;
    }


    public boolean getStopStartFlag() {
        return stopStartFlag;
    }

    public void setStopStartFlag(boolean stopStartFlag) {
        this.stopStartFlag = stopStartFlag;
    }
}
