package UI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static boolean stopStartFlag = true;

    public MainFrame(JPanel graphPanel, JPanel buttonPanel) {
        this.setLayout(new BorderLayout());
        this.add(graphPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.EAST);
        this.setSize(1300, 1000);
        this.setMinimumSize(new Dimension(1300, 1000));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
