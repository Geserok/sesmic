package com.seismic.seismic.frames;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class SettingsPanel extends JFrame {

    private JLabel graph1 = new JLabel("Graph 1");
    private JLabel graph2 = new JLabel("Graph 2");
    private JLabel graph3 = new JLabel("Graph 3");
    private JLabel graph4 = new JLabel("Graph 4");
    private JLabel graph5 = new JLabel("Graph 5");
    private JTextArea step1 = new JTextArea("100");
    private JTextArea step2 = new JTextArea("100");
    private JTextArea step3 = new JTextArea("100");
    private JTextArea step4 = new JTextArea("100");
    private JTextArea step5 = new JTextArea("100");


    @PostConstruct
    public void init() {
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSteps();
            }
        });
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        step1.setBorder(border);
        step2.setBorder(border);
        step3.setBorder(border);
        step4.setBorder(border);
        step5.setBorder(border);

        GridLayout layout = new GridLayout(7, 2);
        setLayout(layout);
        add(new JLabel("Graph"));
        add(new JLabel("Step"));
        add(graph1);
        add(step1);
        add(graph2);
        add(step2);
        add(graph3);
        add(step3);
        add(graph4);
        add(step4);
        add(graph5);
        add(step5);
        add(cancel);
        add(save);
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void turnOn() {
        this.setVisible(true);
    }

    private void saveSteps() {
        String text1 = step1.getText();
        String text2 = step2.getText();
        String text3 = step3.getText();
        String text4 = step4.getText();
        String text5 = step5.getText();
        try {
            int step1 = Integer.parseInt(text1);
            int step2 = Integer.parseInt(text2);
            int step3 = Integer.parseInt(text3);
            int step4 = Integer.parseInt(text4);
            int step5 = Integer.parseInt(text5);
        } catch (Exception e) {

        }
    }
}
