import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UIPop extends generalPanel{
	private JPanel pane;
	private JPanel panel;
	private JLabel bg;
	protected int size, btnX, btnY;
	protected JButton cancel, erase;
	protected JButton btn[];
	protected JTextField field;
	UIPop(int size, JPanel pane){
		this.pane=pane;
		this.size=size;
		panel=addPanel(pane, 5, 84, 500,500);
		panel.setOpaque(true);

		createSudokuButtons();
		bg=addLabel(panel, "img/game control/"+size+"x"+size+".png",0,0);
        
		field.grabFocus();
		}
	
	protected void setVisible(boolean isVisible, int btnX, int btnY, int num){
		pane.setVisible(isVisible);
		if(num==0)
			field.setText("");
		else
			field.setText(num+"");
		field.grabFocus();
		this.btnX=btnX;
		this.btnY=btnY;
		}
	protected void decompose(){
		pane.removeAll();
		panel=null;
		bg=null;
		cancel=erase=null;
		for(int ctr=0; ctr<size; ctr++)
			btn[ctr]=null;
    }
	
    private void createSudokuButtons() {
		btn = new JButton[size];

		if (size == 9) {
			erase = addButton(panel, "img/box/misc/clear.png", 146, 126);
			cancel = addButton(panel, "img/box/misc/cancel.png", 293, 126);

			int[][] positions = {
				{101,182}, {174,182}, {247,182}, {320,182},
				{137,247}, {210,247}, {283,247},
				{174,312}, {247,312}
			};


			for (int i = 0; i < size; i++)
				btn[i] = addButton(panel, "img/box/9x9/normal/" + (i + 1) + ".png", positions[i][0], positions[i][1]);

			field = addTextField(panel, "", 220, 128, 40, 38);
		}

		else if (size == 16) {
			erase = addButton(panel, "img/box/misc/clear.png", 111, 85);
			cancel = addButton(panel, "img/box/misc/cancel.png", 329, 85);

			int[][] positions = {
				{79,152}, {152,152}, {225,152}, {298,152}, {371,152},
				{42,217}, {115,217}, {188,217}, {261,217}, {334,217}, {407,217},
				{79,282}, {152,282}, {225,282}, {298,282}, {371,282},
				{116,347}, {189,347}, {262,347}, {335,347}
			};

			for (int i = 0; i < size; i++)
				btn[i] = addButton(panel, "img/box/16x16/normal/" + (i + 1) + ".png", positions[i][0], positions[i][1]);

			field = addTextField(panel, "", 200, 85, 80, 38);
		}

		else if (size == 25) {
			erase = addButton(panel, "img/box/misc/clear.png", 111, 85);
			cancel = addButton(panel, "img/box/misc/cancel.png", 329, 85);

			int[][] positions = {
				{79,152}, {152,152}, {225,152}, {298,152}, {371,152},
				{42,217}, {115,217}, {188,217}, {261,217}, {334,217}, {407,217},
				{79,282}, {152,282}, {225,282}, {298,282}, {371,282},
				{42,347}, {115,347}, {188,347}, {261,347}, {334,347}, {407,347},
				{152,412}, {225,412}, {298,412}
			};

			for (int i = 0; i < size; i++)
				btn[i] = addButton(panel, "img/box/25x25/normal/" + (i + 1) + ".png", positions[i][0], positions[i][1]);

			field = addTextField(panel, "", 200, 85, 80, 38);
		}

	}


}