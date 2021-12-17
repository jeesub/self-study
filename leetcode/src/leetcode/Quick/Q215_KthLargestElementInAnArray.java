package leetcode.Quick;

/**
 * Q215. Kth Largest Element in an Array.
 * [Quick Selection]
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
        int pivot = nums[end];
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivot) {
                swap(nums, left++, i);
            }
        }
        swap(nums, left, end);
        return left;
    }

    private static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 7, 6, 4};
        int k = 3;
        System.out.println(findKthLargest(nums, k));
        // output: 5
    }
}
