package leetcode.Windows;

/**
 * 1004. Max Consecutive Ones III
 * [Windows]
 * j increases one by one
 * i increases while we have too many 0s
 * maxLength = max(maxLength, j - i + 1)
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1004_MaxConsecutiveOnesIII {
    public static int longestOnes(int[] nums, int k) {
        int maxLength = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            if (nums[j] == 0) {
                k--;
            }
            while (i < j && k < 0) {
                if (nums[i++] == 0) {
                    k++;
                }
            }
            if (k >= 0) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 0, 1, 0, 0, 1, 1};
        int k = 2;
        System.out.println(longestOnes(nums, k));
        // output: 5
    }
}
