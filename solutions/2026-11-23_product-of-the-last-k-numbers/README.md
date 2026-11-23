# 1352. Product of the Last K Numbers

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/product-of-the-last-k-numbers/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-23-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Product of the Last K Numbers](https://leetcode.com/problems/product-of-the-last-k-numbers/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

Design an algorithm that accepts a stream of integers and retrieves the product of the last 
k
 integers of the stream.


Implement the 
ProductOfNumbers
 class:




ProductOfNumbers()
 Initializes the object with an empty stream.


void add(int num)
 Appends the integer 
num
 to the stream.


int getProduct(int k)
 Returns the product of the last 
k
 numbers in the current list. You can assume that always the current list has at least 
k
 numbers.




The test cases are generated so that, at any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.


 


Example:




Input

["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]


Output

[null,null,null,null,null,null,20,40,0,null,32]


Explanation

ProductOfNumbers productOfNumbers = new ProductOfNumbers();
productOfNumbers.add(3);        // [3]
productOfNumbers.add(0);        // [3,0]
productOfNumbers.add(2);        // [3,0,2]
productOfNumbers.add(5);        // [3,0,2,5]
productOfNumbers.add(4);        // [3,0,2,5,4]
productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
productOfNumbers.add(8);        // [3,0,2,5,4,8]
productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32 



 


Constraints:




0 <= num <= 100


1 <= k <= 4 * 10
4


At most 
4 * 10
4
 calls will be made to 
add
 and 
getProduct
.


The product of the stream at any point in time will fit in a 
32-bit
 integer.




 


Follow-up: 
Can you implement 
both
 
GetProduct
 and 
Add
 to work in 
O(1)
 time complexity instead of 
O(k)
 time complexity?

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {

public class ProductOfLastKNumbers {
    List<Integer> list;
    int prev;
    public ProductOfLastKNumbers() {
        list = new ArrayList();
        list.add(1);
        prev = 1;
    }

    public void add(int num) {
        //if element is > 0 create next element in a prefix product list. Update prev to be this
        //element
        if (num > 0) {
            prev*=num;
            list.add(prev);
        }
        //if this is 0  we need to reinit the structure
        else {
            list = new ArrayList();
            list.add(1);
            prev = 1;
        }
    }

    public int getProduct(int k) {
        int N = list.size();
        //in case there are not enough elements by the problem definition it can only happen if
        //we have met 0 before. In this case return 0. In all other cases we get the product from
        //division due to prefix product property. Note that list always has n + 1 elements due to 
        //initial 1, we need it to avoid outofbounds checks for edge cases
        return (k < N) ? prev / list.get(N - 1 - k) : 0;
    }
}

}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
