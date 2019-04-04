import UI.ButtonsPanel;
import UI.Graph;
import UI.GraphPanel;
import UI.MainFrame;
import org.knowm.xchart.XChartPanel;

public class Main {

    public static void main(String[] args) {
        GraphPanel graphPanel = GraphPanel.getInstance();
        ButtonsPanel buttonPanel = new ButtonsPanel();
        new MainFrame(graphPanel, buttonPanel);
        graphPanel.run();
    }
}
