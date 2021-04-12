package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q84. Largest Rectangle in Histogram.
 * Stack: stores boundary.
 * peek height > curr height ? calculate the area with popped value.
 *     Why? peeked value cannot be used as a height from this point because it's too tall.
 *     Pop a value to get a height.
 *     width = curr index - peeked index (peeked index is a left boundary) - 1
 * peek height <= curr height ? do not calculate the area.
 *     Why? peeked value can be used as a height later because it's small.
 * To handle left end and right end boundary, append 0 at the first and at the end.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q84_LargestRectangleInHistogram {

    public static int largestRectangleArea(int[] heights) {
        int[] boundaries = new int[heights.length + 2];
        System.arraycopy(heights, 0, boundaries, 1, heights.length);
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        int maxArea = 0;
        for (int i = 1; i < boundaries.length; i++) {
            while (!deque.isEmpty() && boundaries[i] < boundaries[deque.peekLast()]) {
                int height = boundaries[deque.removeLast()];
                int width = i - deque.peekLast() - 1;
                int area = width * height;
                maxArea = Math.max(maxArea, area);
            }
            deque.addLast(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));
        // output: 15
    }

}
