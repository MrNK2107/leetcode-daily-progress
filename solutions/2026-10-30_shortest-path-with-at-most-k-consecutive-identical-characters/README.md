# 3970. Shortest Path With At Most K Consecutive Identical Characters

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=for-the-badge)](https://leetcode.com/problems/shortest-path-with-at-most-k-consecutive-identical-characters/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-10-30-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Shortest Path With At Most K Consecutive Identical Characters](https://leetcode.com/problems/shortest-path-with-at-most-k-consecutive-identical-characters/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given an integer 
n
 representing the number of nodes in a 
directed weighted
 graph, numbered from 0 to 
n - 1
. This is represented by a 2D integer array 
edges
, where 
edges[i] = [u
i
, v
i
, w
i
]
 represents a directed edge from node 
u
i
 to node 
v
i
 with weight 
w
i
.


You are also given a string 
labels
 of length 
n
, where 
labels[i]
 is the character assigned to node 
i
, and an integer 
k
.


Return the 
minimum
 
total
 edge weight of a path from node 0 to node 
n - 1
 such that the concatenation of the labels of the nodes along the path contains 
at most
 
k
 
consecutive
 
identical
 characters. If no valid path exists, return -1.


 


Example 1:




Input:
 
n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = "aab", k = 1


Output:
 
3


Explanation:


The optimal valid path from node 0 to node 2 is as follows:




Use 
edges[2] = [0, 2, 3]
 to reach node 2 with a weight 
w
i
 = 3
.



The corresponding concatenation of labels is 
"ab"
, which satisfies at most 
k = 1
 consecutive identical characters. Thus, the answer is 3.


Example 2:




Input:
 
n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = "aab", k = 2


Output:
 
2


Explanation:


The optimal valid path from node 0 to node 2 is as follows:




Use 
edges[0] = [0, 1, 1]
 to reach node 1 with weight 
w
i
 = 1
.


Use 
edges[1] = [1, 2, 1]
 to reach node 2 with weight 
w
i
 = 1
.



The corresponding concatenation of labels is 
"aab"
, which satisfies at most 
k = 2
 consecutive identical characters. Thus, the answer is 2.


Example 3:




Input:
 
n = 3, edges = [[0,1,1],[1,2,1]], labels = "aaa", k = 2


Output:
 
-1


Explanation:


There is no valid path from node 0 to node 2 that satisfies at most 
k = 2
 consecutive identical characters. Thus, the answer is -1.




 


Constraints:




1 <= n == labels.length <= 5 * 10
4


0 <= edges.length <= 5 * 10
4


edges[i] == [u
i
, v
i
, w
i
]


0 <= u
i
, v
i
 <= n - 1


u
i
 != v
i


1 <= w
i
 <= 10
4


labels
 consists of lowercase English letters


1 <= k <= 50

---

## 💻 Java Solution

```java
import java.util.*;
import java.io.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(new int[] {e[1], e[2]});
        }

        int[][] dist = new int[n][k + 1];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[0][1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] {0, 0, 1});

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int d = p[0], u = p[1], cur = p[2];
            if (u == n - 1) {
                return d;
            }
            if (d > dist[u][cur]) {
                continue;
            }
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                int nxt = labels.charAt(v) != labels.charAt(u) ? 1 : cur + 1;
                int nd = d + w;
                if (nxt <= k && nd < dist[v][nxt]) {
                    dist[v][nxt] = nd;
                    pq.offer(new int[] {nd, v, nxt});
                }
            }
        }
        return -1;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
