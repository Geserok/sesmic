package UI;

import Exceptions.BadPathException;
import Services.CoordinateCreator;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {

    public StartFrame() throws HeadlessException {
        JTextField textField = new JTextField("Укажите путь к файлу excel");
        JButton button = new JButton("Принять");
        button.addActionListener(e -> {
            StartFrame.this.setVisible(false);
            CoordinateCreator coordinateCreator = CoordinateCreator.getInstance();
            try {
                coordinateCreator.setPath(textField.getText());
                MainFrame.getInstance().setVisible(true);
            } catch (BadPathException exception) {
                MainFrame.getInstance().setVisible(false);
            }
            MainFrame.getInstance().setExtendedState(MAXIMIZED_BOTH);
        });

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        this.setLayout(new FlowLayout());
        this.add(textField);
        this.add(button);
        this.setSize(300, 75);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocation(width / 4, height / 4);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
