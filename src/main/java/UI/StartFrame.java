package UI;

import Services.CoordinateCreator;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {

    public StartFrame() throws HeadlessException {
        JTextField textField = new JTextField("Впишите путь к файлу excel");
        JButton button = new JButton("Принять");
        button.addActionListener(e -> {
            StartFrame.this.setVisible(false);
            CoordinateCreator coordinateCreator = CoordinateCreator.getInstance();
            coordinateCreator.setPath(textField.getText());
            MainFrame.getInstance().setVisible(true);
            MainFrame.getInstance().setExtendedState(MAXIMIZED_BOTH);
        });

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        this.setLayout(new GridLayout(2,1));
        this.add(textField);
        this.add(button);
        this.setSize(300, 200);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocation(width/4, height/4);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
