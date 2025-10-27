import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

        percentageComboBox = addJCombo(pane, percentages, 380, 245, 170, 30);
        Color orangeColor = new Color(220, 159, 0);
        
        percentageComboBox.setBackground(orangeColor);
        percentageComboBox.setForeground(Color.BLACK);

        percentageComboBox.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setBackground(orangeColor);
                button.setForeground(Color.white);
                button.setText("▼");
                button.setBorder(javax.swing.BorderFactory.createLineBorder(orangeColor));
                return button;
            }
        });

        percentageComboBox.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(orangeColor);
                setForeground(Color.BLACK);
                setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                return this;
            }
        });

        Object popup = percentageComboBox.getUI().getAccessibleChild(percentageComboBox, 0);
        if (popup instanceof javax.swing.plaf.basic.BasicComboPopup) {
            javax.swing.JList<?> list = ((javax.swing.plaf.basic.BasicComboPopup) popup).getList();
            list.setBackground(orangeColor);
            list.setSelectionBackground(orangeColor);
            list.setSelectionForeground(Color.BLACK);
        }

        percentageComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(orangeColor, 2));
        percentageComboBox.setSelectedItem("10%");

        startEmpty = addButton(pane, "img/exit/generate-empty.png", "img/exit/h_generate-empty.png", 420, 365);
        startCustom = addButton(pane, "img/exit/use-custom.png", "img/exit/h_use-custom.png", 180, 365);
        cancel = addButton(pane, "img/exit/cancel.png", "img/exit/h_cancel.png", 340, 420);

        bg = addLabel(pane, "img/bg/puzzle-options.png", 100, 99); // Reused background

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