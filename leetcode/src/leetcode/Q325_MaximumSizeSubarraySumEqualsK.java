package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Q325. Maximum Size Subarray Sum Equals k.
 * Make an accumulate sum int and accumulate sum map.
 * Map <Key: accumulate sum, Value: the smallest index of the accumulate sum>
 * Map should have <0, -1> to deal with subarray(0..i) case.
 * While making an accumulate sum, if we can find acc[i] - k in the map, it would be a candidate.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q325_MaximumSizeSubarraySumEqualsK {

    public static int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int acc = 0;
        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            acc += nums[i];
            if (map.containsKey(acc - k)) {
                longest = Math.max(longest, i - map.get(acc - k));
            }
            if (!map.containsKey(acc)) {
                map.put(acc, i);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        System.out.println(maxSubArrayLen(nums, k));
        // output: 4
    }

}
