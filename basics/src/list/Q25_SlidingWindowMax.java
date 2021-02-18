package list;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * List Q 25. Sliding Window Max.
 * input: int[] nums = {1, 3, -1, 5, 3, 6, 7}, int k = 3
 * [1, 3, -1, 5, 3, 6, 7]
 * [1, 3, -1]             : 3
 *    [3, -1, 5]          : 5
 *       [-1, 5, 3]       : 5
 *           [5, 3, 6]    : 6
 *              [3, 6, 7] : 7
 * 
 * Use Deque<Integer // index>
 * Two pointer walks.
 * 
 * 1. First pointer: remove an element outside of window.
 * peekFirst() == outside of window ? removeFirst()
 * 
 * 2. Second pointer: save possible max.
 * peekLast() < curr ? removeLast() // while peekLast() < curr
 * addLast(curr)
 * 
 * nums[peekFirst()] is the maximum value
 * add nums[peekFirst()] into the result list
 * 
 * [1, 3, -1, 5, 3, 6, 7]
 *    [3, -1]             : 3
 *           [5]          : 5
 *           [5, 3]       : 5
 *                 [6]    : 6
 *                    [7] : 7
 * 
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q25_SlidingWindowMax {
    public static List<Integer> findSlidingWindowMax(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int j = i - k;
            if (!deque.isEmpty() && deque.peekFirst() == j) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);

            if (j >= -1) {
                result.add(nums[deque.peekFirst()]);
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, 5, 3, 6, 7};
        int k = 3;
        System.out.println(findSlidingWindowMax(nums, k));
        // output: [3, 5, 5, 6, 7]
    }
}
