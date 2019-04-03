package UI;

import Services.CoordinateCreator;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GraphPanel extends JPanel implements Runnable {

    private CoordinateCreator coordinateCreator = new CoordinateCreator();
    private XChartPanel[] graphs;
    private int speed = 100;
    private double currentMaxY = 0d;
    private double currentMinY = -100d;
    private int currentYCoordinate = 1;


    public GraphPanel(XChartPanel[] graphs) {
        this.graphs = graphs;
        for (JPanel panel : graphs) {
            this.add(panel);
        }
        this.setLayout(new GridLayout(1, 5));
        this.repaint();
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(speed);
                update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        for (XChartPanel graph: graphs) {
            XYChart chart = (XYChart) graph.getChart();
            getSeriesForCharts(chart);
            XYStyler styler = chart.getStyler();
            if (currentYCoordinate > Math.abs(currentMinY)) {
                currentMinY = currentMinY - 100d;
                currentMaxY = currentMaxY - 100d;
            }
            styler.setYAxisMin(currentMinY);
            styler.setYAxisMax(currentMaxY);
        }
        currentYCoordinate++;
        this.repaint();
    }

    private void getSeriesForCharts(XYChart chart) {
        double[] massY = new double[currentYCoordinate];
        for (int j = 0; j < currentYCoordinate; j++) {
            massY[j] = -j;
        }
        double[] coordinates = coordinateCreator.getCoordinates(currentYCoordinate);
        chart.removeSeries("a");
        XYSeries series = chart.addSeries("a", coordinates, massY);
        series.setMarker(SeriesMarkers.NONE);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCurrentMaxY(double currentMaxY) {
        this.currentMaxY = currentMaxY;
    }

    public void setCurrentMinY(double currentMinY) {
        this.currentMinY = currentMinY;
    }
}
