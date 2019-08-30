package com.seismic.seismic.frames;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    public static XYChart getArrayOfGraph() {
        XYChart chart = new XYChartBuilder().width(800).height(600).theme(Styler.ChartTheme.Matlab).title("Matlab Theme").yAxisTitle("Глубина").build();

        List<Integer> xData = new ArrayList<Integer>();
        for (int i = 0; i < 640; i++) {
            xData.add(i);
        }
        List<Double> y1Data = getYAxis(xData, 320, 160);
        List<Double> y2Data = getYAxis(xData, 320, 320);
        List<Double> y3Data = new ArrayList<Double>(xData.size());
        for (int i = 0; i < 640; i++) {
            y3Data.add(y1Data.get(i) - y2Data.get(i));
        }

        XYSeries series = chart.addSeries("Gaussian 1", xData, y1Data);
        series.setMarker(SeriesMarkers.NONE);
        series = chart.addSeries("Gaussian 2", xData, y2Data);
        series.setMarker(SeriesMarkers.NONE);
        series = chart.addSeries("Difference", xData, y3Data);
        series.setMarker(SeriesMarkers.NONE);

        return chart;
    }

    private static List<Double> getYAxis(List<Integer> xData, double mean, double std) {

        List<Double> yData = new ArrayList<Double>(xData.size());

        for (int i = 0; i < xData.size(); i++) {
            yData.add((1 / (std * Math.sqrt(2 * Math.PI))) * Math.exp(-(((xData.get(i) - mean) * (xData.get(i) - mean)) / ((2 * std * std)))));
        }
        return yData;
    }
}

