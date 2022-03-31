package leetcode.Quick;

/**
 * 462. Minimum Moves to Equal Array Elements II.
 * [Quickselect]
 * Find the median and calculate the differences.
 * TC: O(n^2) in the worst case & O(n) in the average case
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q462_MinimumMovesToEqualArrayElementsII {
    public int minMoves2(int[] nums) {
        int median = quickSelect(nums, nums.length / 2);
        int diffs = 0;
        for (int num : nums) {
            diffs += Math.abs(median - num);
        }
        return diffs;
    }

    private int quickSelect(int[] nums, int targetIndex) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int partition = getPartition(nums, left, right);
            if (partition == targetIndex) {
                return nums[targetIndex];
            } else if (partition < targetIndex) {
                left = partition + 1;
            } else {
                right = partition - 1;
            }
        }
        throw new RuntimeException("Cannot find the target index");
    }

    private int getPartition(int[] nums, int left, int right) {
        for (int i = left; i < right; i++) {
            if (nums[i] < nums[right]) {
                swap(nums, left++, i);
            }
        }
        swap(nums, left, right);
        return left;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
