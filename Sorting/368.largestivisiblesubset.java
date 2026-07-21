import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        // Fast primitive sorting
        Arrays.sort(nums);
        int n = nums.length;
        
        int[] dp = new int[n];
        int maxSubsetSize = 0;
        
        // Step 1: Compute maximum divisible chain lengths from right to left
        for (int i = n - 1; i >= 0; i--) {
            int maxChainAfter = 0;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] % nums[i] == 0 && dp[j] > maxChainAfter) {
                    maxChainAfter = dp[j];
                }
            }
            dp[i] = 1 + maxChainAfter;
            if (dp[i] > maxSubsetSize) {
                maxSubsetSize = dp[i];
            }
        }
        
        // Step 2: Single-pass structural filter using our DP states
        // Pre-allocating exact array size prevents underlying array copying runtime penalties
        List<Integer> result = new ArrayList<>(maxSubsetSize);
        int currentSize = maxSubsetSize;
        int prevValue = -1;
        
        for (int i = 0; i < n && currentSize > 0; i++) {
            if (dp[i] == currentSize && (prevValue == -1 || nums[i] % prevValue == 0)) {
                result.add(nums[i]);
                prevValue = nums[i];
                currentSize--;
            }
        }
        
        return result;
    }
}
