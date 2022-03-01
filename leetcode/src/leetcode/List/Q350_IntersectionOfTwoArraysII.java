package leetcode.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 350. Intersection of Two Arrays II
 * [List & HashMap]
 * build a Map<num, freq> from nums1
 * iterate through nums2 and check if number is in the map
 * if exist, add to a list and update the map (freq--)
 * TC: O(m + n)
 * SC: O(min(m, n))
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q350_IntersectionOfTwoArraysII {
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (!map.containsKey(num)) {
                continue;
            }
            list.add(num);
            map.put(num, map.get(num) - 1);
            if (map.get(num) == 0) {
                map.remove(num);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
