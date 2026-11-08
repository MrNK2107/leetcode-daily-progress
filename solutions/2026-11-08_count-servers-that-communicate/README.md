# 1267. Count Servers that Communicate

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/count-servers-that-communicate/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-08-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Count Servers that Communicate](https://leetcode.com/problems/count-servers-that-communicate/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given a map of a server center, represented as a 
m * n
 integer matrix 
grid
, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.



Return the number of servers that communicate with any other server.


 


Example 1:






Input:
 grid = [[1,0],[0,1]]

Output:
 0

Explanation:
 No servers can communicate with others.


Example 2:






Input:
 grid = [[1,0],[1,1]]

Output:
 3

Explanation:
 All three servers can communicate with at least one other server.



Example 3:






Input:
 grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]

Output:
 4

Explanation:
 The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.



 


Constraints:




m == grid.length


n == grid[i].length


1 <= m <= 250


1 <= n <= 250


grid[i][j] == 0 or 1

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {
    public int countServers(int[][] grid) {
        
        int rows = grid.length;
        int cols = rows == 0 ? 0 : grid[0].length;
        int servers = 0;
        int[] countServersInRow = new int[rows];
        int[] countServersInCol = new int[cols];
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    ++countServersInRow[row];
                    ++countServersInCol[col];
                }
            }
        }
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1 && (countServersInRow[row] > 1 || countServersInCol[col] > 1)) {
                    ++servers;
                }
            }
        }
        
        return servers;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
