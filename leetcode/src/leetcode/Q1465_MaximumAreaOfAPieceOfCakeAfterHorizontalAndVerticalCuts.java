package leetcode;

import java.util.Arrays;

/**
 * Q1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
 * Iterate through the sorted cuts and get the max width and max height.
 * Return (max width * max height) % (10^9 + 7)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1465_MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {

    private static final long DENOMINATOR = 1_000_000_007;

    public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long maxHeight = (long) getMax(h, horizontalCuts);
        long maxWidth = (long) getMax(w, verticalCuts);
        return (int) ((maxHeight * maxWidth) % DENOMINATOR);
    }

    private static int getMax(int out, int[] nums) {
        Arrays.sort(nums);
        if (nums.length == 0) {
            return out;
        }
        int max = Math.max(nums[0] - 0, out - nums[nums.length - 1]);
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        return max;
    }

    public static void main(String[] args) {
        int h = 5;
        int w = 4;
        int[] horizontalCuts = {1,2,4};
        int[] verticalCuts = {1,3};
        System.out.println(maxArea(h, w, horizontalCuts, verticalCuts));
        // output: 4
    }

}
