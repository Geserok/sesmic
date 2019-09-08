package com.seismic.seismic.frames;

import com.seismic.seismic.data.Coordinates;
import com.seismic.seismic.exceptions.BadFormatOfFileException;
import com.seismic.seismic.exceptions.EndOfFileExceptions;
import com.seismic.seismic.services.AppData;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    private RangePanel rangePanel;
    @Autowired
    private AppData appData;
    @Autowired
    private DepthPanel depthPanel;

    private XYChart chart;
    private int speed = 1000;
    @Getter
    private double currentMinY = -0.1d;
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
        chart = graph.getArrayOfGraph();
        add(new XChartPanel<>(chart));
        setLayout(new FlowLayout());
        repaint();
    }

    @Override
    public void run() {
        timer.setDelay(appFlags.getSpeed());
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void update() {
        getSeriesForCharts();
        repaint();
    }

    private void getSeriesForCharts() {
        List<List<Double>> xCoordinates = new ArrayList<>();
        List<Double> yCoordinates;
        if (currentYCoordinate < 10) {
            yCoordinates = appData.getYCoordinatesRange(0, currentYCoordinate);
            for (int i = 0; i < currentYCoordinate; i++) {
                List<Double> line = appData.getXCoordinatesForY(i);
                xCoordinates.add(line);
            }
        } else {
            yCoordinates = appData.getYCoordinatesRange(currentYCoordinate - 9, currentYCoordinate + 1);
            for (int i = currentYCoordinate - 9; i < currentYCoordinate + 1; i++) {
                List<Double> line = appData.getXCoordinatesForY(i);
                xCoordinates.add(line);
            }
            chart.getStyler().setYAxisMax(yCoordinates.get(0));
            chart.getStyler().setYAxisMin(yCoordinates.get(9));
        }
        removeAllFormChart();
        addGraphs(xCoordinates, yCoordinates);
        currentYCoordinate++;
        depthPanel.refreshDepth(xCoordinates, yCoordinates);
        revalidate();
    }

    private void addGraphs(List<List<Double>> xCoordinates, List<Double> yCoordinates) {
        for (int seriesNumber = 0; seriesNumber < 5; seriesNumber++) {
            List<Double> xForSingleGraph = new ArrayList<>();
            for (List<Double> list: xCoordinates) {
                xForSingleGraph.add(list.get(seriesNumber));
            }
            graph.addSeriesToChart(chart, Integer.toString(seriesNumber), xForSingleGraph, yCoordinates);
            rangePanel.updateRanges(seriesNumber);
            appData.setVisibleYCoordinates(yCoordinates);
            if(appData.getLastVisibleCoordinate() > yCoordinates.get(yCoordinates.size() - 1)) {
                appData.setLastVisibleCoordinate(yCoordinates.get(yCoordinates.size() - 1));
            }
        }
    }

    private void removeAllFormChart() {
        for (int seriesNumber = 0; seriesNumber < 5; seriesNumber++) {
            chart.removeSeries(Integer.toString(seriesNumber));
        }
    }

    public void upGraphs() {
        if(currentYCoordinate > 11) {
            currentYCoordinate = currentYCoordinate - 2;
            getSeriesForCharts();
            repaint();
        }
    }

    public void downGraphs() {
        double yCoordinate = appData.getYCoordinate(currentYCoordinate);
        if (appData.getLastVisibleCoordinate() < yCoordinate) {
                getSeriesForCharts();
                repaint();
        }
    }
}
