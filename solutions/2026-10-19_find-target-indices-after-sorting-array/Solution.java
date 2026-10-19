import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    /**
     * Finds the indices of the target element after sorting the array nums.
     * 
     * @param nums The input integer array.
     * @param target The target element.
     * @return A list of sorted indices where the target appears in the sorted array.
     */
    public List<Integer> targetIndices(int[] nums, int target) {
        // 1. Sort the array non-decreasingly.
        // Since we are modifying the input structure conceptually (finding indices *after* sorting), 
        // we sort the provided array in place.
        Arrays.sort(nums);
        
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        
        // 2. Iterate through the sorted array and collect all indices where the value equals target.
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }
        
        // The list is automatically sorted because we iterate through indices in increasing order.
        return result;
    }
}