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

    @PostConstruct
    public void init() {

        JLabel southLabel = new JLabel("version 1.1");

        this.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(graphPanel);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel depth = new JLabel("Глубина");
        depth.setUI(new VerticalLabelUI(true));
        depth.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(depth, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);
        this.add(southLabel, BorderLayout.SOUTH);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
