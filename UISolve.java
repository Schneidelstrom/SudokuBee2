import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UISolve extends generalPanel{
	private JPanel pane;
	private JLabel bg;
	private String modes[] = { "img/exit/gameMode.png", "img/exit/experimentMode.png" };
	protected int modeNum=0;
	protected JButton solve, cancel, mode;
	protected JTextField numEmployed, numOnlook, numCycles;
	private String penaltyFunctionLabels[] = {"Row/Column Conflicts", "Sum-Product (Constrained)", "Sum-Product (Unconstrained)"};
	protected int penaltyNum = 0;
    protected JButton changePenalty;
    protected JLabel penaltyDisplay;
	
	UISolve(JPanel pane) {
		this.pane = pane;
		pane.setOpaque(true);
		modeNum = 0;
		solve = addButton(pane, "img/exit/solve.png", "img/exit/h_solve.png", 275, 535);
		cancel = addButton(pane, "img/exit/cancel.png", "img/exit/h_cancel.png", 460, 535);
		mode = addButton(pane, modes[modeNum], modes[modeNum], 403, 437);
		numEmployed = addTextField(pane, "100", 470, 180, 147, 44);
		numOnlook = addTextField(pane, "200", 470, 266, 147, 44);
		numCycles = addTextField(pane, "100000", 470, 351, 147, 44);

		//addLabel(pane, "Penalty Function:", Color.BLACK, 280, 480, 150, 44, SwingConstants.LEFT);
		
		String[] penaltyImages = {
			"img/exit/rowcolumn-conflicts.png",
			"img/exit/sumproducts-constrained.png",
			"img/exit/sumproducts-unconstrained.png"
		};

		penaltyDisplay = addLabel(pane, penaltyImages[penaltyNum+2], 280, 492);
		changePicture(penaltyDisplay, penaltyImages[penaltyNum]);
		changePenalty = addButton(pane,"img/exit/change.png", "img/exit/h_change.png",585, 495);

		changePenalty.addActionListener(e -> {
			penaltyNum = (penaltyNum + 1) % penaltyImages.length;
			changePicture(penaltyDisplay, penaltyImages[penaltyNum]);
		});

		bg = addLabel(pane, "img/bg/parameters.png", 100, 0);
		pane.setVisible(true);
	}
	
	protected PenaltyType getSelectedPenaltyType() {
        switch (penaltyNum) {
            case 1:
                return PenaltyType.SUM_PRODUCT_CONSTRAINED;
            case 2:
                return PenaltyType.SUM_PRODUCT_UNCONSTRAINED;
            case 0:
            default:
                return PenaltyType.ROW_COLUMN_CONFLICTS;
        }
    }

	protected void changeMode() {
		if (modeNum == 0)
			modeNum = 1;
		else
			modeNum = 0;

		changePicture(mode, modes[modeNum]);
	}

	protected void decompose() {
		pane.removeAll();
		pane.setVisible(false);
		solve=cancel=mode=null;
		numEmployed= numOnlook= numCycles=null;
		bg = null;
		changePenalty = null;
        penaltyDisplay = null;
	}
}