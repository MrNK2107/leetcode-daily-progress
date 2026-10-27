import java.util.Arrays;

class Solution {
    /**
     * Creates an m x n grid such that there is exactly one path from (0, 0) to (m-1, n-1), 
     * moving only right or down. '.' represents a traversable cell, '#' represents a blocked cell.
     * The unique path is forced along the top row and then down the rightmost column.
     * 
     * @param m Number of rows (height).
     * @param n Number of columns (width).
     * @return String array representing the grid.
     */
    public String[] createGrid(int m, int n) {
        if (m <= 0 || n <= 0) {
            // Handle invalid dimensions gracefully, though constraints usually guarantee positive inputs.
            return new String[0];
        }

        String[] grid = new String[m];

        for (int r = 0; r < m; r++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int c = 0; c < n; c++) {
                // We force the path to hug the top row (r=0) and the rightmost column (c=n-1).
                // A cell (r, c) is blocked if it is not on this L-shaped path.
                // The blocking condition: r > 0 AND c < n - 1
                if (r > 0 && c < n - 1) {
                    rowBuilder.append('#');
                } else {
                    rowBuilder.append('.');
                }
            }
            grid[r] = rowBuilder.toString();
        }

        return grid;
    }
}