package UI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    static boolean stopStartFlag = true;

    public MainFrame(JPanel graphPanel, JPanel buttonPanel) {
        this.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(graphPanel);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel depth = new JLabel("Глубина");
        depth.setUI(new VerticalLabelUI(true));
        depth.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(depth, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.EAST);
        this.setSize(1300, 1000);
        this.setMinimumSize(new Dimension(1300, 1000));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
