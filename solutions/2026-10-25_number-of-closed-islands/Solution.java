import java.util.*;

class Solution {
    private int R;
    private int C;

    /**
     * Helper DFS to mark connected land cells (0) as non-land (1).
     * This is used both for boundary elimination and island counting.
     */
    private void dfs(int[][] grid, int r, int c) {
        // Check bounds or if the cell is already water/marked
        if (r < 0 || r >= R || c < 0 || c >= C || grid[r][c] == 1) {
            return;
        }

        // Mark this land cell as visited/non-closed by changing it to 1
        grid[r][c] = 1; 

        // Explore neighbors
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        R = grid.length;
        C = grid[0].length;

        // Step 1: Eliminate all land components connected to the boundary.
        // We iterate over all cells on the perimeter and run DFS if we find '0'.

        // Top and Bottom rows (j=0 to C-1)
        for (int j = 0; j < C; j++) {
            if (grid[0][j] == 0) {
                dfs(grid, 0, j);
            }
            if (R > 1 && grid[R - 1][j] == 0) {
                dfs(grid, R - 1, j);
            }
        }

        // Left and Right columns (i=1 to R-2, avoiding corners already checked)
        for (int i = 1; i < R - 1; i++) {
            if (grid[i][0] == 0) {
                dfs(grid, i, 0);
            }
            if (C > 1 && grid[i][C - 1] == 0) {
                dfs(grid, i, C - 1);
            }
        }

        // Step 2: Count remaining islands. Any '0' left must belong to a closed island.
        int closedIslandCount = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 0) {
                    // Found the start of a new closed island
                    closedIslandCount++;
                    
                    // Mark all parts of this island so we don't count them again
                    dfs(grid, i, j);
                }
            }
        }

        return closedIslandCount;
    }
}