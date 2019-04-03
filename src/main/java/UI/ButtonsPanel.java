package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel {

    public ButtonsPanel() {
        JButton scrollDown = new JButton("V");
        JButton scrollUp = new JButton("^");
        this.setLayout(new GridLayout(2, 1));
        this.add(scrollUp);
        this.add(scrollDown);

        scrollUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonsPanel.this.getParent().repaint();
            }
        });

    }

}
