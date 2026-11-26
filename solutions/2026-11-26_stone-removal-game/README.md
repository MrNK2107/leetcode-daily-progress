# 3360. Stone Removal Game

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-brightgreen?style=for-the-badge)](https://leetcode.com/problems/stone-removal-game/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-26-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Stone Removal Game](https://leetcode.com/problems/stone-removal-game/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

Alice and Bob are playing a game where they take turns removing stones from a pile, with 
Alice going first
.




Alice starts by removing 
exactly
 10 stones on her first turn.


For each subsequent turn, each player removes 
exactly
 1 fewer
 
stone
 
than the previous opponent.




The player who cannot make a move loses the game.


Given a positive integer 
n
, return 
true
 if Alice wins the game and 
false
 otherwise.


 


Example 1:




Input:
 
n = 12


Output:
 
true


Explanation:




Alice removes 10 stones on her first turn, leaving 2 stones for Bob.


Bob cannot remove 9 stones, so Alice wins.






Example 2:




Input:
 
n = 1


Output:
 
false


Explanation:




Alice cannot remove 10 stones, so Alice loses.






 


Constraints:




1 <= n <= 50

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {
    public boolean canAliceWin(int n) {

        int[] stones = {19, 15, 11, 7, 3};
        for (int stone : stones) {
            if (n >= stone) {
                n -= stone;
            } else {
                return n >= (stone / 2) + 1;}
        }
        return true; }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
