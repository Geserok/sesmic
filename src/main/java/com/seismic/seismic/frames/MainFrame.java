package com.seismic.seismic.frames;

import com.seismic.seismic.services.AppFlags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component
public class MainFrame extends JFrame {

    @Autowired
    private GraphPanel graphPanel;
    @Autowired
    private ButtonsPanel buttonsPanel;
    @Autowired
    private CustomJScrollPane customJScrollPane;
    @Autowired
    private RangePanel rangePanel;
    @Autowired
    private DepthPanel depthPanel;
    @Autowired
    private ScrollButtons scrollButtons;
    @PostConstruct
    public void init() {

        this.setLayout(new BorderLayout());
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BorderLayout());
        centrePanel.add(depthPanel, BorderLayout.WEST);
        centrePanel.add(new JPanel(), BorderLayout.NORTH);
        centrePanel.add(customJScrollPane, BorderLayout.CENTER);
        centrePanel.add(rangePanel, BorderLayout.SOUTH);
        centrePanel.add(scrollButtons, BorderLayout.EAST);
        this.add(centrePanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
