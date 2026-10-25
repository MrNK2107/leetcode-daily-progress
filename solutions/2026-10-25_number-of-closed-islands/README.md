# 1254. Number of Closed Islands

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/number-of-closed-islands/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-25-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

Given a 2D 
grid
 consists of 
0s
 (land) and 
1s
 (water).  An 
island
 is a maximal 4-directionally connected group of 
0
s
 and a 
closed island
 is an island 
totally
 (all left, top, right, bottom) surrounded by 
1s.


Return the number of 
closed islands
.


 


Example 1:






Input:
 grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]

Output:
 2

Explanation:
 
Islands in gray are closed because they are completely surrounded by water (group of 1s).


Example 2:






Input:
 grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]

Output:
 1



Example 3:




Input:
 grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]

Output:
 2



 


Constraints:




1 <= grid.length, grid[0].length <= 100


0 <= grid[i][j] <=1

---

## 💻 Java Solution

```java
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
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
