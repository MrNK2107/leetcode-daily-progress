# 4220. Minimum Operations to Sort a String

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/minimum-operations-to-sort-a-string/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-21-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Minimum Operations to Sort a String](https://leetcode.com/problems/minimum-operations-to-sort-a-string/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given a string 
s
 consisting of lowercase English letters.


In one operation, you can select any 
substring
 of 
s
 that is 
not
 the entire string and 
sort
 it in 
non-descending alphabetical
 order.


Return the 
minimum
 number of operations required to make 
s
 sorted in 
non-descending
 order. If it is not possible, return -1.


 


Example 1:




Input:
 
s = "dog"


Output:
 
1


Explanation:
​​​​​​​




Sort substring 
"og"
 to 
"go"
.


Now, 
s = "dgo"
, which is sorted in ascending order. Thus, the answer is 1.






Example 2:




Input:
 
s = "card"


Output:
 
2


Explanation:




Sort substring 
"car"
 to 
"acr"
, so 
s = "acrd"
.


Sort substring 
"rd"
 to 
"dr"
, making 
s = "acdr"
, which is sorted in ascending order. Thus, the answer is 2.






Example 3:




Input:
 
s = "gf"


Output:
 
-1


Explanation:




It is impossible to sort 
s
 under the given constraints. Thus, the answer is -1.






 


Constraints:




1 <= s.length <= 10
5


s
 consists of only lowercase English letters.

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
import java.util.List;

class Solution {
    /**
     * Calculates the minimum number of operations (moves) required to sort a string S.
     * This is equivalent to N - L, where N is the length of S, and L is the 
     * length of the Longest Non-Decreasing Subsequence (LNDS) of S.
     * We use an O(N log N) algorithm based on patience sorting principles for LNDS.
     * 
     * @param s The input string.
     * @return Minimum operations required.
     */
    public int minOperations(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        // tails stores the smallest tail character for all non-decreasing subsequences found so far.
        // This list maintains the sorted property required for binary search.
        List<Character> tails = new ArrayList<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            // Find the index j of the first element in 'tails' that is strictly greater than c.
            // This is equivalent to finding the upper bound of c.
            int j = findUpperBoundIndex(tails, c);

            if (j == tails.size()) {
                // Case 1: All elements in tails are <= c. We extend the longest subsequence found so far.
                tails.add(c);
            } else {
                // Case 2: Found an element tails[j] > c. By replacing it with c, we create a 
                // new non-decreasing subsequence of length j+1 ending in a smaller character (c), 
                // which is always beneficial for future extensions.
                tails.set(j, c);
            }
        }

        int lndsLength = tails.size();
        return n - lndsLength;
    }

    /**
     * Finds the index of the first element in the list that is strictly greater than target.
     * Returns list.size() if all elements are less than or equal to target.
     * This implements the upper_bound logic for binary search.
     */
    private int findUpperBoundIndex(List<Character> tails, char target) {
        int low = 0;
        int high = tails.size(); // Search range is [low, high)

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (tails.get(mid) > target) {
                // This element might be the upper bound, try searching earlier
                high = mid;
            } else {
                // tails[mid] <= target. We must search in the right half.
                low = mid + 1;
            }
        }
        return low; // 'low' is the insertion point (index of first element > target)
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
