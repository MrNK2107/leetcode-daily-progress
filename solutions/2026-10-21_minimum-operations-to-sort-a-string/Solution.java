import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * Calculates the minimum number of operations (moves) required to sort a string S.
     * This is equivalent to N - L, where N is the length of S, and L is the 
     * length of the Longest Non-Decreasing Subsequence (LNDS) of S.
     * We use an O(N log N) algorithm based on patience sorting principles for LNDS.
     * 
     * @param s The input string.
     * @return Minimum operations required.
     */
    public int minOperations(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        // tails stores the smallest tail character for all non-decreasing subsequences found so far.
        // This list maintains the sorted property required for binary search.
        List<Character> tails = new ArrayList<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            // Find the index j of the first element in 'tails' that is strictly greater than c.
            // This is equivalent to finding the upper bound of c.
            int j = findUpperBoundIndex(tails, c);

            if (j == tails.size()) {
                // Case 1: All elements in tails are <= c. We extend the longest subsequence found so far.
                tails.add(c);
            } else {
                // Case 2: Found an element tails[j] > c. By replacing it with c, we create a 
                // new non-decreasing subsequence of length j+1 ending in a smaller character (c), 
                // which is always beneficial for future extensions.
                tails.set(j, c);
            }
        }

        int lndsLength = tails.size();
        return n - lndsLength;
    }

    /**
     * Finds the index of the first element in the list that is strictly greater than target.
     * Returns list.size() if all elements are less than or equal to target.
     * This implements the upper_bound logic for binary search.
     */
    private int findUpperBoundIndex(List<Character> tails, char target) {
        int low = 0;
        int high = tails.size(); // Search range is [low, high)

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (tails.get(mid) > target) {
                // This element might be the upper bound, try searching earlier
                high = mid;
            } else {
                // tails[mid] <= target. We must search in the right half.
                low = mid + 1;
            }
        }
        return low; // 'low' is the insertion point (index of first element > target)
    }
}