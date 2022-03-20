package leetcode.MapSet;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. Contiguous Array.
 * [HashMap]
 * Keep track of the balance // 0: +1, 1: -1
 *   [0, 0, 0, 1, 1, 0, 0, 1]
 * 0  1  2  3  2  1  2  3  2
 *       ^                 ^ -> makes balance 0 -> [0, 1, 1, 0, 0, 1]
 * Map<balance, the first index of the balance>
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q525_ContiguousArray {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        int balance = 0;
        for (int i = 0; i < nums.length; i++) {
            balance += nums[i] == 0 ? 1 : -1;

            if (map.containsKey(balance)) {
                max = Math.max(max, i - map.get(balance));
            } else {
                map.put(balance, i);
            }
        }
        return max;
    }
}
