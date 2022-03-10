package leetcode.DP;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 85. Maximal Rectangle.
 *
 * [Brute Force]
 * Check every possible rectangles.
 * (row1, col1) to (row2, col2)
 * TC: O(m^3 * n^3) // start points: m * n, end points: m * n, check values: m * n
 * SC: O(1)
 *
 * [Optimization 1: DP]
 * matrix =  [[1, 0, 1, 0, 0],
 *            [1, 0, 1, 1, 1],
 *            [1, 1, 1, 1, 1],
 *            [1, 0, 0, 1, 0]]
 * heights[i, j] = max height at point(i, j)
 * heights = [[1, 0, 1, 0, 0],
 *            [2, 0, 2, 1, 1],
 *            [3, 1, 3, 2, 2],
 *            [4, 0, 0, 3, 0]]
 *
 * heights[2] = [3, 1, 3, 2, 2]
 * size(a..b) = min height(a..b) * (b - a + 1)
 * check every (a..b) // TC: O(n^2)
 * TC: O(m * n^2)
 * SC: O(m * n) & can optimize it to O(n)
 *
 * [Optimization 2: Monotonous Stack]
 * current heights = heights[2] with leading 0 & ending 0
 * heights = [0, 3, 1, 3, 2, 2, 0]
 *                        ^ k
 * stack = [index 0 (height 0), index 2 (height 1), index 3 (height 3)]
 * heights[3] (height 3) cannot contribute from here(k = 4). It's too high.
 * Calculate area using heights[3] (height 3) and we'll never use it again.
 * area = width * height = ((k - 1) - (prev index + 1) + 1) * height = (3 - 2) * 3 = 3
 * heights[2] (height 1) still can contribute. Keep it.
 *
 * heights = [0, 3, 1, 3, 2, 2, 0]
 *                           ^ k
 * stack = [index 0 (height 0), index 2 (height 1), index 4 (height 2)]
 * It's better to use heights[5] (height 2) than heights[4] (height 2)
 *
 * heights = [0, 3, 1, 3, 2, 2, 0]
 *                              ^ k
 * stack = [index 0 (height 0), index 2 (height 1), index 5 (height 2)]
 * heights[5] (height 2) cannot contribute from here(k = 6). It's too high.
 * Calculate area using heights[5] (height 2).
 * height 2 can contribute from heights[3], because heights[2] = 1 is too low.
 * area = width * height = ((k - 1) - (prev index + 1) + 1) * height = (5 - 2) * 2 = 6
 *
 * stack = [index 0 (height 0), index 2 (height 1)]
 * heights[2] (height 1) cannot contribute from here(k = 6). It's too high.
 * Calculate area using heights[2] (height 1).
 * height 2 can contribute from heights[1], because heights[0] = 0 is too low.
 * area = width * height = ((k - 1) - (prev index + 1) + 1) * height = (5 - 0) * 1 = 5
 *
 * Calculating area using heights TC: O(n), SC: O(n)
 * TC: O(m * n)
 * SC: O(m * n) & can optimize it to O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q85_MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int[] heights = new int[matrix[0].length + 2];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    heights[j + 1] = 0;
                } else {
                    heights[j + 1]++;
                }
            }
            maxArea = Math.max(maxArea, calculateMaxArea(heights));
        }
        return maxArea;
    }

    private int calculateMaxArea(int[] heights) {
        int maxArea = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        for (int i = 1; i < heights.length; i++) {
            while (deque.size() > 1 && heights[i] <= heights[deque.peekLast()]) {
                int index = deque.removeLast();
                int newArea = (i - deque.peekLast() - 1) * heights[index];
                maxArea = Math.max(maxArea, newArea);
            }
            deque.add(i);
        }
        return maxArea;
    }
}
