public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointer for the end of valid elements in nums1
        int p1 = m - 1;
        // Pointer for the end of elements in nums2
        int p2 = n - 1;
        // Pointer for the absolute end of the nums1 array
        int i = m + n - 1;

        // Merge from back to front
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[i] = nums1[p1];
                p1--;
            } else {
                nums1[i] = nums2[p2];
                p2--;
            }
            i--;
        }

        // Copy any remaining elements from nums2 if nums1 was exhausted early
        while (p2 >= 0) {
            nums1[i] = nums2[p2];
            p2--;
            i--;
        }
    }
}
