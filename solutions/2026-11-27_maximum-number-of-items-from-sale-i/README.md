# 3946. Maximum Number of Items From Sale I

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/maximum-number-of-items-from-sale-i/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-27-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Maximum Number of Items From Sale I](https://leetcode.com/problems/maximum-number-of-items-from-sale-i/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given a 2D integer array 
items
, where 
items[i] = [factor
i
, price
i
]
 represents the 
i
th
 item. You are also given an integer 
budget
.


There are unlimited copies of each item available for purchase.You may buy any number of copies of any items such that the total cost of the purchased copies is at most 
budget
.


After buying items, you may receive free copies according to the following rules:




For each item 
i
 that you bought 
at least one copy
 of, you receive 
one free copy
 of every item 
j
 such that 
j != i
 and 
factor
i
 divides 
factor
j
.


Buying multiple copies of the same item 
i
 does 
not
 give additional free copies through item 
i
.


The same item 
j
 can be received multiple times for free if it is received from purchases of different item types.




Return the 
maximum total number of item copies
 you can obtain, including both purchased copies and free copies, while spending at most 
budget
 on purchased items.


 


Example 1:




Input:
 
items = [[6,2],[2,6],[3,4]], budget = 9


Output:
 
4


Explanation:




You can buy 2 copies of item 0 and 1 copy of item 2 for a total cost of 
2 * 2 + 4 = 8
, which is not greater than 
budget = 9
.


Buying item 2 gives 1 free copy of item 0, because 
factor
2
 = 3
 divides 
factor
0
 = 6
.


You leave with 3 purchased copies and 1 free copy, for a total of 4 item copies.






Example 2:




Input:
 
items = [[2,4],[3,2],[4,1],[6,4],[12,4]], budget = 8


Output:
 
10


Explanation:




You can buy 1 copy of item 0, 1 copy of item 1, and 2 copies of item 2 for a total cost of 
4 + 2 + 2 * 1 = 8
.


Buying item 0 gives 1 free copy of items 2, 3, and 4.


Buying item 1 gives 1 free copy of items 3 and 4.


Buying item 2 gives 1 free copy of item 4.


Thus, you receive 6 free copies. You leave with 4 purchased copies and 6 free copies, for a total of 10 item copies.






 


Constraints:




1 <= items.length <= 1000


items[i] = [factor
i
, price
i
]


1 <= factor
i
, price
i
 <= 1500


1 <= budget <= 1500

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] f = new int[budget + 1];
        int mn = Integer.MAX_VALUE;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            mn = Math.min(mn, price);

            int cnt = 0;
            for (int[] jItem : items) {
                if (jItem[0] % factor == 0) {
                    cnt++;
                }
            }

            for (int j = budget; j >= price; j--) {
                f[j] = Math.max(f[j], f[j - price] + cnt);
            }
        }

        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            ans = Math.max(ans, f[i] + (budget - i) / mn);
        }

        return ans;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
