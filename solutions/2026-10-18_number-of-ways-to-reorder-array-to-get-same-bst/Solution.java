import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        // Pascal's triangle for combination table C(n, k) % MOD
        table = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            table[i][0] = table[i][i] = 1;
            for (int j = 1; j < i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        // Subtract 1 because the problem asks for the number of DIFFERENT ways to reorder nums
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        if (list.size() <= 2) return 1;

        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            if (val < root) left.add(val);
            else right.add(val);
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int total = left.size() + right.size();
        long comb = table[total][left.size()];

        return (((leftWays * rightWays) % MOD) * comb) % MOD;
    }
}