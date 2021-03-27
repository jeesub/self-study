package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Q560. Subarray Sum Equals K.
 * Use an accumulate sum Map<accumulate sum, frequency>.
 * sum(i..j) = sum(0..j) - sum(0..i).
 * The map has (0, 1) as a default.
 * Iterate through the nums (pointer j) and find points i satisfy sum(0..j) - k = sum(0..i).
 * count += number of point (i, j)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q560_SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int acc = 0;
        int count = 0;
        for (int num : nums) {
            acc += num;
            if (map.containsKey(acc - k)) {
                count += map.get(acc - k);
            }
            map.put(acc, map.getOrDefault(acc, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 1};
        int k = 0;
        System.out.println(subarraySum(nums, k));
        // output: 4
    }

}
