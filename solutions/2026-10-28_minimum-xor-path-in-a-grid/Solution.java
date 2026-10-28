import java.util.*;

class Solution {
    /**
     * Calculates the minimum possible XOR value among all valid paths from (0, 0) to (m-1, n-1).
     * Uses Dynamic Programming where dp[i][j] stores the set of all achievable XOR sums at cell (i, j).
     */
    public int minCost(int[][] grid) {
        int M = grid.length;
        if (M == 0) return 0;
        int N = grid[0].length;
        if (N == 0) return 0;

        // dp[i][j] stores the set of all possible XOR sums reaching cell (i, j).
        @SuppressWarnings("unchecked")
        Set<Integer>[][] dp = new Set[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = new HashSet<>();
            }
        }

        // Base Case: (0, 0)
        dp[0][0].add(grid[0][0]);

        // Fill the first row (i=0, j>0)
        for (int j = 1; j < N; j++) {
            int G = grid[0][j];
            Set<Integer> prevSums = dp[0][j - 1];
            for (int x : prevSums) {
                dp[0][j].add(x ^ G);
            }
        }

        // Fill the first column (i>0, j=0)
        for (int i = 1; i < M; i++) {
            int G = grid[i][0];
            Set<Integer> prevSums = dp[i - 1][0];
            for (int y : prevSums) {
                dp[i][0].add(y ^ G);
            }
        }

        // Fill the rest of the grid
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                int G = grid[i][j];
                Set<Integer> currentSums = dp[i][j];

                // 1. From Up: (i-1, j) -> (i, j)
                Set<Integer> fromUp = dp[i - 1][j];
                for (int x : fromUp) {
                    currentSums.add(x ^ G);
                }

                // 2. From Left: (i, j-1) -> (i, j)
                Set<Integer> fromLeft = dp[i][j - 1];
                for (int y : fromLeft) {
                    currentSums.add(y ^ G);
                }
            }
        }

        // The result is the minimum value in dp[M-1][N-1]
        Set<Integer> finalSums = dp[M - 1][N - 1];
        
        if (finalSums.isEmpty()) {
             // Should not happen given constraints M, N >= 1
             return 0; 
        }

        int minXOR = Integer.MAX_VALUE;
        for (int x : finalSums) {
            minXOR = Math.min(minXOR, x);
        }

        return minXOR;
    }
}