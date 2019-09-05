package com.seismic.seismic.services;

import com.seismic.seismic.frames.ButtonsPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

@Service
public class DepthLabelFactory {

    @Autowired
    ButtonsPanel buttonsPanel;
    @Autowired
    private AppData appData;

    public JButton getDepthLabels(int yCoordinate) {
        JButton button;
        try{
            button = new JButton(String.valueOf(appData.getYCoordinate(yCoordinate)));
        } catch (Exception e) {
            button = new JButton(String.valueOf(yCoordinate));
        }
        button.setBorderPainted(false);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Double> xCoordinatesForY = appData.getXCoordinatesForY((int) yCoordinate);
                double[] newXCooditanes = new double[5];
                for (int i = 0; i < 5; i++) {
                    newXCooditanes[i] = xCoordinatesForY.get(i);
                }
                buttonsPanel.updateParams(newXCooditanes);
            }
        });
        return button;
    }
}
