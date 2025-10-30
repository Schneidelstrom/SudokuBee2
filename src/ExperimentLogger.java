import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ExperimentLogger {
    private PrintWriter writer;
    private String baseFilename;

    public ExperimentLogger(String baseFilename) {
        this.baseFilename = baseFilename;
        try {
            new java.io.File("experiments").mkdirs();

            String reportFilename = "experiments/" + this.baseFilename + ".txt";

            FileWriter fw = new FileWriter(reportFilename, false);
            this.writer = new PrintWriter(fw);
            System.out.println("    -> Experiment log will be saved to: " + reportFilename);

        } catch (IOException e) {
            System.err.println("Error initializing ExperimentLogger: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void logInitialState(int[][][] initialPuzzle, PenaltyType penaltyType, int employedBees, int onlookerBees, int maxCycles) {
        if (writer == null) return;
        writer.println("=======================================");
        writer.println("    SUDOKU BEE - EXPERIMENT REPORT     ");
        writer.println("=======================================");
        writer.println();
        writer.println("----- ALGORITHM PARAMETERS -----");
        writer.println("Penalty Function: " + penaltyType.toString());
        writer.println("Employed Bees: " + employedBees);
        writer.println("Onlooker Bees: " + onlookerBees);
        writer.println("Max Cycles: " + maxCycles);
        writer.println();
        writer.println("----- INITIAL PUZZLE -----");
        writer.println(formatSudokuGrid(initialPuzzle));
        writer.flush();
    }

    public void logFinalResult(int[][][] finalSolution, double fitness, int cycles, double runtimeSeconds) {
        if (writer == null) return;
        writer.println();
        writer.println("----- EXPERIMENT RESULTS -----");
        writer.println("Termination Reason: " + (fitness == 1.0 ? "Solution Found" : "Max Cycles Reached"));
        writer.println("Final Fitness: " + String.format("%.25f", fitness));

        boolean isSolutionValid = validateSolution(finalSolution);
        writer.println("Solution Validity: " + (isSolutionValid ? "VALID" : "INVALID"));

        writer.println("Total Cycles Executed: " + cycles);
        writer.println("Total Runtime: " + String.format("%.3f", runtimeSeconds) + " seconds");
        writer.println();
        writer.println("----- FINAL SOLUTION -----");
        if (fitness == 1.0) {
            writer.println(formatSudokuGrid(finalSolution));
        } else {
            writer.println("A perfect solution was not found. Displaying the best attempt:");
            writer.println(formatSudokuGrid(finalSolution));
        }

        writer.flush();
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
    
    public String getFilename() {
        return "experiments/" + this.baseFilename + ".txt";
    }

    private String formatSudokuGrid(int[][][] grid) {
        if (grid == null) return "Grid is null.";
        StringBuilder sb = new StringBuilder();
        int size = grid.length;
        int subgridDim = (int) Math.sqrt(size);

        for (int r = 0; r < size; r++) {
            if (r > 0 && r % subgridDim == 0) {
                for (int i = 0; i < size; i++) {
                    sb.append("----");
                }
                sb.append("\n");
            }
            for (int c = 0; c < size; c++) {
                if (c > 0 && c % subgridDim == 0) {
                    sb.append("| ");
                }
                sb.append(String.format("%2d ", grid[r][c][0]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private boolean validateSolution(int[][][] solution) {
        if (solution == null) return false;
        try {
            Subgrid[] subgrids = createSubgrids(solution.length);
            Validator validator = new Validator(solution, subgrids);
            return validator.checkAnswer();
        } catch (Exception e) {
            System.err.println("An error occurred during final solution validation.");
            e.printStackTrace();
            return false;
        }
    }

    private Subgrid[] createSubgrids(int size) {
        if (size <= 0) return new Subgrid[0];
        
        Subgrid[] subgrid = new Subgrid[size];
        int subDimY = (int) Math.sqrt(size);
        
        if (subDimY == 0 || size % subDimY != 0) {
             System.err.println("Warning: Grid size is not a perfect square, subgrid validation may be inaccurate.");
             return new Subgrid[0];
        }
        int subDimX = size / subDimY;
        
        for (int ctr = 0, xCount = 0; ctr < size; ctr++, xCount++) {
            subgrid[ctr] = new Subgrid(xCount * subDimX, ((ctr / subDimY) * subDimY), subDimX, subDimY);
            if ((ctr + 1) % subDimY == 0 && ctr > 0)
                xCount = -1;
        }
        return subgrid;
    }
}