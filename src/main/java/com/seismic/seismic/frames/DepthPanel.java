package com.seismic.seismic.frames;

import com.seismic.seismic.services.AppData;
import com.seismic.seismic.services.DepthLabelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DepthPanel extends JPanel {

    @Autowired
    DepthLabelFactory depthLabelFactory;
    @Autowired
    AppData appData;

    @PostConstruct
    public void init() {
        setLayout(new GridLayout(10, 0));
        for (int i = 0; i < 10; i++) {
            add(depthLabelFactory.getDepthLabels(i));
        }
    }

    public void refreshDepth(List<List<Double>> xCoordinates, List<Double> yCoordinates) {
        removeAll();
        for (int j = 0; j < yCoordinates.size(); j++) {
                add(depthLabelFactory.getDepthLabels(yCoordinates.get(j), xCoordinates.get(j)));

        }
    }
}
