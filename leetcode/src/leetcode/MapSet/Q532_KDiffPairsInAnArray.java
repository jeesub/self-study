package leetcode.MapSet;

import java.util.HashMap;
import java.util.Map;

/**
 * 532. K-diff Pairs in an Array.
 * [HashMap]
 * Build a frequency map.
 * Iterate through the key set.
 * caes 1. k == 0, check if key's value > 1
 * case 2. k != 0, check if map contains key num + k
 *         // num - k will be checked on another key
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q532_KDiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int count = 0;
        if (k == 0) {
            for (int key : freqMap.keySet()) {
                if (freqMap.get(key) > 1) {
                    count++;
                }
            }
            return count;
        }

        for (int key : freqMap.keySet()) {
            if (freqMap.containsKey(key + k)) {
                count++;
            }
        }
        return count;
    }
}
