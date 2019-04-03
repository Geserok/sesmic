package UI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(JPanel graphPanel, JPanel buttonPanel) {
        this.setLayout(new BorderLayout());
        this.add(graphPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.EAST);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
