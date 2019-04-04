package UI;

import javax.swing.*;
import java.awt.*;

public class OptionalFrame extends JFrame {

    public OptionalFrame() throws HeadlessException {
        this.setLayout(new BorderLayout());
        this.add(new JTextField("Optional menu"), BorderLayout.CENTER);
        this.setSize(300, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setVisible(true);
    }
}
