# 1692. Number of Ways to Reorder Array to Get Same BST

[![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red?style=for-the-badge)](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-18-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Number of Ways to Reorder Array to Get Same BST](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

Given an array 
nums
 that represents a permutation of integers from 
1
 to 
n
. We are going to construct a binary search tree (BST) by inserting the elements of 
nums
 in order into an initially empty BST. Find the number of different ways to reorder 
nums
 so that the constructed BST is identical to that formed from the original array 
nums
.




For example, given 
nums = [2,1,3]
, we will have 2 as the root, 1 as a left child, and 3 as a right child. The array 
[2,3,1]
 also yields the same BST but 
[3,2,1]
 yields a different BST.




Return 
the number of ways to reorder
 
nums
 
such that the BST formed is identical to the original BST formed from
 
nums
.


Since the answer may be very large, 
return it modulo 
10
9
 + 7
.


 


Example 1:






Input:
 nums = [2,1,3]

Output:
 1

Explanation:
 We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.



Example 2:






Input:
 nums = [3,4,5,1,2]

Output:
 5

Explanation:
 The following 5 arrays will yield the same BST: 
[3,1,2,4,5]
[3,1,4,2,5]
[3,1,4,5,2]
[3,4,1,2,5]
[3,4,1,5,2]



Example 3:






Input:
 nums = [1,2,3]

Output:
 0

Explanation:
 There are no other orderings of nums that will yield the same BST.



 


Constraints:




1 <= nums.length <= 1000


1 <= nums[i] <= nums.length


All integers in 
nums
 are 
distinct
.

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
    private static final int MOD = 1_000_000_007;
    private int[] nums;
    private int N;
    
    // Memoization table for solve(L, R)
    private long[][] memo;

    // Segment Tree implementation details
    // The tree stores the minimum index in 'nums' corresponding to values in its range.
    private int[] stMinIndex; 
    private int ST_SIZE;

    /**
     * Calculates (base^exp) % MOD
     */
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    /**
     * Calculates modular inverse using Fermat's Little Theorem.
     */
    private long modInverse(long n) {
        return power(n, MOD - 2);
    }

    // --- Combinatorics (Precomputation is not strictly necessary since N <= 1000, but good practice) ---
    private long[] fact;
    private long[] invFact;

    private void precomputeCombinations(int maxN) {
        fact = new long[maxN + 1];
        invFact = new long[maxN + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invFact[maxN] = modInverse(fact[maxN]);
        for (int i = maxN - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    /**
     * Calculates C(n, k) mod MOD.
     */
    private long combinations(int n, int k) {
        if (k < 0 || k > n) return 0;
        return (((fact[n] * invFact[k]) % MOD) * invFact[n - k]) % MOD;
    }

    // --- Segment Tree Implementation ---

    /**
     * Builds the segment tree. The leaves correspond to values 1 to N.
     * Each node stores the minimum index in nums for the range of values it covers.
     */
    private void buildST(int node, int start, int end) {
        if (start == end) {
            // Leaf node: value 'start'. Store its position in nums.
            stMinIndex[node] = -1; // Sentinel for "not found" or large index
            for (int i = 0; i < N; i++) {
                if (nums[i] == start) {
                    stMinIndex[node] = i;
                    break;
                }
            }
        } else {
            int mid = (start + end) / 2;
            buildST(2 * node, start, mid);
            buildST(2 * node + 1, mid + 1, end);
            // Internal node: minimum of children's min indices.
            stMinIndex[node] = Math.min(stMinIndex[2 * node], stMinIndex[2 * node + 1]);
        }
    }

    /**
     * Queries the segment tree for the minimum index corresponding to values in [L, R].
     * Returns N if no element is found (since indices are 0 to N-1).
     */
    private int queryST(int node, int start, int end, int L, int R) {
        // Range outside query range
        if (start > R || end < L) {
            return N; // Sentinel value larger than any valid index
        }
        // Range fully within query range
        if (L <= start && end <= R) {
            return stMinIndex[node];
        }

        int mid = (start + end) / 2;
        int p1 = queryST(2 * node, start, mid, L, R);
        int p2 = queryST(2 * node + 1, mid + 1, end, L, R);

        return Math.min(p1, p2);
    }


    /**
     * Solves the problem recursively using DP/Memoization on value ranges [L, R].
     * Returns the number of ways to order elements {L, ..., R} to form the target BST structure.
     */
    private long solve(int L, int R) {
        if (L > R) {
            return 1; // Empty set, one way (empty sequence)
        }
        if (memo[L][R] != -1) {
            return memo[L][R];
        }

        // 1. Find the root of the subtree defined by values [L, R].
        // The root is the element in [L, R] that appears earliest in nums.
        int minIndex = queryST(1, 1, N, L, R);
        
        if (minIndex == N) {
            // Should not happen if L <= R and values are guaranteed to be present
            return 0; 
        }

        int rootVal = nums[minIndex];

        // 2. Recursive calls for left and right subtrees.
        long waysLeft = solve(L, rootVal - 1);
        long waysRight = solve(rootVal + 1, R);

        // 3. Calculate the combinatorial factor (interleaving).
        int k = rootVal - L; // Size of left set
        int m = R - rootVal; // Size of right set
        
        // Total remaining elements: k + m. We choose k positions for the left sequence.
        long combinationsFactor = combinations(k + m, k);

        // 4. Combine results: W_L * W_R * C(k+m, k)
        long result = (waysLeft * waysRight) % MOD;
        result = (result * combinationsFactor) % MOD;

        memo[L][R] = result;
        return result;
    }


    public int numOfWays(int[] nums) {
        this.nums = nums;
        this.N = nums.length;

        // 1. Precompute factorials and inverse factorials up to N-1 (max size of k+m).
        precomputeCombinations(N);

        // 2. Initialize Segment Tree structure. Size is roughly 4*N for safety.
        stMinIndex = new int[4 * (N + 1)];
        Arrays.fill(stMinIndex, N); // Initialize with sentinel value N
        buildST(1, 1, N);

        // 3. Initialize Memoization table. Values range from 1 to N.
        memo = new long[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(memo[i], -1);
        }

        // The problem asks for the ways to order all elements {1, ..., N}.
        long result = solve(1, N);

        return (int) result;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
