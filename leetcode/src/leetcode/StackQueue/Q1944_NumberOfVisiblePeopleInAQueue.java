package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1944. Number of Visible People in a Queue.
 * [Stack]
 * Iterate through the heights backward.
 * While stack is not empty && heights[stack.peek] < curr height, pop & count++.
 * curr can see count people + one person in the stack.
 * // next person cannot see the people smaller than curr
 * Put curr into the stack and move on.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1944_NumberOfVisiblePeopleInAQueue {
    public static int[] canSeePersonsCount(int[] heights) {
        int[] result = new int[heights.length];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            int count = 0;
            while (!deque.isEmpty() && heights[deque.peekLast()] < heights[i]) {
                count++;
                deque.removeLast();
            }
            result[i] = count;
            if (!deque.isEmpty()) {
                result[i]++;
            }
            deque.add(i);
        }
        return result;
    }
}
