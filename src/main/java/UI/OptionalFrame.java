package UI;

import Services.CoordinateCreator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static UI.MainFrame.stopStartFlag;

public class OptionalFrame extends JFrame {

    public OptionalFrame() {
        JLabel textLabel = new JLabel("Выберите следуюший лист excel");
        List<String> lists = null;
        try {
            lists = CoordinateCreator.getInstance().getLists();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        lists.forEach(a -> {
            JButton listButton = new JButton(a);
            listButton.addActionListener(e -> {
                CoordinateCreator cc = CoordinateCreator.getInstance();
                cc.setSheet(a);
                cc.updateData();
                stopStartFlag = true;
                OptionalFrame.this.setVisible(false);
            });
            buttons.add(listButton);
        });


        this.setLayout(new GridLayout(2, 1));
        this.add(textLabel);
        this.add(buttons);
        this.setSize(300, 200);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setVisible(true);
    }
}
