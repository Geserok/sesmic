package UI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static UI.MainFrame.stopStartFlag;

public class ButtonsPanel extends JPanel {

    private GraphPanel graphPanel;

    private static ButtonsPanel instance;

    private List<JLabel> listParams = new ArrayList<>();

    public static ButtonsPanel getInstance() {
        if (instance == null) {
            instance = new ButtonsPanel();
        }
        return instance;
    }

    private ButtonsPanel() {
        this.graphPanel = GraphPanel.getInstance();
        JButton startStop = new JButton("Start/stop");
        JTextArea speed = new JTextArea("60");
        speed.setBorder(new LineBorder(Color.BLACK));
        for (int i = 0; i < 5; i++) {
            listParams.add(new JLabel("Param " + (i+1)));
        }

        this.setLayout(new FlowLayout());
        this.add(new JPanel() {
            {
                setBorder(new LineBorder(Color.BLACK));
                setLayout(new GridLayout(8, 1));
                add(startStop);
                add(new JTextField("Скорость обновления(точек/мин):"));
                add(speed);
                listParams.forEach(this::add);
            }
        });

        startStop.addActionListener(e -> {
            if (stopStartFlag) {
                stopStartFlag = false;
            } else {
                stopStartFlag = true;
                try {
                    int newSpeed = Integer.parseInt(speed.getText());
                    if (newSpeed > 0 && newSpeed <= 600) {
                        graphPanel.setSpeed(newSpeed);
                    }
                } catch (NumberFormatException e1) {
                    speed.setText(String.valueOf(graphPanel.getSpeed()));
                }
            }
        });
    }

    public void updateParams(int paramNumber, double[] xCoordinates) {
        listParams.get(paramNumber - 1).setText("Param " + paramNumber + ": " + xCoordinates[xCoordinates.length - 1]);
    }
}
