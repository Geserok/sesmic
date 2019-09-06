package com.seismic.seismic.frames;

import com.seismic.seismic.data.Coordinates;
import com.seismic.seismic.services.AppData;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.internal.Series_Markers;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Collection;
import java.util.List;

@Component
public class Graph {

    @Autowired
    private Coordinates coordinates;
    @Autowired
    private GraphPanel graphPanel;
    @Autowired
    private AppData appData;

    private XYChart chart;

    private XYStyler styler;

    public XYChart getArrayOfGraph() {
        chart = new XYChartBuilder().theme(Styler.ChartTheme.Matlab).build();
        styler = chart.getStyler();
        styler.setLegendVisible(false);
        styler.setPlotGridLinesVisible(false);
        styler.setXAxisTicksVisible(false);
        styler.setYAxisTicksVisible(false);
        styler.setYAxisTitleVisible(false);
        styler.setYAxisMin(-1d);
        styler.setYAxisMax(0.0);
        styler.setXAxisMax(-200.0);
        styler.setXAxisMax(800.0);
        chart.setWidth(1500);
        chart.setHeight(1000);
        chart.setSeriesStyles();
        return chart;
    }

    public void addSeriesToChart(XYChart chart, String name, List<Double> xCoordinates, List<Double> yCoordinates) {
        List<Double> doubles = coordinates.prepareCoordinates(xCoordinates, Integer.parseInt(name));
        chart.addSeries(name, doubles, yCoordinates).setMarker(SeriesMarkers.CIRCLE);
    }
}

