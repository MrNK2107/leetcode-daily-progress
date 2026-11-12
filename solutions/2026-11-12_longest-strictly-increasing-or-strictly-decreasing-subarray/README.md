# 3105. Longest Strictly Increasing or Strictly Decreasing Subarray

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-brightgreen?style=for-the-badge)](https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-12-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Longest Strictly Increasing or Strictly Decreasing Subarray](https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given an array of integers 
nums
. Return 
the length of the 
longest
 
subarray
 of 
nums
 which is either 
strictly increasing
 or 
strictly decreasing
.


 


Example 1:




Input:
 
nums = [1,4,3,3,2]


Output:
 
2


Explanation:


The strictly increasing subarrays of 
nums
 are 
[1]
, 
[2]
, 
[3]
, 
[3]
, 
[4]
, and 
[1,4]
.


The strictly decreasing subarrays of 
nums
 are 
[1]
, 
[2]
, 
[3]
, 
[3]
, 
[4]
, 
[3,2]
, and 
[4,3]
.


Hence, we return 
2
.




Example 2:




Input:
 
nums = [3,3,3,3]


Output:
 
1


Explanation:


The strictly increasing subarrays of 
nums
 are 
[3]
, 
[3]
, 
[3]
, and 
[3]
.


The strictly decreasing subarrays of 
nums
 are 
[3]
, 
[3]
, 
[3]
, and 
[3]
.


Hence, we return 
1
.




Example 3:




Input:
 
nums = [3,2,1]


Output:
 
3


Explanation:


The strictly increasing subarrays of 
nums
 are 
[3]
, 
[2]
, and 
[1]
.


The strictly decreasing subarrays of 
nums
 are 
[3]
, 
[2]
, 
[1]
, 
[3,2]
, 
[2,1]
, and 
[3,2,1]
.


Hence, we return 
3
.




 


Constraints:




1 <= nums.length <= 50


1 <= nums[i] <= 50

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int maxLength = 1;
        int incLength = 1;
        int decLength = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) {
                incLength++;
                decLength = 1;
            } else if(nums[i] < nums[i - 1]) {
                decLength++;
                incLength = 1;
            } else {
                incLength = 1;
                decLength = 1;
            }
            maxLength = Math.max(maxLength, Math.max(incLength, decLength));
        }
        return maxLength;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
