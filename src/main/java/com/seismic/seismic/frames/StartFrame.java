package com.seismic.seismic.frames;

import com.seismic.seismic.exceptions.BadPathException;
import com.seismic.seismic.services.CoordinateCreator;
import com.seismic.seismic.services.DataImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

@Component
public class StartFrame extends JFrame {

    @Autowired
    CoordinateCreator coordinateCreator;
    @Autowired
    MainFrame mainFrame;
    @Autowired
    DataImporter dataImporter;

    @PostConstruct
    public void init() throws HeadlessException {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select excel file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Select excel file", "txt", "xls", "xlsx");
        fileChooser.addChoosableFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            setVisible(false);
            try {
                dataImporter.importData(selectedFile.getAbsolutePath());
                mainFrame.setVisible(true);
            } catch (Exception exception) {
                mainFrame.setVisible(false);
            }
            mainFrame.setExtendedState(MAXIMIZED_BOTH);

        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            System.exit(1);
        }

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        this.setLayout(new FlowLayout());
        this.add(fileChooser);
        this.setSize(600, 400);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocation(width / 4, height / 4);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
