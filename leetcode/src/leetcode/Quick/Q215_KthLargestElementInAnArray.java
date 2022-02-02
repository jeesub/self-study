package leetcode.Quick;

/**
 * Q215. Kth Largest Element in an Array.
 * [Quickselect]
 * [1, 3, 5, 7, 6, 2, 4]
 *                    ^ pivot
 *        ^ should be on the right part // partition stops here.
 *        (if nums[i] > pivot, do nothing)
 *                 ^ should be on the left part // swap(curr, partition) & partition++
 *                 (if nums[i] < pivot, swap(i, partition++))
 *
 * [1, 3, 2, 7, 6, 5, 4]                        // partition is pointing 7 now
 * finished the first iteration. swap(partition, pivot number)
 *
 * [1, 3, 2, 4, 6, 5, 7]
 *           ^
 * left side: smaller than 4
 * right side: larger than 4
 *
 * TC: O(n^2) in the worst case. O(n) in the average case.
 * SC: O(1)
 *
 * [Heap]
 * In a min heap, keeps k elements.
 * Offer every element.
 * If min heap size is larger than k, pop it.
 * Return the first element of the min heap.
 * TC: O(nlogk), where n is the length of the input array.
 * SC: O(nlogk), where n is the length of the input array.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q215_KthLargestElementInAnArray {
    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, nums.length - k, 0, nums.length - 1);
    }

    private static int quickSelect(int[] nums, int k, int start, int end) {
        int partition = getPartition(nums, start, end);

        if (partition > k) {
            return quickSelect(nums, k, start, partition - 1);
        }
        if (partition < k) {
            return quickSelect(nums, k, partition + 1, end);
        }
        return nums[k];

    }

    private static int getPartition(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        }
        int pivot = nums[end];
        int partition = start;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, partition++);
            }
        }
        swap(nums, end, partition);
        return partition;
    }

    private static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 7, 6, 4};
        int k = 3;
        System.out.println(findKthLargest(nums, k));
        // output: 5
    }
}
