package leetcode;

import java.util.Arrays;

/**
 * Q689. Maximum Sum of 3 Non-Overlapping Subarrays
 * sum[i] = sum(i..i + k)
 * nums =     [1,  2,  1,  2,  6,  7,  5, 1]
 * sum =      [3,  3,  3,  8,  13, 12, 6]
 * leftMax =  [3,  3,  3,  8,  13, 13, 13]
 * rightMax = [13, 13, 13, 13, 13, 12, 6]
 *                     ^   ^   ^
 *                     m   m   m (possible ms)
 * 
 *             l       m       n          (3 + 3 + 13)
 *                 l       m       n      (3 + 8 + 12, maximum)
 *                     l       m       n  (3 + 13 + 6)
 *             
 *             sum of left (pointer l): leftMax[m - k]
 *             sum of middle (pointer m): sum[m]
 *             sum of right (pointer n): rightMax[m + k]
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q689_MaximumSumOf3NonOverlappingSubarrays {

    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] sum = new int[nums.length - k + 1];
        int[] leftIndex = new int[sum.length];
        int[] rightIndex = new int[sum.length];
        initSum(nums, sum, k);
        initLeft(sum, leftIndex);
        initRight(sum, rightIndex);

        int[] result = new int[3];
        int totalSum = 0;
        for (int i = k; i < sum.length - k; i++) {
            int currSum = sum[leftIndex[i - k]] + sum[i] + sum[rightIndex[i + k]];
            if (currSum > totalSum) {
                totalSum = currSum;
                result[0] = leftIndex[i - k];
                result[1] = i;
                result[2] = rightIndex[i + k];
            }
        }
        return result;
    }

    private static void initSum(int[] nums, int[] sum, int k) {
        int acc = 0;
        for (int i = 0; i < nums.length; i++) {
            acc += nums[i];
            if (i < k - 1) {
                continue;
            }
            if (i > k - 1) {
                acc -= nums[i - k];
            }
            sum[i - k + 1] = acc;
        }
    }

    private static void initLeft(int[] sum, int[] leftIndex) {
        leftIndex[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            if (sum[leftIndex[i - 1]] < sum[i]) {
                leftIndex[i] = i;
            } else {
                leftIndex[i] = leftIndex[i - 1];
            }
        }
    }

    private static void initRight(int[] sum, int[] rightIndex) {
        rightIndex[sum.length - 1] = sum.length - 1;
        for (int i = sum.length - 2; i >= 0; i--) {
            if (sum[i] < sum[rightIndex[i + 1]]) {
                rightIndex[i] = rightIndex[i + 1];
            } else {
                rightIndex[i] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(nums, k)));
        // output: [0, 3, 5]
    }

}
