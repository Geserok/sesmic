package UI;

import Exceptions.BadPathException;
import Services.CoordinateCreator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class StartFrame extends JFrame {

    public StartFrame() throws HeadlessException {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select excel file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Select excel file", "xls", "xlsx");
        fileChooser.addChoosableFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            StartFrame.this.setVisible(false);
            CoordinateCreator coordinateCreator = CoordinateCreator.getInstance();
            try {
                coordinateCreator.setPath((selectedFile.getAbsolutePath()));
                MainFrame.getInstance().setVisible(true);
            } catch (BadPathException exception) {
                MainFrame.getInstance().setVisible(false);
            }
            MainFrame.getInstance().setExtendedState(MAXIMIZED_BOTH);
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
