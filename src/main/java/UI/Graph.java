package UI;


import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

public class Graph {

    public static XChartPanel[] getArrayOfGraph(int numCharts) {
        XChartPanel[] graph = new XChartPanel[numCharts];
        for (int i = 0; i < numCharts; i++) {
            XYChart chart = new XYChartBuilder()
                    .xAxisTitle("Диапазон")
                    .yAxisTitle("")
                    .width(200)
                    .height(3000)
                    .build();

            chart.getStyler().setYAxisMin(-1000d);
            chart.getStyler().setYAxisMax(0d);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideN);
            graph[i] = new XChartPanel(chart);
        }
        return graph;
    }
}

