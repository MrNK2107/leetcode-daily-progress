# 3963. Create Grid With Exactly One Path

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-brightgreen?style=for-the-badge)](https://leetcode.com/problems/create-grid-with-exactly-one-path/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-27-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Create Grid With Exactly One Path](https://leetcode.com/problems/create-grid-with-exactly-one-path/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given two integers 
m
 and 
n
, representing the number of rows and columns of a grid.


Construct 
any
 
m x n
 grid consisting only of the characters 
'.'
 and 
'#'
, where:




'.'
 represents a free cell.


'#'
 represents an obstacle cell.




A 
valid path
 is a sequence of free cells that:




Starts at the top-left cell 
(0, 0)
.


Ends at the bottom-right cell 
(m - 1, n - 1)
.


Moves only:
	


Right, from 
(i, j)
 to 
(i, j + 1)
, or


Down, from 
(i, j)
 to 
(i + 1, j)
.








Return any grid such that there is 
exactly one valid path
 from the top-left cell to the bottom-right cell.


 


Example 1:




Input:
 
m = 2, n = 3


Output:
 
["..#","#.."]


Explanation:




The only valid path is: 
(0,0) → (0,1) → (1,1) → (1,2)




Example 2:




Input:
 
m = 3, n = 3


Output:
 
["..#","#..","##."]


Explanation:




The only valid path is: 
(0,0) → (0,1) → (1,1) → (1,2) → (2,2)




Example 3:




Input:
 
m = 1, n = 4


Output:
 
["...."]


Explanation:


The only valid path is: 
(0,0) → (0,1) → (0,2) → (0,3)




 


Constraints:




1 <= m, n <= 25

---

## 💻 Java Solution

```java
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
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
