import java.util.*;
import java.io.*;

class Solution {

    public int maxSelectedElements(int[] A) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        Arrays.sort(A);
        int res = 0;
        for (int a : A) {
            dp.put(a + 1, dp.getOrDefault(a, 0) + 1);
            dp.put(a, dp.getOrDefault(a - 1, 0) + 1);
            res = Math.max(res, Math.max(dp.get(a), dp.get(a + 1)));
        }
        return res;
    }

}