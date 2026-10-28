# 3882. Minimum XOR Path in a Grid

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/minimum-xor-path-in-a-grid/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-28-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Minimum XOR Path in a Grid](https://leetcode.com/problems/minimum-xor-path-in-a-grid/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given a 2D integer array 
grid
 of size 
m * n
.


You start at the 
top-left
 cell 
(0, 0)
 and want to reach the 
bottom-right
 cell 
(m - 1, n - 1)
.


At each step, you 
may
 move either 
right or down
.


The 
cost
 of a path is defined as the 
bitwise XOR
 of all the values in the cells along that path, 
including
 the start and end cells.


Return the 
minimum
 possible XOR value among all valid paths from 
(0, 0)
 to 
(m - 1, n - 1)
.


 


Example 1:




Input:
 
grid = [[1,2],[3,4]]


Output:
 
6


Explanation:


There are two valid paths:




(0, 0) → (0, 1) → (1, 1)
 with XOR: 
1 XOR 2 XOR 4 = 7


(0, 0) → (1, 0) → (1, 1)
 with XOR: 
1 XOR 3 XOR 4 = 6




The minimum XOR value among all valid paths is 6.




Example 2:




Input:
 
grid = [[6,7],[5,8]]


Output:
 
9


Explanation:


There are two valid paths:




(0, 0) → (0, 1) → (1, 1)
 with XOR: 
6 XOR 7 XOR 8 = 9


(0, 0) → (1, 0) → (1, 1)
 with XOR: 
6 XOR 5 XOR 8 = 11




The minimum XOR value among all valid paths is 9.




Example 3:




Input:
 
grid = [[2,7,5]]


Output:
 
0


Explanation:


There is only one valid path:




(0, 0) → (0, 1) → (0, 2)
 with XOR: 
2 XOR 7 XOR 5 = 0




The XOR value of this path is 0, which is the minimum possible.




 


Constraints:




1 <= m == grid.length <= 1000


1 <= n == grid[i].length <= 1000


m * n <= 1000


0 <= grid[i][j] <= 1023​

---

## 💻 Java Solution

```java
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
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
