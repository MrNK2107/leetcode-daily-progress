# 1569. Number of Ways to Reorder Array to Get Same BST

[![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red?style=for-the-badge)](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-18-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-Accepted-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Number of Ways to Reorder Array to Get Same BST](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/)
- **Runtime:** `56 ms`
- **Memory:** `172.3 MB`

---

## 📝 Problem Statement

Refer to problem link for description.

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
import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        // Pascal's triangle for combination table C(n, k) % MOD
        table = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            table[i][0] = table[i][i] = 1;
            for (int j = 1; j < i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        // Subtract 1 because the problem asks for the number of DIFFERENT ways to reorder nums
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        if (list.size() <= 2) return 1;

        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            if (val < root) left.add(val);
            else right.add(val);
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int total = left.size() + right.size();
        long comb = table[total][left.size()];

        return (((leftWays * rightWays) % MOD) * comb) % MOD;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
