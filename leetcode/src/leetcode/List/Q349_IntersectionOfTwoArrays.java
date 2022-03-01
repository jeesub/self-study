package leetcode.List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 349. Intersection of Two Arrays.
 * [List & HashSet]
 * Put numbers in nums1 into a HashSet.
 * Iterate through the nums2, and check if we have a number in the set.
 * TC: O(m + n)
 * SC: O(min(m, n))
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q349_IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersection(nums2, nums1);
        }
        Set<Integer> set = new HashSet<>();
        for (int each : nums1) {
            set.add(each);
        }
        List<Integer> result = new ArrayList<>();
        for (int each : nums2) {
            if (set.contains(each)) {
                result.add(each);
                set.remove(each);
            }
        }
        int[] resultArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }
}
