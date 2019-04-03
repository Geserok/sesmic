import UI.ButtonsPanel;
import UI.Graph;
import UI.GraphPanel;
import UI.MainFrame;
import org.knowm.xchart.XChartPanel;


public class Main {

    public static void main(String[] args) {

        int quantityGraphs = 5;
        Graph graph = new Graph();
        XChartPanel[] arrayOfGraph = graph.getArrayOfGraph(quantityGraphs);

        GraphPanel graphPanel = new GraphPanel(arrayOfGraph);
        ButtonsPanel buttonPanel = new ButtonsPanel();
        new MainFrame(graphPanel, buttonPanel);
        graphPanel.run();
    }
}
