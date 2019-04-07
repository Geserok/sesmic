package UI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel graphPanel;
    private JPanel buttonPanel;

    static boolean stopStartFlag = false;
    private static MainFrame instance;

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private MainFrame() {
        graphPanel = GraphPanel.getInstance();
        buttonPanel = ButtonsPanel.getInstance();

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
        this.add(buttonPanel, BorderLayout.EAST);
        this.add(southLabel, BorderLayout.SOUTH);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
