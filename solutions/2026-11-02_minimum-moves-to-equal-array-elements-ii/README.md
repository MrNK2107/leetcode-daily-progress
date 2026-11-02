# 462. Minimum Moves to Equal Array Elements II

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-02-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Minimum Moves to Equal Array Elements II](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/)
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


In one move, you can increment or decrement an element of the array by 
1
.


Test cases are designed so that the answer will fit in a 
32-bit
 integer.


 


Example 1:




Input:
 nums = [1,2,3]

Output:
 2

Explanation:

Only two moves are needed (remember each move increments or decrements one element):
[
1
,2,3]  =>  [2,2,
3
]  =>  [2,2,2]



Example 2:




Input:
 nums = [1,10,2,9]

Output:
 16



 


Constraints:




n == nums.length


1 <= nums.length <= 10
5


-10
9
 <= nums[i] <= 10
9

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {

public int minMoves2(int[] nums) {
	Arrays.sort(nums);
	int operations = 0, mid = nums[nums.length/2];
	for (int num: nums) operations += Math.abs(mid - num);
	return operations;
}

}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
