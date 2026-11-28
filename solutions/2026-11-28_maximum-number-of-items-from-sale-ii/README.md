# 3947. Maximum Number of Items From Sale II

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/maximum-number-of-items-from-sale-ii/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-28-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Maximum Number of Items From Sale II](https://leetcode.com/problems/maximum-number-of-items-from-sale-ii/)
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


There are unlimited copies of each item available for purchase. You may buy any number of copies of any items such that the total cost of the purchased copies is at most 
budget
.


After buying items, you may receive free copies according to the following rules:




Each purchased copy of item 
i
 can give you 
at most one
 free copy of another item 
j
.


The free item must satisfy 
i != j
 and 
factor
i
 divides 
factor
j
.


For each ordered pair 
(i, j)
, you can receive a free copy of item 
j
 from purchases of item 
i
 
at most once
, regardless of how many copies of item 
i
 you buy.


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
 
items = [[1,6],[2,4],[3,5]], budget = 19


Output:
 
5


Explanation:




You can buy 2 copies of item 0 and 1 copy of item 1 for a total cost of 
2 * 6 + 4 = 16
, which is not greater than 
budget = 19
.


One purchased copy of item 0 gives 1 free copy of item 1, because 
factor
0
 = 1
 divides 
factor
1
 = 2
.


The other purchased copy of item 0 gives 1 free copy of item 2, because 
factor
0
 = 1
 divides 
factor
2
 = 3
.


You leave with 3 purchased copies and 2 free copies, for a total of 5 item copies.






Example 2:




Input:
 
items = [[2,8],[1,10],[6,6],[4,12],[5,20],[5,17]], budget = 35


Output:
 
7


Explanation:




You can buy 2 copies of item 0, 1 copy of item 1, and 1 copy of item 2 for a total cost of 
2 * 8 + 10 + 6 = 32
, which is not greater than 
budget = 35
.


One purchased copy of item 0 gives 1 free copy of item 2, because 
factor
0
 = 2
 divides 
factor
2
 = 6
.


The other purchased copy of item 0 gives 1 free copy of item 3, because 
factor
0
 = 2
 divides 
factor
3
 = 4
.


The purchased copy of item 1 gives 1 free copy of item 2, because 
factor
1
 = 1
 divides 
factor
2
 = 6
.


Buying item 2 gives no free copy, because 
factor
2
 = 6
 does not divide the factor of any other item.


You leave with 4 purchased copies and 3 free copies, for a total of 7 item copies.






 


Constraints:




1 <= items.length <= 10
5


items[i] = [factor
i
, price
i
]


1 <= factor
i
 <= items.length


1 <= price
i
 <= 10
9


1 <= budget <= 10
9

---

## 💻 Java Solution

```java
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    /**
     * Finds the maximum total quantity of items that can be bought given a budget.
     * This is a greedy problem: prioritize buying items with the lowest price.
     * 
     * @param items 2D array where items[i] = [price, quantity]
     * @param budget The total budget available.
     * @return The maximum total quantity of items purchased.
     */
    public int maximumSaleItems(int[][] items, int budget) {
        if (items == null || items.length == 0 || budget <= 0) {
            return 0;
        }

        // 1. Sort items based on price (items[i][0]) in ascending order.
        // We use Comparator.comparingInt for clean sorting based on the price element.
        Arrays.sort(items, Comparator.comparingInt(a -> a[0]));

        int totalQuantity = 0;
        int remainingBudget = budget;

        // 2. Greedy selection: Iterate through items from cheapest to most expensive.
        for (int[] item : items) {
            int price = item[0];
            int quantity = item[1];

            if (remainingBudget <= 0) {
                break;
            }

            if (price == 0) {
                // If price is 0, we buy all of it as it costs nothing.
                totalQuantity += quantity;
                continue;
            }

            // Calculate how many units we can afford with the remaining budget
            int affordable = remainingBudget / price;

            // Determine the actual quantity to buy: limited by stock or affordability
            int buyQuantity = Math.min(quantity, affordable);

            // Update totals
            totalQuantity += buyQuantity;
            remainingBudget -= buyQuantity * price;
        }

        return totalQuantity;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
