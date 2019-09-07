package com.seismic.seismic.frames;

import com.seismic.seismic.services.AppFlags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component
public class RangePanel extends JPanel {

    @Autowired
    AppFlags appFlags;

    private JLabel firstRange;
    private JLabel secondRange;
    private JLabel thirdRange;
    private JLabel forthRange;
    private JLabel fifthRange;

    @PostConstruct
    public void init() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{350, 300, 300, 300, 300};
        setLayout(gridBagLayout);
        add(firstRange = new JLabel("First"));
        add(secondRange = new JLabel("Second"));
        add(thirdRange = new JLabel("Third"));
        add(forthRange = new JLabel("Forth"));
        add(fifthRange = new JLabel("Fifth"));
        add(new JLabel(""));
    }

    public void updateRanges(int graphNumber) {
        switch (graphNumber) {
            case 0: {
                firstRange.setText(appFlags.getXMin()[graphNumber] + "..." + appFlags.getXMax()[graphNumber]);
                break;
            }
            case 1: {
                secondRange.setText(appFlags.getXMin()[graphNumber] + "..." + appFlags.getXMax()[graphNumber]);
                break;
            }
            case 2: {
                thirdRange.setText(appFlags.getXMin()[graphNumber] + "..." + appFlags.getXMax()[graphNumber]);
                break;
            }
            case 3: {
                forthRange.setText(appFlags.getXMin()[graphNumber] + "..." + appFlags.getXMax()[graphNumber]);
                break;
            }
            case 4: {
                fifthRange.setText(appFlags.getXMin()[graphNumber] + "..." + appFlags.getXMax()[graphNumber]);
                break;
            }
        }
    }
}
