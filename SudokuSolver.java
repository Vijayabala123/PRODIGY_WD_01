public class SudokuSolver {

    // Size of the Sudoku grid
    public static final int SIZE = 9;

    // Sudoku grid (initial unsolved puzzle)
    private int[][] grid;

    public SudokuSolver(int[][] grid) {
        this.grid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    // Method to solve the Sudoku puzzle
    public boolean solve() {
        return solveSudoku(0, 0);
    }

    // Recursive method to solve Sudoku using backtracking
    private boolean solveSudoku(int row, int col) {
        // Check if we have completed the grid
        if (row == SIZE - 1 && col == SIZE) {
            return true; // Solved
        }

        // Move to the next row if we have completed current row
        if (col == SIZE) {
            row++;
            col = 0;
        }

        // Skip filled cells
        if (grid[row][col] != 0) {
            return solveSudoku(row, col + 1);
        }

        // Try each number from 1 to 9
        for (int num = 1; num <= SIZE; num++) {
            if (isValid(row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(row, col + 1)) {
                    return true;
                }

                // Backtrack
                grid[row][col] = 0;
            }
        }

        return false;
    }

    // Check if placing num at grid[row][col] is valid
    private boolean isValid(int row, int col, int num) {
        // Check if num is not already in the current row, col, and box
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num || grid[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }

    // Method to print the solved Sudoku grid
    public void printSolution() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] unsolvedGrid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuSolver solver = new SudokuSolver(unsolvedGrid);
        if (solver.solve()) {
            System.out.println("Sudoku solved successfully:");
            solver.printSolution();
        } else {
            System.out.println("No solution exists for the given Sudoku.");
        }
    }
}
