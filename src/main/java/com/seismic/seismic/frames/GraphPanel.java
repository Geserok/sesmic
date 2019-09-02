package com.seismic.seismic.frames;

import com.seismic.seismic.data.Coordinates;
import com.seismic.seismic.exceptions.BadFormatOfFileException;
import com.seismic.seismic.exceptions.EndOfFileExceptions;
import com.seismic.seismic.services.AppFlags;
import com.seismic.seismic.services.CoordinateCreator;
import lombok.Getter;
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
    private Coordinates coordinates;
    @Autowired
    private ButtonsPanel buttonsPanel;
    @Autowired
    private AppFlags appFlags;
    @Autowired
    private Graph graph;
    @Autowired
    private CustomJScrollPane customJScrollPane;

    private XYChart chart;
    private int speed = 1000;
    @Getter
    private double currentMinY = -0.1d;
    private int currentYCoordinate = 10;
    private int quantityGraphs = 5;
    private Timer timer;

    @PostConstruct
    public void init() throws IOException {
        timer = new Timer(appFlags.getSpeed(), a -> {
            {
                try {
                    update();
                } catch (EndOfFileExceptions e) {
                    stop();
                    appFlags.setStopStartFlag(false);
                } catch (Exception e) {
                    appFlags.setStopStartFlag(false);
                    stop();
                    System.out.println(e);
                    new BadFormatOfFileException();
                }
            }
        });
        setSize(1000, 1000);
        chart = graph.getArrayOfGraph();
        add(new XChartPanel<>(chart));
        setLayout(new FlowLayout());
        repaint();
    }

    @Override
    public void run() {
        getSeriesForCharts();
        timer.setDelay(appFlags.getSpeed());
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void update() {
        getSeriesForCharts();
        currentYCoordinate++;
        repaint();
    }

    public void updatePanel() {
        this.remove(0);
        chart = graph.getArrayOfGraph();
        add(new XChartPanel<>(chart));
        setPreferredSize(new Dimension(getWidth(), chart.getHeight() + 100));
        revalidate();
        repaint();
    }

    private void getSeriesForCharts() {
        java.util.List<Double> yCoordinates = coordinates.getYCoordinates(currentYCoordinate);
        if (yCoordinates.get(currentYCoordinate - 1) < currentMinY) {
            currentMinY = yCoordinates.get(currentYCoordinate - 1);
            updatePanel();
        }
        //buttonsPanel.updateParams(paramNumber, xCoordinates);
        for (int seriesNumber = 0; seriesNumber < 5; seriesNumber++) {
            chart.removeSeries(Integer.toString(seriesNumber));
            java.util.List<Double> xCoordinates = coordinates.getXCoordinates(seriesNumber, currentYCoordinate);
            graph.addSeriesToChart(chart, Integer.toString(seriesNumber),xCoordinates, yCoordinates);
        }
    }
}
