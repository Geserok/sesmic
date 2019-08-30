package com.seismic.seismic.frames;

import com.seismic.seismic.exceptions.BadFormatOfFileException;
import com.seismic.seismic.exceptions.BadPathException;
import com.seismic.seismic.exceptions.EndOfFileExceptions;
import com.seismic.seismic.services.AppFlags;
import com.seismic.seismic.services.CoordinateCreator;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@Component
public class GraphPanel extends JPanel implements Runnable {

    @Autowired
    private CoordinateCreator coordinateCreator;
    @Autowired
    ButtonsPanel buttonsPanel;
    @Autowired
    AppFlags appFlags;

    private XYChart graphs;
    private int speed = 1000;
    private double currentMinY = -1000d;
    private int currentYCoordinate = 1;
    private int quantityGraphs = 5;
    private Timer timer;

    @PostConstruct
    public void init() {
        XYChart graphs = Graph.getArrayOfGraph();
        timer = new Timer(appFlags.getSpeed(), a -> {
            {
                try {
                    update();
                } catch (EndOfFileExceptions e) {
                    appFlags.setStopStartFlag(false);

                } catch (Exception e) {
                    appFlags.setStopStartFlag(false);
                    new BadFormatOfFileException();
                }
            }
        });
        this.graphs = graphs;
        this.setSize(800, 1000);
        this.setLayout(new FlowLayout());
        this.repaint();
    }

    @Override
    public void run() {
        timer.setDelay(appFlags.getSpeed());
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void update() throws IOException {
        for (int i = 1; i < 6 ; i++) {
            getSeriesForCharts(graphs, i);
            i++;
        }
        currentYCoordinate++;
        this.repaint();
    }

    public void updatePanel() {
            graphs.getStyler().setYAxisMin(graphs.getStyler().getYAxisMin() * 1.5);
            currentMinY = graphs.getStyler().getYAxisMin();
    }

    private void getSeriesForCharts(XYChart chart, int paramNumber) throws IOException {
        double[] massY = new double[currentYCoordinate];
        for (int j = 0; j < currentYCoordinate; j++) {
            massY[j] = -j;
        }
        double[] xCoordinates = coordinateCreator.getXCoordinates(currentYCoordinate, paramNumber);
        double[] yCoordinates = coordinateCreator.getYCoordinates(currentYCoordinate);
        if (yCoordinates[currentYCoordinate - 1] < currentMinY) {
            updatePanel();
        }
        buttonsPanel.updateParams(paramNumber, xCoordinates);
        chart.removeSeries("Param " + paramNumber);
        chart.addSeries("Param " + paramNumber, xCoordinates, yCoordinates);
    }
}
