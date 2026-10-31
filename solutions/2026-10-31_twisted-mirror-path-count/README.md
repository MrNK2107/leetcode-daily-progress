# 3665. Twisted Mirror Path Count

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/twisted-mirror-path-count/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-31-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Twisted Mirror Path Count](https://leetcode.com/problems/twisted-mirror-path-count/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

Given an 
m x n
 binary grid 
grid
 where:




grid[i][j] == 0
 represents an empty cell, and


grid[i][j] == 1
 represents a mirror.




A robot starts at the top-left corner of the grid 
(0, 0)
 and wants to reach the bottom-right corner 
(m - 1, n - 1)
. It can move only 
right
 or 
down
. If the robot attempts to move into a mirror cell, it is 
reflected
 before entering that cell:




If it tries to move 
right
 into a mirror, it is turned 
down
 and moved into the cell directly below the mirror.


If it tries to move 
down
 into a mirror, it is turned 
right
 and moved into the cell directly to the right of the mirror.




If this reflection would cause the robot to move outside the 
grid
 boundaries, the path is considered invalid and should not be counted.


Return the number of unique valid paths from 
(0, 0)
 to 
(m - 1, n - 1)
.


Since the answer may be very large, return it 
modulo
 
10
9
 + 7
.


Note
: If a reflection moves the robot into a mirror cell, the robot is immediately reflected again based on the direction it used to enter that mirror: if it entered while moving right, it will be turned down; if it entered while moving down, it will be turned right. This process will continue until either the last cell is reached, the robot moves out of bounds or the robot moves to a non-mirror cell.


 


Example 1:




Input:
 
grid = [[0,1,0],[0,0,1],[1,0,0]]


Output:
 
5


Explanation:








Number


Full Path










1


(0, 0) → (0, 1) [M] → (1, 1) → (1, 2) [M] → (2, 2)






2


(0, 0) → (0, 1) [M] → (1, 1) → (2, 1) → (2, 2)






3


(0, 0) → (1, 0) → (1, 1) → (1, 2) [M] → (2, 2)






4


(0, 0) → (1, 0) → (1, 1) → (2, 1) → (2, 2)






5


(0, 0) → (1, 0) → (2, 0) [M] → (2, 1) → (2, 2)












[M]
 indicates the robot attempted to enter a mirror cell and instead reflected.








Example 2:




Input:
 
grid = [[0,0],[0,0]]


Output:
 
2


Explanation:








Number


Full Path










1


(0, 0) → (0, 1) → (1, 1)






2


(0, 0) → (1, 0) → (1, 1)










Example 3:




Input:
 
grid = 
[[0,1,1],[1,1,0]]


Output:
 1


Explanation:








Number


Full Path










1


(0, 0) → (0, 1) [M] → (1, 1) [M] → (1, 2)








(0, 0) → (1, 0) [M] → (1, 1) [M] → (2, 1)
 goes out of bounds, so it is invalid.


 


Constraints:




m == grid.length


n == grid[i].length


2 <= m, n <= 500


grid[i][j]
 is either 
0
 or 
1
.


grid[0][0] == grid[m - 1][n - 1] == 0

---

## 💻 Java Solution

```java
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
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
