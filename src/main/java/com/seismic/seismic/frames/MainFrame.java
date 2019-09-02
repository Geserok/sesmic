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

    @PostConstruct
    public void init() {

        JLabel southLabel = new JLabel("version 1.2");
        this.setLayout(new BorderLayout());
        JLabel depth = new JLabel("Глубина");
        depth.setUI(new VerticalLabelUI(true));
        depth.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(depth, BorderLayout.WEST);
        this.add(customJScrollPane, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);
        this.add(southLabel, BorderLayout.SOUTH);

        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
