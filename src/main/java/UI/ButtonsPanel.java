package UI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static UI.MainFrame.stopStartFlag;

public class ButtonsPanel extends JPanel {

    private GraphPanel graphPanel;

    public ButtonsPanel() {
        this.graphPanel = GraphPanel.getInstance();
        JButton startStop = new JButton("Start/stop");
        JTextArea speed = new JTextArea("60");
        speed.setBorder(new LineBorder(Color.BLACK));
        this.setLayout(new GridLayout(20, 1));
        this.add(startStop);
        this.add(new JPanel());
        this.add(new JPanel(){
             {
                 setBorder(new LineBorder(Color.BLACK));
                 setLayout(new GridLayout(2,1));
                add(new JTextField("Скорость обновления (точек/мин):"));
                add(speed);
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
}