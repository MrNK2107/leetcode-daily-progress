import java.util.HashMap;
import java.util.Map;

class Solution {
    
    // Constants for Double Hashing
    private static final long M1 = 1_000_000_007L;
    private static final long M2 = 1_000_000_009L;
    
    // Bases chosen larger than max possible element value (10^5)
    private static final long P1 = 100003L;
    private static final long P2 = 100007L;

    // Precomputed powers and prefix hashes
    private long[] pow1;
    private long[] pow2;
    private long[] h1;
    private long[] h2;
    private int N;

    /**
     * Calculates the hash of the subarray nums[i...i+L-1] using precomputed values.
     * Returns a combined hash (H1 * M2 + H2) to minimize collisions.
     */
    private long getHash(int i, int L) {
        // Hash 1 calculation
        // H1(i, i+L-1) = (h1[i+L] - h1[i] * P1^L) mod M1
        long hash1 = h1[i + L] - (h1[i] * pow1[L]) % M1;
        if (hash1 < 0) {
            hash1 += M1;
        }

        // Hash 2 calculation
        // H2(i, i+L-1) = (h2[i+L] - h2[i] * P2^L) mod M2
        long hash2 = h2[i + L] - (h2[i] * pow2[L]) % M2;
        if (hash2 < 0) {
            hash2 += M2;
        }

        // Combine hashes into a single long key
        // Since M1 and M2 are around 10^9, their product fits in long (10^18)
        return hash1 * M2 + hash2;
    }

    /**
     * Checks if there exists at least one unique subarray of length L.
     * Time complexity: O(N)
     */
    private boolean canFindUnique(int L) {
        if (L == 0) return false;
        
        Map<Long, Integer> counts = new HashMap<>();
        
        // Iterate over all starting positions i
        for (int i = 0; i <= N - L; i++) {
            long hash = getHash(i, L);
            counts.put(hash, counts.getOrDefault(hash, 0) + 1);
        }
        
        // Check if any hash appeared exactly once
        for (int count : counts.values()) {
            if (count == 1) {
                return true;
            }
        }
        
        return false;
    }

    public int smallestUniqueSubarray(int[] nums) {
        this.N = nums.length;
        if (N == 0) return 0;

        // 1. Precomputation (O(N))
        pow1 = new long[N + 1];
        pow2 = new long[N + 1];
        h1 = new long[N + 1];
        h2 = new long[N + 1];

        pow1[0] = 1;
        pow2[0] = 1;
        
        for (int i = 1; i <= N; i++) {
            // Powers
            pow1[i] = (pow1[i - 1] * P1) % M1;
            pow2[i] = (pow2[i - 1] * P2) % M2;
            
            // Prefix Hashes (h[i] stores hash of nums[0...i-1])
            // Note: nums[i-1] is the element being added
            h1[i] = (h1[i - 1] * P1 + nums[i - 1]) % M1;
            h2[i] = (h2[i - 1] * P2 + nums[i - 1]) % M2;
        }

        // 2. Binary Search on the answer L (O(log N) iterations)
        int low = 1;
        int high = N;
        int minLength = N; // Maximum possible length is N

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canFindUnique(mid)) {
                // Found a unique subarray of length mid. Try for a smaller length.
                minLength = mid;
                high = mid - 1;
            } else {
                // No unique subarray of length mid. Must increase length.
                low = mid + 1;
            }
        }
        
        return minLength;
    }
}