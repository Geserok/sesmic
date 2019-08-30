package com.seismic.seismic.frames;

import com.seismic.seismic.data.Coordinates;
import com.seismic.seismic.services.CoordinateCreator;
import com.seismic.seismic.services.DataImporter;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Graph {

    @Autowired
    private Coordinates coordinates;

    public XYChart getArrayOfGraph() throws IOException {
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.XChart).build();
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setXAxisTickMarkSpacingHint(100);
        return chart;
    }

    public void addSeriesToChart(XYChart chart) {
        List<Double> yCoordinates = coordinates.getYCoordinates();
        List<Double> xFirstCoordinates = coordinates.getXThirdCoordinates();
        List<Double> doubles = prepareCoordinates(xFirstCoordinates);
        chart.addSeries("1", doubles, yCoordinates).setMarker(SeriesMarkers.NONE);

    }

    private List<Double> prepareCoordinates(List<Double> xCoordinates) {
        int size = xCoordinates.size();
        List<Double> sortedCoordinates = new ArrayList(xCoordinates);
        sortedCoordinates.sort(Double::compareTo);
        Double maxBound = sortedCoordinates.get(size - 1);
        Double minBound = sortedCoordinates.get(0);
        if (minBound > 0) {
            return xCoordinates.stream().map(a -> a - minBound).collect(Collectors.toList());
        } else if (minBound < 0) {
            return xCoordinates.stream().map(a -> a - minBound).collect(Collectors.toList());
        } else {
            return xCoordinates;
        }
    }
}

