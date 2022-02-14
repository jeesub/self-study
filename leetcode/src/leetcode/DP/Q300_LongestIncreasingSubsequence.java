package leetcode.DP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 300. Longest Increasing Subsequence.
 * [DP & BS]
 * dp[i] = the LIS at the point i
 * dp[i] = max(dp[j] + 1), where j < i && nums[j] < nums[i]
 *
 * [10, 9, 2, 5, 3, 7, 101, 18]
 * dp[0] = 1 // [10]
 * dp[1] = 1 // [9]
 * dp[2] = 1 // [2]
 * dp[3] = 2 // dp[2] + 1 // [2, 5]
 * dp[4] = 2 // dp[2] + 1 // [2, 3]
 * dp[5] = 3 // dp[3] + 1 or dp[4] + 1 // [2, 5, 7] or [2, 3, 7]
 * dp[6] = 4 // dp[5] + 1 // [2, 5, 7, 101] or [2, 3, 7, 101]
 * dp[7] = 4 // dp[5] + 1 // [2, 5, 7, 18] or [2, 3, 7, 18]
 *
 * [2, 5] vs [2, 3] // [2, 3] is always better
 * [2, 3, 7, 101] vs [2, 3, 7, 18] // [2, 3, 7, 18] is always better
 * Keep track the best LIS
 * We can use Binary Search
 * if index < list.size, overwrite
 * if index >= list.isze, append
 * the best LIS is not the correct subsequence, but the length is correct
 * dp[0] = 1 // [10]
 * dp[1] = 1 // [9]
 * dp[2] = 1 // [2]
 * dp[3] = 2 // dp[2] + 1 // [2, 5]
 * dp[4] = 2 // dp[2] + 1 // [2, 3]
 * dp[5] = 3 // dp[4] + 1 // [2, 3, 7]
 * dp[6] = 4 // dp[5] + 1 // [2, 3, 7, 101]
 * dp[7] = 4 // dp[5] + 1 // [2, 3, 7, 18]
 * return list.size
 *
 * TC: O(NlogN)
 * SC: O(N)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q300_LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            int index = Collections.binarySearch(list, num);
            if (index < 0) {
                index = -index - 1;
            }
            if (index < list.size()) {
                list.set(index, num);
            } else {
                list.add(num);
            }
        }
        return list.size();
    }
}
