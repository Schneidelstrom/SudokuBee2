import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class UIGenerateConfig extends generalPanel {
    private JPanel pane;
    private JLabel bg;
    protected JComboBox<String> percentageComboBox;
    protected JButton startEmpty, startCustom, cancel;

    UIGenerateConfig(JPanel pane) {
        this.pane = pane;
        pane.setOpaque(true);

        String[] percentages = new String[21];
        for (int i = 0; i <= 20; i++) {
            percentages[i] = (i * 5) + "%";
        }

        percentageComboBox = addJCombo(pane, percentages, 350, 240, 100, 30);
        percentageComboBox.setSelectedItem("10%");

        startEmpty = addButton(pane, "Generate from Empty", 425, 350, 175, 40);
        startCustom = addButton(pane, "Use Custom Board", 250, 350, 175, 40);
        cancel = addButton(pane, "img/exit/cancel.png", "img/exit/h_cancel.png", 370, 420);

        JLabel title = addLabel(pane, "Puzzle Generation Options", java.awt.Color.BLACK, 250, 150, 300, 50, 0);
        JLabel percentLabel = addLabel(pane, "Percentage of Given Cells:", java.awt.Color.BLACK, 50, 240, 300, 30, 0);

        bg = addLabel(pane, "img/bg/options.png", 100, 99); // Reused background

        pane.setVisible(true);
    }

    protected int getSelectedPercentage() {
        String selected = (String) percentageComboBox.getSelectedItem();
        if (selected != null) {
            return Integer.parseInt(selected.replace("%", ""));
        }
        return 10;
    }

    protected void decompose() {
        pane.removeAll();
        pane.setVisible(false);
        bg = null;
        percentageComboBox = null;
        startEmpty = startCustom = cancel = null;
    }
}