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
    private ButtonsPanel buttonsPanel;
    @Autowired
    private AppFlags appFlags;
    @Autowired
    private Graph graph;

    private XYChart chart;
    private int speed = 1000;
    private double currentMinY = -1000d;
    private int currentYCoordinate = 1;
    private int quantityGraphs = 5;
    private Timer timer;

    @PostConstruct
    public void init() throws IOException {
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
        chart = graph.getArrayOfGraph();
        this.add(new XChartPanel<>(chart));
        this.setSize(800, 1000);
        this.setLayout(new FlowLayout());
        this.repaint();
    }

    @Override
    public void run() {
        graph.addSeriesToChart(chart);
        timer.setDelay(appFlags.getSpeed());
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void update() throws IOException {
//        for (int i = 1; i < 6 ; i++) {
//            //getSeriesForCharts(graphs);
//            i++;
//        }
//        currentYCoordinate++;
        this.repaint();
    }

    public void updatePanel() {
            chart.getStyler().setYAxisMin(chart.getStyler().getYAxisMin() * 1.5);
            currentMinY = chart.getStyler().getYAxisMin();
    }

//    private void getSeriesForCharts(XYChart chart) throws IOException {
//        double[] massY = new double[currentYCoordinate];
//        for (int j = 0; j < currentYCoordinate; j++) {
//            massY[j] = -j;
//        }
//        double[] xCoordinates = coordinateCreator.getXCoordinates(currentYCoordinate, paramNumber);
//        double[] yCoordinates = coordinateCreator.getYCoordinates(currentYCoordinate);
//        if (yCoordinates[currentYCoordinate - 1] < currentMinY) {
//            updatePanel();
//        }
//        buttonsPanel.updateParams(paramNumber, xCoordinates);
//        chart.removeSeries("Param " + paramNumber);
//        chart.addSeries("Param " + paramNumber, xCoordinates, yCoordinates);
//    }
}
