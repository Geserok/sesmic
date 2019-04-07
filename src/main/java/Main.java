import UI.ButtonsPanel;
import UI.GraphPanel;
import UI.MainFrame;
import UI.StartFrame;

public class Main {

    public static void main(String[] args) {
        new StartFrame();
        GraphPanel graphPanel = GraphPanel.getInstance();
        ButtonsPanel.getInstance();
        MainFrame.getInstance();
        graphPanel.run();
    }
}
