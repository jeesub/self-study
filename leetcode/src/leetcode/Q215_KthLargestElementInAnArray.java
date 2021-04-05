package leetcode;

import java.util.PriorityQueue;

/**
 * Q215. Kth Largest Element in an Array.
 * [Heap]
 * In a min heap, keeps k elements.
 * Offer every elements.
 * If min heap size is larger than k, pop it.
 * Return the first element of the min heap.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q215_KthLargestElementInAnArray {

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.poll();
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 7, 6, 4};
        int k = 3;
        System.out.println(findKthLargest(nums, k));
        // output: 5
    }

}
