# 3331. Find Subtree Sizes After Changes

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/find-subtree-sizes-after-changes/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-21-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Find Subtree Sizes After Changes](https://leetcode.com/problems/find-subtree-sizes-after-changes/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given a tree rooted at node 0 that consists of 
n
 nodes numbered from 
0
 to 
n - 1
. The tree is represented by an array 
parent
 of size 
n
, where 
parent[i]
 is the parent of node 
i
. Since node 0 is the root, 
parent[0] == -1
.


You are also given a string 
s
 of length 
n
, where 
s[i]
 is the character assigned to node 
i
.


We make the following changes on the tree 
one
 time 
simultaneously
 for all nodes 
x
 from 
1
 to 
n - 1
:




Find the 
closest
 node 
y
 to node 
x
 such that 
y
 is an ancestor of 
x
, and 
s[x] == s[y]
.


If node 
y
 does not exist, do nothing.


Otherwise, 
remove
 the edge between 
x
 and its current parent and make node 
y
 the new parent of 
x
 by adding an edge between them.




Return an array 
answer
 of size 
n
 where 
answer[i]
 is the 
size
 of the 
subtree
 rooted at node 
i
 in the 
final
 tree.


 


Example 1:




Input:
 
parent = [-1,0,0,1,1,1], s = "abaabc"


Output:
 
[6,3,1,1,1,1]


Explanation:




The parent of node 3 will change from node 1 to node 0.




Example 2:




Input:
 
parent = [-1,0,4,0,1], s = "abbba"


Output:
 
[5,2,1,1,1]


Explanation:




The following changes will happen at the same time:




The parent of node 4 will change from node 1 to node 0.


The parent of node 2 will change from node 4 to node 1.






 


Constraints:




n == parent.length == s.length


1 <= n <= 10
5


0 <= parent[i] <= n - 1
 for all 
i >= 1
.


parent[0] == -1


parent
 represents a valid tree.


s
 consists only of lowercase English letters.

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        Map<Character, Integer> lastSeen = new HashMap<>();
        dfsAdjustParent(0, s, adj, lastSeen, newParent);

        List<List<Integer>> finalAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            finalAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            finalAdj.get(newParent[i]).add(i);
        }

        int[] answer = new int[n];
        dfsCountSubtreeSizes(0, finalAdj, answer);
        
        return answer;
    }

    public void dfsAdjustParent(int node, String s, List<List<Integer>> adj, Map<Character, Integer> lastSeen, int[] newParent) {
        char c = s.charAt(node);
        Integer prev = lastSeen.get(c);
        if (prev != null && prev != newParent[node]) {
            newParent[node] = prev;
        }

        Integer originalParent = lastSeen.put(c, node);
        for (int child : adj.get(node)) {
            dfsAdjustParent(child, s, adj, lastSeen, newParent);
        }
        if (originalParent == null) {
            lastSeen.remove(c);
        } else {
            lastSeen.put(c, originalParent);
        }
    }

    public int dfsCountSubtreeSizes(int node, List<List<Integer>> adj, int[] answer) {
        int size = 1;
        for (int child : adj.get(node)) {
            size += dfsCountSubtreeSizes(child, adj, answer);
        }
        answer[node] = size;
        return size;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
