import java.util.Arrays;

class Solution {
    /**
     * Calculates the minimum number of operations required to sort string s in non-descending order.
     * In one operation, any substring of s (not the entire string) can be sorted.
     * If impossible, returns -1.
     *
     * @param s The input string.
     * @return Minimum operations to sort the string, or -1 if impossible.
     */
    public int minOperations(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int n = s.length();
        char[] chars = s.toCharArray();
        char[] sorted = s.toCharArray();
        Arrays.sort(sorted);

        // Check if string is already sorted
        boolean isSorted = true;
        for (int i = 0; i < n; i++) {
            if (chars[i] != sorted[i]) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }

        // A string of length 2 cannot be sorted by any proper substring (< 2)
        if (n <= 2) {
            return -1;
        }

        char minChar = sorted[0];
        char maxChar = sorted[n - 1];

        // If the first character is already the minimum, we can sort s[1 ... n-1] in 1 operation.
        // If the last character is already the maximum, we can sort s[0 ... n-2] in 1 operation.
        if (chars[0] == minChar || chars[n - 1] == maxChar) {
            return 1;
        }

        // In 2 operations:
        // We can move minChar to index 0 if minChar exists in s[0 ... n-2] (sort prefix, then sort suffix).
        // Or we can move maxChar to index n-1 if maxChar exists in s[1 ... n-1] (sort suffix, then sort prefix).
        boolean canDoIn2 = false;
        for (int i = 0; i < n - 1; i++) {
            if (chars[i] == minChar) {
                canDoIn2 = true;
                break;
            }
        }
        if (!canDoIn2) {
            for (int i = 1; i < n; i++) {
                if (chars[i] == maxChar) {
                    canDoIn2 = true;
                    break;
                }
            }
        }

        // If chars[n-1] is the ONLY minChar and chars[0] is the ONLY maxChar, it requires 3 operations.
        return canDoIn2 ? 2 : 3;
    }
}