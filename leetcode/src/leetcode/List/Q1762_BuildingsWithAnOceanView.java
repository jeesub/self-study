package leetcode.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1762. Buildings With an Ocean View.
 * Iterate backwards and keep the maximum height.
 * If curr > max so far, curr has an ocean view.
 * TC: O(n), where n is the length of the input array.
 * SC: O(n), where n is the length of the input array.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1762_BuildingsWithAnOceanView {
    public static int[] findBuildings(int[] heights) {
        List<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] <= max) {
                continue;
            }
            list.add(i);
            max = heights[i];
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(list.size() - i - 1);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] heights = {4, 2, 3, 1};
        System.out.println(Arrays.toString(findBuildings(heights)));
        // output: [0, 2, 3]
    }
}
