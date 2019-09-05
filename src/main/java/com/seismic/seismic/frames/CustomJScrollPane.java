package com.seismic.seismic.frames;

import com.seismic.seismic.services.AppFlags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

@Component
public class CustomJScrollPane extends JScrollPane {

    @Autowired
    private GraphPanel graphPanel;
    @Autowired
    private AppFlags appFlags;

    @PostConstruct
    public void init() {
        setViewportView(graphPanel);
        setVerticalScrollBar(verticalScrollBar);
        setLayout(new ScrollPaneLayout());
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        verticalScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (appFlags.getStopStartFlag()) {
                        e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                }
            }
        });
    }
}
