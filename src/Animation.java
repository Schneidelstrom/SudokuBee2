import javax.swing.JPanel;
import javax.swing.JLabel;

public class Animation{
	private JPanel pane;
	private int sudokuArray[][][];
	private int size, startX, startY, inc, btnX, btnY, ans;
	protected JLabel btn[][][];
	private generalPanel gp=new generalPanel();
	Animation(){}
	Animation(int sudokuArray[][][], JPanel pane){
		this.pane=pane;
		this.sudokuArray=sudokuArray;
		pane.setVisible(true);
		setConstants();
		}
	private void setConstants(){
		size=sudokuArray.length;
        switch(size) {
            case 16:
                inc = 31;
                startX = 10;
                startY = 88;
                break;
            case 25:
                inc = 20;
                startX = 8;
                startY = 86;
                break;
            case 9:
            default:
                inc = 56;
                startX = 12;
                startY = 86;
                break;
        }
		btn=new JLabel[size][size][size+1];
		for (int ctr = 0, Y = startY; ctr < size; ctr++, Y += inc) {
            for (int count = 0, X = startX; count < size; count++, X += inc) {
                String backgroundFile = getBackgroundImage(ctr, count, size);
                gp.addLabel(pane, "img/board/" + "/" + backgroundFile, X, Y, inc, inc);

                String imgType = (sudokuArray[ctr][count][1] == 0) ? "given" : "normal";
                for (int counter = 0; counter <= size; counter++) {
                    String numberImagePath = "img/box/" + size + "x" + size + "/" + imgType + "/" + counter + ".png";
                    btn[ctr][count][counter] = gp.addInvisibleLabel(pane, numberImagePath, X, Y, inc, inc);

					pane.setComponentZOrder(btn[ctr][count][counter], 0);
                }

                if (imgType.equals("given")) btn[ctr][count][sudokuArray[ctr][count][0]].setVisible(true);
            }
        }
    }
	protected int[][][] getSudokuArray(){
		return sudokuArray;
		}
	protected void changePic(int solution[][][]){
		for(int row=0, row2=size-1; row<size; row++, row2--){
			for(int col=row, col2=size-1; col<size; col++, col2--){
				if(sudokuArray[row][col][1]==1){
					btn[row][col][sudokuArray[row][col][0]].setVisible(false);
					btn[row][col][solution[row][col][0]].setVisible(true);
					}
				if(sudokuArray[col][row][1]==1){
					btn[col][row][sudokuArray[col][row][0]].setVisible(false);
					btn[col][row][solution[col][row][0]].setVisible(true);
					}
				if(sudokuArray[row2][col2][1]==1){
					btn[row2][col2][sudokuArray[row2][col2][0]].setVisible(false);
					btn[row2][col2][solution[row2][col2][0]].setVisible(true);
					}
				if(sudokuArray[col2][row2][1]==1){
					btn[col2][row2][sudokuArray[col2][row2][0]].setVisible(false);
					btn[col2][row2][solution[col2][row2][0]].setVisible(true);
					}
				}
			}
		sudokuArray=solution;
		}
	protected void setSudoku(int solution[][][]){
		sudokuArray=solution;
		}
	protected void decompose(){
		pane.removeAll();
		sudokuArray=null;
		btn=null;
		gp=null;
		pane.setVisible(false);
	}
	private String getBackgroundImage(int row, int col, int size) {
        int subgridDim = (int) Math.sqrt(size);
        if (subgridDim == 0) return "1.png";
        int subgridRow = row / subgridDim;
        int subgridCol = col / subgridDim;
        return ((subgridRow + subgridCol) % 2 == 0) ? "1.png" : "2.png";
	}
}