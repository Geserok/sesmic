package com.seismic.seismic.frames;

import com.seismic.seismic.services.AppFlags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ButtonsPanel extends JPanel {

    @Autowired
    private AppFlags appFlags;
    @Autowired
    private GraphPanel graphPanel;

    private List<JLabel> listParams = new ArrayList<>();

    @PostConstruct
    public void init() {
        JButton startStop = new JButton("Start/stop");
        //JTextArea speed = new JTextArea("60");
        JSlider speed = new JSlider(1, 10, 1);
        speed.setPaintLabels(true);
        speed.setMajorTickSpacing(1);
        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider)e.getSource();
                int value = slider.getValue();
                appFlags.setSpeed(value * 60);
            }
        });
        speed.setBorder(new LineBorder(Color.BLACK));
        for (int i = 0; i < 5; i++) {
            listParams.add(new JLabel("Param " + (i + 1) + ":"));
        }

        this.setLayout(new FlowLayout());
        this.add(new JPanel() {
            {
                setBorder(new LineBorder(Color.BLACK));
                setLayout(new GridLayout(8, 1));
                add(startStop);
                add(new JLabel("Скорость обновления(точек/мин):")).setEnabled(false);
                add(speed);
                listParams.forEach(this::add);
            }
        });

        startStop.addActionListener(e -> {
            if (appFlags.getStopStartFlag()) {
                graphPanel.stop();
                appFlags.setStopStartFlag(false);
            } else {
                appFlags.setStopStartFlag(true);
                try {
                    int newSpeed = Integer.parseInt(String.valueOf(speed.getValue() * 60));
                    if (newSpeed > 0 && newSpeed <= 600) {
                        appFlags.setSpeed(newSpeed);
                    }
                    graphPanel.run();
                } catch (NumberFormatException e1) {
                    speed.setValue(appFlags.getSpeed() / 60);
                }
            }
        });
    }

    public void updateParams(int paramNumber, double[] xCoordinates) {
        listParams.get(paramNumber - 1).setText("Param " + paramNumber + ": " + xCoordinates[xCoordinates.length - 1]);
    }
}
