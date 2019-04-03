package UI;

import Services.CoordinateCreator;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class Graph {

        public XChartPanel[] getArrayOfGraph(int numCharts) {
            XChartPanel[] graph = new XChartPanel[numCharts];
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
                graph[i] = new XChartPanel(chart);
            }
            return graph;
        }
    }

