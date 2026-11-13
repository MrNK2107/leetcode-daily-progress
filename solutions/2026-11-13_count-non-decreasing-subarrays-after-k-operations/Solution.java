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