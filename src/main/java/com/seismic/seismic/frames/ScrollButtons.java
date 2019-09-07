package com.seismic.seismic.frames;

import com.seismic.seismic.services.AppFlags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Component
public class ScrollButtons extends JPanel {

    @Autowired
    private AppFlags appFlags;
    @Autowired
    private GraphPanel graphPanel;

    @PostConstruct
    public void init() {
        GridLayout layout = new GridLayout(2, 1);
        setLayout(layout);
        JButton up = new JButton("▲");
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!appFlags.getStopStartFlag()) {
                    graphPanel.upGraphs();
                }
            }
        });
        JButton down = new JButton("▼");
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!appFlags.getStopStartFlag()) {
                    graphPanel.downGraphs();
                }
            }
        });
        add(up);
        add(down);
    }
}
