# 3619. Count Islands With Total Value Divisible by K

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-26-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Count Islands With Total Value Divisible by K](https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given an 
m x n
 matrix 
grid
 and a positive integer 
k
. An 
island
 is a group of 
positive
 integers (representing land) that are 
4-directionally
 connected (horizontally or vertically).


The 
total value
 of an island is the sum of the values of all cells in the island.


Return the number of islands with a total value 
divisible by
 
k
.


 


Example 1:






Input:
 
grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5


Output:
 
2


Explanation:


The grid contains four islands. The islands highlighted in blue have a total value that is divisible by 5, while the islands highlighted in red do not.




Example 2:






Input:
 
grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3


Output:
 
6


Explanation:


The grid contains six islands, each with a total value that is divisible by 3.




 


Constraints:




m == grid.length


n == grid[i].length


1 <= m, n <= 1000


1 <= m * n <= 10
5


0 <= grid[i][j] <= 10
6


1 <= k <= 10
6

---

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    /**
     * Counts the number of islands (connected components of positive integers) 
     * whose total value is divisible by k.
     * Uses Breadth-First Search (BFS) to find connected components and calculate their sum.
     */
    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        boolean[][] visited = new boolean[M][N];
        int count = 0;

        // Directions: right, left, down, up
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                // An island must start at a positive value cell that hasn't been visited.
                if (!visited[r][c] && grid[r][c] > 0) {
                    
                    long currentSum = 0;
                    Queue<int[]> queue = new LinkedList<>();

                    // Start BFS from (r, c)
                    queue.offer(new int[]{r, c});
                    visited[r][c] = true;
                    currentSum += grid[r][c];

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int row = current[0];
                        int col = current[1];

                        // Explore neighbors
                        for (int[] dir : directions) {
                            int nr = row + dir[0];
                            int nc = col + dir[1];

                            // Check boundaries, visited status, and if it's land (> 0)
                            if (nr >= 0 && nr < M && nc >= 0 && nc < N && 
                                !visited[nr][nc] && grid[nr][nc] > 0) {
                                
                                visited[nr][nc] = true;
                                currentSum += grid[nr][nc];
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }

                    // Check divisibility condition
                    if (currentSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
