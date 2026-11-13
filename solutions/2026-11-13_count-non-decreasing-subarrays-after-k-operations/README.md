# 3420. Count Non-Decreasing Subarrays After K Operations

[![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red?style=for-the-badge)](https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations/)
[![Language](https://img.shields.io/badge/Language-Java_21-orange?style=for-the-badge&logo=openjdk)](Solution.java)
[![Date](https://img.shields.io/badge/Solved-2026-11-13-blue?style=for-the-badge)](README.md)
[![Verdict](https://img.shields.io/badge/Verdict-ready_for_submission-brightgreen?style=for-the-badge)](README.md)

- **Problem Link:** [LeetCode - Count Non-Decreasing Subarrays After K Operations](https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations/)
- **Runtime:** `N/A`
- **Memory:** `N/A`

---

## 📝 Problem Statement

You are given an array 
nums
 of 
n
 integers and an integer 
k
.


For each subarray of 
nums
, you can apply 
up to
 
k
 operations on it. In each operation, you increment any element of the subarray by 1.


Note
 that each subarray is considered independently, meaning changes made to one subarray do not persist to another.


Return the number of subarrays that you can make 
non-decreasing
 ​​​​​after performing at most 
k
 operations.


An array is said to be 
non-decreasing
 if each element is greater than or equal to its previous element, if it exists.


 


Example 1:




Input:
 
nums = [6,3,1,2,4,4], k = 7


Output:
 
17


Explanation:


Out of all 21 possible subarrays of 
nums
, only the subarrays 
[6, 3, 1]
, 
[6, 3, 1, 2]
, 
[6, 3, 1, 2, 4]
 and 
[6, 3, 1, 2, 4, 4]
 cannot be made non-decreasing after applying up to k = 7 operations. Thus, the number of non-decreasing subarrays is 
21 - 4 = 17
.




Example 2:




Input:
 
nums = [6,3,1,3,6], k = 4


Output:
 
12


Explanation:


The subarray 
[3, 1, 3, 6]
 along with all subarrays of 
nums
 with three or fewer elements, except 
[6, 3, 1]
, can be made non-decreasing after 
k
 operations. There are 5 subarrays of a single element, 4 subarrays of two elements, and 2 subarrays of three elements except 
[6, 3, 1]
, so there are 
1 + 5 + 4 + 2 = 12
 subarrays that can be made non-decreasing.




 


Constraints:




1 <= nums.length <= 10
5


1 <= nums[i] <= 10
9


1 <= k <= 10
9

---

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private static final long INV_2 = 500_000_004L; // Modular inverse of 2 mod 10^9 + 7

    // Helper function to calculate the number of subarrays in a segment of length L, modulo MOD.
    private long countSubarrays(long L) {
        if (L <= 0) return 0;
        // Count = L * (L + 1) / 2
        long term1 = L % MOD;
        long term2 = (L + 1) % MOD;
        long result = (term1 * term2) % MOD;
        result = (result * INV_2) % MOD;
        return result;
    }

    // Represents a maximal non-decreasing segment
    private static class Segment {
        long length;
        Segment left;
        Segment right;

        public Segment(long length) {
            this.length = length;
            this.left = null;
            this.right = null;
        }
    }

    // Represents a potential merge operation
    private static class MergeCandidate implements Comparable<MergeCandidate> {
        long gain;
        Segment leftSegment;
        Segment rightSegment;

        public MergeCandidate(long gain, Segment leftSegment, Segment rightSegment) {
            this.gain = gain;
            this.leftSegment = leftSegment;
            this.rightSegment = rightSegment;
        }

        // Max Heap based on gain
        @Override
        public int compareTo(MergeCandidate other) {
            return Long.compare(other.gain, this.gain);
        }
    }

    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int N = nums.length;
        if (N == 0) return 0;

        // 1. Identify Initial Segments and Build Linked List
        List<Segment> segments = new ArrayList<>();
        
        int currentLength = 1;
        for (int i = 1; i < N; i++) {
            if (nums[i] >= nums[i - 1]) {
                currentLength++;
            } else {
                // Segment ends
                Segment s = new Segment(currentLength);
                segments.add(s);
                
                // Start new segment
                currentLength = 1;
            }
        }
        // Add the last segment
        Segment lastSegment = new Segment(currentLength);
        segments.add(lastSegment);

        // Link segments
        for (int i = 0; i < segments.size(); i++) {
            if (i > 0) {
                segments.get(i).left = segments.get(i - 1);
            }
            if (i < segments.size() - 1) {
                segments.get(i).right = segments.get(i + 1);
            }
        }

        // 2. Initialize Total Count and Priority Queue
        long totalCount = 0;
        for (Segment s : segments) {
            totalCount = (totalCount + countSubarrays(s.length)) % MOD;
        }

        PriorityQueue<MergeCandidate> pq = new PriorityQueue<>();

        // 3. Populate PQ with initial adjacent merges
        Segment current = segments.get(0);
        while (current.right != null) {
            Segment next = current.right;
            
            // Calculate gain: Gain = Count(L+R) - Count(L) - Count(R)
            long L = current.length;
            long R = next.length;
            
            long countLR = countSubarrays(L + R);
            long countL = countSubarrays(L);
            long countR = countSubarrays(R);
            
            // Gain = (countLR - countL - countR) mod MOD
            long gain = (countLR - countL - countR + 2 * MOD) % MOD;
            
            pq.add(new MergeCandidate(gain, current, next));
            
            current = next;
        }

        // 4. Simulation Loop (Greedy Merging)
        for (int op = 0; op < k && !pq.isEmpty(); op++) {
            MergeCandidate candidate = pq.poll();
            Segment S1 = candidate.leftSegment;
            Segment S2 = candidate.rightSegment;

            // Check if the segments are still adjacent and valid (i.e., haven't been merged already)
            if (S1.right != S2 || S2.left != S1) {
                continue; // Stale candidate
            }

            // Perform Merge
            long L = S1.length;
            long R = S2.length;
            long newLength = L + R;

            // Update total count
            // totalCount = totalCount + gain
            totalCount = (totalCount + candidate.gain) % MOD;

            // Create the new merged segment S_new
            Segment S_new = new Segment(newLength);
            
            // Link S_new into the list
            Segment S_left = S1.left;
            Segment S_right = S2.right;

            S_new.left = S_left;
            S_new.right = S_right;

            if (S_left != null) {
                S_left.right = S_new;
            }
            if (S_right != null) {
                S_right.left = S_new;
            }

            // 5. Check for new potential merges involving S_new
            
            // Check merge with S_left
            if (S_left != null) {
                Segment SL = S_left;
                long gain = calculateMergeGain(SL.length, newLength);
                pq.add(new MergeCandidate(gain, SL, S_new));
            }

            // Check merge with S_right
            if (S_right != null) {
                Segment SR = S_right;
                long gain = calculateMergeGain(newLength, SR.length);
                pq.add(new MergeCandidate(gain, S_new, SR));
            }
        }

        return totalCount;
    }

    // Helper function to calculate gain for merging two segments of lengths L and R
    private long calculateMergeGain(long L, long R) {
        long countLR = countSubarrays(L + R);
        long countL = countSubarrays(L);
        long countR = countSubarrays(R);
        
        // Gain = (countLR - countL - countR) mod MOD
        // We add 2*MOD to ensure the result is positive before the final modulo operation
        return (countLR - countL - countR + 2 * MOD) % MOD;
    }
}
```

---
*Auto-generated and documented via Gemma 4 on RTX 5060 GPU.*
