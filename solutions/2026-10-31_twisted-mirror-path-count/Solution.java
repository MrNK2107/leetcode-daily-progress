import java.util.*;

class Solution {
    /**
     * Calculates the number of unique paths from (0, 0) to (R-1, C-1) in a grid.
     * Assumes standard movement (down or right).
     * Assumes that grid[i][j] == 1 means the cell is traversable, and any other value (e.g., 0) means it's an obstacle.
     * This implementation uses Dynamic Programming based on the common interpretation of "Unique Paths" with obstacles.
     * 
     * @param grid The input grid where values determine path feasibility.
     * @return The number of unique paths (cast to int, assuming no overflow beyond Integer.MAX_VALUE).
     */
    public int uniquePaths(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int R = grid.length;
        int C = grid[0].length;

        // DP table to store the number of ways to reach (i, j)
        // Use long internally to prevent potential intermediate overflow during summation.
        long[][] dp = new long[R][C];

        // 1. Initialize starting point
        if (grid[0][0] == 1) {
            dp[0][0] = 1;
        } else {
            // If the start is blocked, no paths exist.
            return 0;
        }

        // 2. Fill the first column
        for (int i = 1; i < R; i++) {
            if (grid[i][0] == 1) {
                dp[i][0] = dp[i - 1][0];
            } else {
                // Blocked cell, no paths can reach it.
                dp[i][0] = 0;
            }
        }

        // 3. Fill the first row
        for (int j = 1; j < C; j++) {
            if (grid[0][j] == 1) {
                dp[0][j] = dp[0][j - 1];
            } else {
                // Blocked cell, no paths can reach it.
                dp[0][j] = 0;
            }
        }

        // 4. Fill the rest of the DP table
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (grid[i][j] == 1) {
                    // Paths can come from above (down move) or from the left (right move).
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    // Obstacle, no paths can reach it.
                    dp[i][j] = 0;
                }
            }
        }

        // The result is the number of ways to reach the bottom-right corner.
        // Since the return type is int, we cast the long result back, assuming it fits.
        return (int) dp[R - 1][C - 1];
    }
}