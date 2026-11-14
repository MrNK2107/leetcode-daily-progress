# 3364. Minimum Positive Sum Subarray 

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-brightgreen?style=for-the-badge)](https://leetcode.com/problems/minimum-positive-sum-subarray/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-14-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Minimum Positive Sum Subarray ](https://leetcode.com/problems/minimum-positive-sum-subarray/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given an integer array 
nums
 and 
two
 integers 
l
 and 
r
. Your task is to find the 
minimum
 sum of a 
subarray
 whose size is between 
l
 and 
r
 (inclusive) and whose sum is greater than 0.


Return the 
minimum
 sum of such a subarray. If no such subarray exists, return -1.


A 
subarray
 is a contiguous 
non-empty
 sequence of elements within an array.


 


Example 1:




Input:
 
nums = [3, -2, 1, 4], l = 2, r = 3


Output:
 
1


Explanation:


The subarrays of length between 
l = 2
 and 
r = 3
 where the sum is greater than 0 are:




[3, -2]
 with a sum of 1


[1, 4]
 with a sum of 5


[3, -2, 1]
 with a sum of 2


[-2, 1, 4]
 with a sum of 3




Out of these, the subarray 
[3, -2]
 has a sum of 1, which is the smallest positive sum. Hence, the answer is 1.




Example 2:




Input:
 
nums = [-2, 2, -3, 1], l = 2, r = 3


Output:
 
-1


Explanation:


There is no subarray of length between 
l
 and 
r
 that has a sum greater than 0. So, the answer is -1.




Example 3:




Input:
 
nums = [1, 2, 3, 4], l = 2, r = 4


Output:
 
3


Explanation:


The subarray 
[1, 2]
 has a length of 2 and the minimum sum greater than 0. So, the answer is 3.




 


Constraints:




1 <= nums.length <= 100


1 <= l <= r <= nums.length


-1000 <= nums[i] <= 1000

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            int res = (i == 0 ? 0 : pre[i - 1]) + nums.get(i);
            pre[i] = res;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int best = Integer.MAX_VALUE;
        int count = 0;
        for (int i = l - 1; i < n; i++) {
            int toAdd = i >= l ? pre[i - l] : 0;
            map.put(toAdd, map.getOrDefault(toAdd, 0) + 1);
            count++;
            if (count > r - l + 1) {
                int toRemove = i > r ? pre[i - r - 1] : 0;
                map.put(toRemove, map.get(toRemove) - 1);
                if (map.get(toRemove) == 0) map.remove(toRemove);
                count--;
            }
            Integer closest = map.lowerKey(pre[i]);
            if (closest != null) {
                best = Math.min(best, pre[i] - closest);
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
