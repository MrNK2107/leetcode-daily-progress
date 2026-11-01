# 453. Minimum Moves to Equal Array Elements

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/minimum-moves-to-equal-array-elements/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-01-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Minimum Moves to Equal Array Elements](https://leetcode.com/problems/minimum-moves-to-equal-array-elements/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

Given an integer array 
nums
 of size 
n
, return 
the minimum number of moves required to make all array elements equal
.


In one move, you can increment 
n - 1
 elements of the array by 
1
.


 


Example 1:




Input:
 nums = [1,2,3]

Output:
 3

Explanation:
 Only three moves are needed (remember each move increments two elements):
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]



Example 2:




Input:
 nums = [1,1,1]

Output:
 0



 


Constraints:




n == nums.length


1 <= nums.length <= 10
5


-10
9
 <= nums[i] <= 10
9


The answer is guaranteed to fit in a 
32-bit
 integer.

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {

public int minMoves(int[] nums) {
        if(nums==null||nums.length<=1) return 0;
        long min=(long)nums[0];
        long sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=(long)nums[i];
            min=Math.min(min,nums[i]);
        }
        return (int)(sum-min*nums.length);
    }

}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
