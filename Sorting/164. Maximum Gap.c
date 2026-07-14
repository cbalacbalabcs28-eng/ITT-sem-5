#include <stdlib.h>
#include <math.h>

#define MAX(a, b) ((a) > (b) ? (a) : (b))
#define MIN(a, b) ((a) < (b) ? (a) : (b))

int maximumGap(int* nums, int numsSize) {
    if (numsSize < 2) {
        return 0;
    }
    int min_val = nums[0];
    int max_val = nums[0];
    for (int i = 1; i < numsSize; i++) {
        if (nums[i] < min_val) min_val = nums[i];
        if (nums[i] > max_val) max_val = nums[i];
    }

    if (min_val == max_val) {
        return 0;
    }
    int bucket_size = MAX(1, (max_val - min_val) / (numsSize - 1));
    int num_buckets = (max_val - min_val) / bucket_size + 1;
    int* bucket_mins = (int*)malloc(num_buckets * sizeof(int));
    int* bucket_maxs = (int*)malloc(num_buckets * sizeof(int));
    int* is_empty = (int*)malloc(num_buckets * sizeof(int));

    for (int i = 0; i < num_buckets; i++) {
        is_empty[i] = 1;
    }
    for (int i = 0; i < numsSize; i++) {
        int idx = (nums[i] - min_val) / bucket_size;
        if (is_empty[idx]) {
            bucket_mins[idx] = nums[i];
            bucket_maxs[idx] = nums[i];
            is_empty[idx] = 0;
        } else {
            bucket_mins[idx] = MIN(bucket_mins[idx], nums[i]);
            bucket_maxs[idx] = MAX(bucket_maxs[idx], nums[i]);
        }
    }
    int max_gap = 0;
    int previous_max = min_val;
    for (int i = 0; i < num_buckets; i++) {
        if (is_empty[i]) {
            continue;
        }
        max_gap = MAX(max_gap, bucket_mins[i] - previous_max);
        previous_max = bucket_maxs[i];
    }
    free(bucket_mins);
    free(bucket_maxs);
    free(is_empty);

    return max_gap;
}
