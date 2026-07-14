#include <stdlib.h>
#include <stdbool.h>


int compare(const void *a, const void *b) {
    return (*(int*)a - *(int*)b);
}

void backtrack(int* nums, int numsSize, int** result, int* returnSize, int** returnColumnSizes, 
               int* currentPath, int pathIndex, bool* visited) {
    
    if (pathIndex == numsSize) {
        result[*returnSize] = (int*)malloc(numsSize * sizeof(int));
        for (int i = 0; i < numsSize; i++) {
            result[*returnSize][i] = currentPath[i];
        }
        
        (*returnColumnSizes)[*returnSize] = numsSize;
        (*returnSize)++;
        return;
    }
    
    for (int i = 0; i < numsSize; i++) {
        if (visited[i]) continue;
        
        if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
        visited[i] = true;
        currentPath[pathIndex] = nums[i];
        
     
        backtrack(nums, numsSize, result, returnSize, returnColumnSizes, currentPath, pathIndex + 1, visited);

        visited[i] = false;
    }
}

/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be highly allocated, assume caller calls free().
 */
int** permuteUnique(int* nums, int numsSize, int* returnSize, int** returnColumnSizes) {
    // 1. Sort the input array to align duplicate numbers side-by-side
    qsort(nums, numsSize, sizeof(int), compare);
    
    // 2. Allocate the upper bound of possible combinations (N!)
    int maxPermutations = 1;
    for (int i = 1; i <= numsSize; i++) {
        maxPermutations *= i;
    }
    
    int** result = (int**)malloc(maxPermutations * sizeof(int*));
    *returnColumnSizes = (int*)malloc(maxPermutations * sizeof(int));
    *returnSize = 0; // Initialize result counter
    
    // 3. Setup temporary memory arrays for path status tracking
    bool* visited = (bool*)calloc(numsSize, sizeof(bool));
    int* currentPath = (int*)malloc(numsSize * sizeof(int));
    
    // 4. Run the recursive backtracking process
    backtrack(nums, numsSize, result, returnSize, returnColumnSizes, currentPath, 0, visited);
    
    // 5. Free temporary execution structures
    free(visited);
    free(currentPath);
    
    return result;
}
