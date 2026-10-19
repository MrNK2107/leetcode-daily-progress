# 2210. Find Target Indices After Sorting Array

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-brightgreen?style=for-the-badge)](https://leetcode.com/problems/find-target-indices-after-sorting-array/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-19-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Find Target Indices After Sorting Array](https://leetcode.com/problems/find-target-indices-after-sorting-array/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given a 
0-indexed
 integer array 
nums
 and a target element 
target
.


A 
target index
 is an index 
i
 such that 
nums[i] == target
.


Return 
a list of the target indices of
 
nums
 after
 sorting 
nums
 in 
non-decreasing
 order
. If there are no target indices, return 
an 
empty
 list
. The returned list must be sorted in 
increasing
 order.


 


Example 1:




Input:
 nums = [1,2,5,2,3], target = 2

Output:
 [1,2]

Explanation:
 After sorting, nums is [1,
2
,
2
,3,5].
The indices where nums[i] == 2 are 1 and 2.



Example 2:




Input:
 nums = [1,2,5,2,3], target = 3

Output:
 [3]

Explanation:
 After sorting, nums is [1,2,2,
3
,5].
The index where nums[i] == 3 is 3.



Example 3:




Input:
 nums = [1,2,5,2,3], target = 5

Output:
 [4]

Explanation:
 After sorting, nums is [1,2,2,3,
5
].
The index where nums[i] == 5 is 4.



 


Constraints:




1 <= nums.length <= 100


1 <= nums[i], target <= 100

---

## 💡 Intuition & Approach

1. **Problem Analysis:**
   - We are tasked with finding the number of distinct ways to arrange the elements such that they construct the exact same Binary Search Tree (BST) structure.
   - The root of any BST is fixed by the first element that appears in the input stream.
   - All elements strictly smaller than the root must belong to the left subtree, and all elements strictly larger must belong to the right subtree.

2. **Algorithmic Strategy:**
   - We divide elements into the left and right subtrees recursively.
   - While the relative order within the left subtree must be preserved, and the relative order within the right subtree must be preserved, elements from the left and right subtrees can be interleaved in any order.
   - The number of interleavings of two sequences of lengths $k$ and $m$ is given by the combination formula:
     $$\binom{k + m}{k} = \frac{(k + m)!}{k! \cdot m!}$$
   - We use modular inverse (via Fermat's Little Theorem) or Pascal's identity to compute combinations modulo $10^9 + 7$.

3. **Complexity:**
   - **Time Complexity:** Optimal polynomial time depending on BST split and memoization.
   - **Space Complexity:** $O(N)$ auxiliary stack and factorial table space.

---

## 💻 Java Solution

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    /**
     * Finds the indices of the target element after sorting the array nums.
     * 
     * @param nums The input integer array.
     * @param target The target element.
     * @return A list of sorted indices where the target appears in the sorted array.
     */
    public List<Integer> targetIndices(int[] nums, int target) {
        // 1. Sort the array non-decreasingly.
        // Since we are modifying the input structure conceptually (finding indices *after* sorting), 
        // we sort the provided array in place.
        Arrays.sort(nums);
        
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        
        // 2. Iterate through the sorted array and collect all indices where the value equals target.
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }
        
        // The list is automatically sorted because we iterate through indices in increasing order.
        return result;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
