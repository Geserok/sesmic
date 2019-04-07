package UI;

import Exceptions.BadPathException;
import Exceptions.EndOfFileExceptions;
import Services.CoordinateCreator;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static UI.MainFrame.stopStartFlag;

public class GraphPanel extends JPanel implements Runnable {

    private CoordinateCreator coordinateCreator = CoordinateCreator.getInstance();
    private XChartPanel[] graphs;
    private int speed = 1000;
    private double currentMinY = -1000d;
    private int currentYCoordinate = 1;
    private int quantityGraphs = 5;

    private Timer timer;

    private static GraphPanel instance;

    public static GraphPanel getInstance() {
        if (instance == null) {
            instance = new GraphPanel();
        }
        return instance;
    }

    private GraphPanel() {
        XChartPanel[] graphs = Graph.getArrayOfGraph(quantityGraphs);
        timer = new Timer(speed, a -> {
            {
                try {
                    update();
                } catch (IOException e) {
                    new BadPathException();
                } catch (EndOfFileExceptions e) {
                    stopStartFlag = false;
                    new OptionalFrame();
                }
            }
        });
        this.graphs = graphs;
        for (JPanel panel : graphs) {
            this.add(panel);
        }
        this.setSize(800, 1000);
        this.setLayout(new FlowLayout());
        this.repaint();
    }

    @Override
    public void run() {
        while (true) {
            if (!stopStartFlag) {
                try {
                    timer.stop();
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                timer.start();
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

    public void updatePanel() {
        for (XChartPanel graph : graphs) {
            XYChart chart = (XYChart) graph.getChart();
            chart.getStyler().setYAxisMin(chart.getStyler().getYAxisMin() * 1.5);
            currentMinY = chart.getStyler().getYAxisMin();
        }
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
        ButtonsPanel.getInstance().updateParams(paramNumber, xCoordinates);
        chart.removeSeries("Param " + paramNumber);
        chart.addSeries("Param " + paramNumber, xCoordinates, yCoordinates);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = (1000 * 60 / speed);
        timer.setDelay(this.speed);
    }

}
