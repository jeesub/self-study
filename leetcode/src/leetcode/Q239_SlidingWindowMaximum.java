package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Q239. Sliding Window Maximum.
 * Deque stores maximum value in window and possible next maximums.
 * PeekFirst value = the max of window.
 * While (a new number is bigger than peek last value), remove last.
 * Add a new number at last.
 * If the first number in deque is out of window, remove it.
 * max value = deque's peek first value
 * 
 * ex) nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
 * [1 3  -1] -3  5  3  6  7 ; Deque stores [3, -1]
 * 1 [3  -1  -3] 5  3  6  7 ; Deque stores [3, -1, -3]
 * 1  3 [-1  -3  5] 3  6  7 ; Deque stores [5]
 * 1  3  -1 [-3  5  3] 6  7 ; Deque stores [5, 3]
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q239_SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k - 1; i++) {
            appendWindow(deque, nums, i, k);
        }

        int[] result = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            appendWindow(deque, nums, i, k);
            result[i - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }

    private static void appendWindow(Deque<Integer> deque, int[] nums, int i, int k) {
        if (!deque.isEmpty() && deque.peekFirst() == i - k) {
            deque.removeFirst();
        }
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.removeLast();
        }
        deque.addLast(i);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
        // output: [3, 3, 5, 5, 6, 7]
    }

}
