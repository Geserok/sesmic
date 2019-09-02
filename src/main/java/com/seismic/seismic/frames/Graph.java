package com.seismic.seismic.frames;

import com.seismic.seismic.data.Coordinates;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class Graph {

    @Autowired
    private Coordinates coordinates;
    @Autowired
    private GraphPanel graphPanel;

    private XYChart chart;

    private XYStyler styler;

    public XYChart getArrayOfGraph() {
        chart = new XYChartBuilder().build();
        styler = chart.getStyler();
        styler.setPlotGridLinesVisible(false);
        styler.setYAxisMin(graphPanel.getCurrentMinY());
        styler.setYAxisMax(0.0);
        chart.setWidth(graphPanel.getWidth());
        chart.setHeight(graphPanel.getHeight());
        chart.setSeriesStyles();
        return chart;
    }

    public void addSeriesToChart(XYChart chart, String name, List<Double> xCoordinates, List<Double> yCoordinates) {
        chart.addSeries(name, xCoordinates, yCoordinates).setMarker(SeriesMarkers.NONE);
    }
}

