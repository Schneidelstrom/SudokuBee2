import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExperimentLogger {
    private PrintWriter writer;
    private String filename;

    public ExperimentLogger() {
        try {
            new java.io.File("results").mkdirs();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            this.filename = "results/experiment_" + dateFormat.format(new Date()) + ".txt";
            
            FileWriter fw = new FileWriter(this.filename, false);
            this.writer = new PrintWriter(fw);
            System.out.println("Experiment log will be saved to: " + this.filename);

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
        writer.println("Timestamp: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
        writer.println("Final Fitness: " + String.format("%.6f", fitness));
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
        return this.filename;
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
}