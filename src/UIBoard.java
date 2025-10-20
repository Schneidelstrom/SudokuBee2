import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Cursor;

public class UIBoard{
	private JPanel pane;
	private int sudokuArray[][][];
	private int size, startX, startY, inc, btnX, btnY, ans;
	protected JButton btn[][];
	private generalPanel gp=new generalPanel();
	UIBoard(){}
	UIBoard(int sudokuArray[][][], JPanel pane){
		this.pane=pane;
		this.sudokuArray=sudokuArray;
		setConstants(false);
		}
	UIBoard(int sudokuArray[][][],boolean isNull, JPanel pane){
		this.pane=pane;
		this.sudokuArray=sudokuArray;
		ans=0;
		if(isNull)
			fill();
		setConstants(true);
		}
	private void fill(){
		size=sudokuArray.length;
		for(int ctr=0; ctr<size;ctr++){
			for(int count=0; count<size;count++){
				sudokuArray[ctr][count][0]=0;
				sudokuArray[ctr][count][1]=1;
				}
			}
		}

    private void setConstants(boolean setCursor) {
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

        btn=new JButton[size][size];

        for (int ctr = 0, Y = startY; ctr < size; ctr++, Y += inc) {
            for (int count = 0, X = startX; count < size; count++, X += inc) {
                String backgroundFile = getBackgroundImage(ctr, count, size);
				
                gp.addLabel(pane, "img/board/" + "/" + backgroundFile, X, Y, inc, inc);

                String imgType = (sudokuArray[ctr][count][1] == 0) ? "given" : "normal";
                String numberImagePath = "img/box/" + size + "x" + size + "/" + imgType + "/" + sudokuArray[ctr][count][0] + ".png";
                btn[ctr][count] = gp.gameButton(pane, numberImagePath, X, Y, inc, inc);

				pane.setComponentZOrder(btn[ctr][count], 0);

                if (setCursor && imgType.equals("normal")) btn[ctr][count].setCursor(new Cursor(Cursor.HAND_CURSOR));
                else btn[ctr][count].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

                if (sudokuArray[ctr][count][0] != 0) ans++;
            }
        }
    }

	protected JButton getButton(){
		return btn[btnX][btnY];
		}
	protected int getStatus(int x, int y){
		return sudokuArray[x][y][1];
		}
	protected int[][][] getSudokuArray(){
		return sudokuArray;
		}
	protected void changeCursor(){
		for(int row=0; row<size; row++){
			for(int col=0; col<size; col++){
				btn[row][col].setCursor(new Cursor(0));
				btn[col][row].setCursor(new Cursor(0));
				}
			}
		}
	protected void changePic(){
		for(int row=0; row<size; row++){
			for(int col=row; col<size; col++){
				if(sudokuArray[row][col][1]==1)
					sudokuArray[row][col][0]=0;
				if(sudokuArray[col][row][1]==1)
					sudokuArray[col][row][0]=0;
				}
			}
		}
	protected void setSudoku(int solution[][][]){
		sudokuArray=solution;
		}
	protected void setSudokuArray(int value, boolean isAns, int x, int y){
		if(sudokuArray[x][y][0]==0 && value!=0)
			ans++;
		if(sudokuArray[x][y][0]!=0 && value==0)
			ans--;
		sudokuArray[x][y][0]=value;
		int num=1;
		if(!isAns && value!=0)
			num=0;
		sudokuArray[x][y][1]=num;
		sudokuArray[x][y][0]=value;
		}
	protected int getValue(int x, int y){
		return sudokuArray[x][y][0];
		}
	protected int getSize(){
		return size;
		}
	protected int getAns(){
		return ans;
		}
	protected void decompose(){
		pane.removeAll();
		sudokuArray=null;
		btn=null;
		gp=null;
	}
	
	private String getBackgroundImage(int row, int col, int size) {
        int subgridDim = (int) Math.sqrt(size);
        if (subgridDim == 0) return "1.png";
        int subgridRow = row / subgridDim;
        int subgridCol = col / subgridDim;
        if ((subgridRow + subgridCol) % 2 == 0) return "1.png";
        else return "2.png";
    }
}