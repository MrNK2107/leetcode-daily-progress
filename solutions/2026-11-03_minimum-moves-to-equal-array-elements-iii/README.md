# 3736. Minimum Moves to Equal Array Elements III

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-brightgreen?style=for-the-badge)](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-03-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Minimum Moves to Equal Array Elements III](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given an integer array 
nums
.


In one move, you may 
increase
 the value of any single element 
nums[i]
 by 1.


Return the 
minimum total
 number of 
moves
 required so that all elements in 
nums
 become 
equal
.


 


Example 1:




Input:
 
nums = [2,1,3]


Output:
 
3


Explanation:


To make all elements equal:




Increase 
nums[0] = 2
 by 1 to make it 3.


Increase 
nums[1] = 1
 by 1 to make it 2.


Increase 
nums[1] = 2
 by 1 to make it 3.




Now, all elements of 
nums
 are equal to 3. The minimum total moves is 
3
.




Example 2:




Input:
 
nums = [4,4,5]


Output:
 
2


Explanation:


To make all elements equal:




Increase 
nums[0] = 4
 by 1 to make it 5.


Increase 
nums[1] = 4
 by 1 to make it 5.




Now, all elements of 
nums
 are equal to 5. The minimum total moves is 
2
.




 


Constraints:




1 <= nums.length <= 100


1 <= nums[i] <= 100

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int mx = 0;
        int s = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
            s += x;
        }
        return mx * n - s;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
