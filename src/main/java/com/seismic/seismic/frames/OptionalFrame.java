package com.seismic.seismic.frames;

import com.seismic.seismic.services.AppFlags;
import com.seismic.seismic.services.CoordinateCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class OptionalFrame extends JFrame {

    @Autowired
    CoordinateCreator coordinateCreator;
    @Autowired
    AppFlags appFlags;

    @PostConstruct
    public void init() {
        JLabel textLabel = new JLabel("Выберите следуюший лист excel");
        List<String> lists = null;
//        try {
//            lists = coordinateCreator.getLists();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
//        lists.forEach(a -> {
//            JButton listButton = new JButton(a);
//            listButton.addActionListener(e -> {
//                coordinateCreator.setSheet(a);
//                coordinateCreator.updateData();
//                appFlags.setStopStartFlag(true);
//                OptionalFrame.this.setVisible(false);
//            });
//            buttons.add(listButton);
//        });

        this.setLayout(new GridLayout(2, 1));
        this.add(textLabel);
        this.add(buttons);
        this.setSize(300, 200);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setVisible(false);
    }
}
