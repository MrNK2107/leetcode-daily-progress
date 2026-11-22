# 2124. Check if All A's Appears Before All B's

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-brightgreen?style=for-the-badge)](https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-22-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Check if All A's Appears Before All B's](https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

Given a string 
s
 consisting of 
only
 the characters 
'a'
 and 
'b'
, return 
true
 
if 
every
 
'a'
 
appears before 
every
 
'b'
 in the string
. Otherwise, return 
false
.


 


Example 1:




Input:
 s = "aaabbb"

Output:
 true

Explanation:

The 'a's are at indices 0, 1, and 2, while the 'b's are at indices 3, 4, and 5.
Hence, every 'a' appears before every 'b' and we return true.



Example 2:




Input:
 s = "abab"

Output:
 false

Explanation:

There is an 'a' at index 2 and a 'b' at index 1.
Hence, not every 'a' appears before every 'b' and we return false.



Example 3:




Input:
 s = "bbb"

Output:
 true

Explanation:

There are no 'a's, hence, every 'a' appears before every 'b' and we return true.



 


Constraints:




1 <= s.length <= 100


s[i]
 is either 
'a'
 or 
'b'
.

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {

    public boolean checkString(String s) {
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i - 1) == 'b' && s.charAt(i) == 'a') {
                return false;
            }
        }
        return true;
    }

}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
