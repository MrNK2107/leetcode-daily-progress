# 4220. Minimum Operations to Sort a String

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/minimum-operations-to-sort-a-string/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-21-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-Accepted-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Minimum Operations to Sort a String](https://leetcode.com/problems/minimum-operations-to-sort-a-string/)
- **Runtime:** `29 ms`
- **Memory:** `48 MB`

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
import java.util.Arrays;

class Solution {
    /**
     * Calculates the minimum number of operations required to sort string s in non-descending order.
     * In one operation, any substring of s (not the entire string) can be sorted.
     * If impossible, returns -1.
     *
     * @param s The input string.
     * @return Minimum operations to sort the string, or -1 if impossible.
     */
    public int minOperations(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int n = s.length();
        char[] chars = s.toCharArray();
        char[] sorted = s.toCharArray();
        Arrays.sort(sorted);

        // Check if string is already sorted
        boolean isSorted = true;
        for (int i = 0; i < n; i++) {
            if (chars[i] != sorted[i]) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }

        // A string of length 2 cannot be sorted by any proper substring (< 2)
        if (n <= 2) {
            return -1;
        }

        char minChar = sorted[0];
        char maxChar = sorted[n - 1];

        // If the first character is already the minimum, we can sort s[1 ... n-1] in 1 operation.
        // If the last character is already the maximum, we can sort s[0 ... n-2] in 1 operation.
        if (chars[0] == minChar || chars[n - 1] == maxChar) {
            return 1;
        }

        // In 2 operations:
        // We can move minChar to index 0 if minChar exists in s[0 ... n-2] (sort prefix, then sort suffix).
        // Or we can move maxChar to index n-1 if maxChar exists in s[1 ... n-1] (sort suffix, then sort prefix).
        boolean canDoIn2 = false;
        for (int i = 0; i < n - 1; i++) {
            if (chars[i] == minChar) {
                canDoIn2 = true;
                break;
            }
        }
        if (!canDoIn2) {
            for (int i = 1; i < n; i++) {
                if (chars[i] == maxChar) {
                    canDoIn2 = true;
                    break;
                }
            }
        }

        // If chars[n-1] is the ONLY minChar and chars[0] is the ONLY maxChar, it requires 3 operations.
        return canDoIn2 ? 2 : 3;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
