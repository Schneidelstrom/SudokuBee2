import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class GenerateSudoku{
	private int[][][] sudoku;
	private Random rand = new Random();

	GenerateSudoku(int[][][] solvedSudoku, int percentageOfGivens) {
		int size = solvedSudoku.length;
		this.sudoku = new int[size][size][2];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                this.sudoku[r][c][0] = 0;
                this.sudoku[r][c][1] = 1;
            }
        }

		int numGivens = (int) Math.round((size * size) * (percentageOfGivens / 100.0));

        List<int[]> cellCoords = new ArrayList<>();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                cellCoords.add(new int[]{r, c});
            }
        }

        Collections.shuffle(cellCoords, rand);

        for (int i = 0; i < numGivens && i < cellCoords.size(); i++) {
            int[] coord = cellCoords.get(i);
            int row = coord[0];
            int col = coord[1];

            this.sudoku[row][col][0] = solvedSudoku[row][col][0];
            this.sudoku[row][col][1] = 0;
        }
	}

    public static void addRandomGivens(int[][][] partialBoard, int[][][] solvedBoard, int numToAdd) {
        int size = partialBoard.length;
        List<int[]> emptyCells = new ArrayList<>();
        
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (partialBoard[r][c][0] == 0) {
                    emptyCells.add(new int[]{r, c});
                }
            }
        }

        Collections.shuffle(emptyCells);

        for (int i = 0; i < numToAdd && i < emptyCells.size(); i++) {
            int[] coord = emptyCells.get(i);
            int row = coord[0];
            int col = coord[1];
            partialBoard[row][col][0] = solvedBoard[row][col][0];
            partialBoard[row][col][1] = 0;
        }
    }

	protected int[][][] getSudoku(){
		return sudoku;
	}
}