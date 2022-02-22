package leetcode.MapSet;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Maximum Size Subarray Sum Equals k.
 * [Map]
 * sum(0..j) - sum(0..i) = sum(i + 1..j)
 * find i & j where sum(i + 1..j) = k
 * Map<accumulated sum, the first index> // default: (0, -1)
 * if map doesn't contain key acc, map.put(acc, curr index)
 * if map contains key (acc - k), max = max(max, curr index - prev index)
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q325_MaximumSizeSubarraySumEqualsK {

    public static int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        int acc = 0;
        for (int i = 0; i < nums.length; i++) {
            acc += nums[i];
            if (!map.containsKey(acc)) {
                map.put(acc, i);
            }
            if (map.containsKey(acc - k)) {
                max = Math.max(max, i - map.get(acc - k));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        System.out.println(maxSubArrayLen(nums, k));
        // output: 4
    }

}
