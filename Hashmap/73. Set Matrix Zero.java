public class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int col0 = 1; // Tracks if the first column needs to be zeroed out

        // Step 1: Scan matrix and store states in row/col headers
        for (int i = 0; i < m; i++) {
            // Check if the 0th column has any zero
            if (matrix[i][0] == 0) {
                col0 = 0;
            }
            // Scan the rest of the row cells
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 2: Apply zeroes in reverse (bottom-up, right-to-left)
        // This prevents overwriting headers before processing their dependencies
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // Update the first column marker for the row
            if (col0 == 0) {
                matrix[i][0] = 0;
            }
        }
    }
}
