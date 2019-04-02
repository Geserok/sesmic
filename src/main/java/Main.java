import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int numCharts = 5;

        List<XYChart> charts = new ArrayList<>();

        for (int i = 0; i < numCharts; i++) {
            XYChart chart = new XYChartBuilder()
                    .xAxisTitle("Диапазон")
                    .yAxisTitle("Y")
                    .width(300)
                    .height(300)
                    .build();

            chart.getStyler().setYAxisMin(-100d);
            chart.getStyler().setYAxisMax(0d);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideN);
            double[] massY = new double[200];
            for (int j = 0; j < 200; j++) {
                massY[j] = -j;
            }
            double[] massX = getRandomWalk(200);
            XYSeries series = chart.addSeries("" + i, massX, massY);

            series.setMarker(SeriesMarkers.NONE);
            charts.add(chart);
        }
        JPanel panel0 = new XChartPanel(charts.get(0));
        JPanel panel1 = new XChartPanel(charts.get(1));
        JPanel panel2 = new XChartPanel(charts.get(2));
        JPanel panel3 = new XChartPanel(charts.get(3));
        JPanel panel4 = new XChartPanel(charts.get(4));

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 5));
        mainPanel.add(panel0);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);

        JPanel buttonPanel = new JPanel();
        JButton scrollDown = new JButton("V");
        JButton scrollUp = new JButton("^");
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(scrollUp);
        buttonPanel.add(scrollDown);
        scrollDown.addActionListener(e -> {
            charts.forEach(a -> {
                a.getStyler().setYAxisMax(-100d);
                a.getStyler().setYAxisMin(-200d);
            });
            mainPanel.repaint();
            System.out.println("Down");
        });

        scrollUp.addActionListener(e -> {
            charts.forEach(a -> {
                a.getStyler().setYAxisMax(-0d);
                a.getStyler().setYAxisMin(-100d);
            });
            mainPanel.repaint();
            System.out.println("Up");
        });
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Generates a set of random walk data
     *
     * @param numPoints
     * @return
     */
    private static double[] getRandomWalk(int numPoints) {

        double[] y = new double[numPoints];
        y[0] = 0;
        for (int i = 1; i < y.length; i++) {
            y[i] = y[i - 1] + Math.random() - 0.5;
        }
        return y;
    }

}