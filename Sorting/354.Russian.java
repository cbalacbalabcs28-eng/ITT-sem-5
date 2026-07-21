import java.util.Arrays;

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        
        // Fast sort: Width ascending, Height descending when widths match
        // Replacing lambdas with direct comparisons improves runtime execution
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        int n = envelopes.length;
        int[] tails = new int[n];
        int len = 0;
        
        for (int[] env : envelopes) {
            int height = env[1];
            
            // Inline manual binary search beats Arrays.binarySearch overhead
            int low = 0;
            int high = len;
            
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (tails[mid] < height) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            
            // Overwrite or append the height element
            tails[low] = height;
            
            // If inserted at the very end, the size of our LIS increases
            if (low == len) {
                len++;
            }
        }
        
        return len;
    }
}
