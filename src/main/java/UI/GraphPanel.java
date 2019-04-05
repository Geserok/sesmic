package UI;

import Exceptions.EndOfFileExceptions;
import Services.CoordinateCreator;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static UI.MainFrame.stopStartFlag;

public class GraphPanel extends JPanel implements Runnable {

    private CoordinateCreator coordinateCreator = new CoordinateCreator();
    private XChartPanel[] graphs;
    private int speed = 1000;
    private double currentMaxY = 0d;
    private double currentMinY = -100d;
    private int currentYCoordinate = 1;
    private int quantityGraphs = 5;

    private static GraphPanel instance;

    public static GraphPanel getInstance() {
        if (instance == null) {
            instance = new GraphPanel();
        }
        return instance;
    }

    private GraphPanel() {
        XChartPanel[] graphs = Graph.getArrayOfGraph(quantityGraphs);
        this.graphs = graphs;
        for (JPanel panel : graphs) {
            this.add(panel);
        }
        this.setSize(800, 3000);
        this.setLayout(new FlowLayout());
        this.repaint();
    }

    @Override
    public void run() {
        while (true) {

            if (!stopStartFlag) {
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    TimeUnit.MILLISECONDS.sleep(speed);
                    update();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                } catch (EndOfFileExceptions e) {
                    new OptionalFrame();
                    break;
                }
            }
        }
    }

    public void update() throws IOException {
        int i = 1;
        for (XChartPanel graph : graphs) {
            XYChart chart = (XYChart) graph.getChart();
            getSeriesForCharts(chart, i);
            i++;
        }
        currentYCoordinate++;
        this.repaint();
    }

    private void getSeriesForCharts(XYChart chart, int paramNumber) throws IOException {
        double[] massY = new double[currentYCoordinate];
        for (int j = 0; j < currentYCoordinate; j++) {
            massY[j] = -j;
        }
        double[] xCoordinates = coordinateCreator.getXCoordinates(currentYCoordinate, paramNumber);
        double[] yCoordinates = coordinateCreator.getYCoordinates(currentYCoordinate);
        chart.removeSeries("Param " + paramNumber);
        XYSeries series = chart.addSeries("Param " + paramNumber, xCoordinates, yCoordinates);
        series.setMarker(SeriesMarkers.NONE);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = 1000 * 1 / (speed / 60);
    }

    public void setCurrentMaxY(double currentMaxY) {
        this.currentMaxY = currentMaxY;
    }

    public void setCurrentMinY(double currentMinY) {
        this.currentMinY = currentMinY;
    }
}
