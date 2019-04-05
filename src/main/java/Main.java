import UI.ButtonsPanel;
import UI.GraphPanel;
import UI.MainFrame;

public class Main {

    public static void main(String[] args) {
        GraphPanel graphPanel = GraphPanel.getInstance();
        ButtonsPanel buttonPanel = new ButtonsPanel();
        new MainFrame(graphPanel, buttonPanel);
        graphPanel.run();
    }
}
