package Exceptions;

import UI.StartFrame;

import javax.swing.*;
import java.awt.*;


public class BadPathException extends RuntimeException {

    public BadPathException() {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new GridLayout(2, 1));
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        jFrame.setLocation(width / 4, height / 4);
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            new StartFrame();
            jFrame.setVisible(false);
        });
        jFrame.add(new JLabel("Bad path"));
        jFrame.add(button);
        jFrame.setSize(200, 100);
        jFrame.setVisible(true);
    }

}
