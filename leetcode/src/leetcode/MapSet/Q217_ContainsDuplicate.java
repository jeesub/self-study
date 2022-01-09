package leetcode.MapSet;

import java.util.HashSet;
import java.util.Set;

/**
 * 217. Contains Duplicate.
 * [HashSet]
 * Iterate through the input array.
 * If curr is in a set, return true.
 * Else, put curr in the set and continue.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q217_ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 0};
        System.out.println(containsDuplicate(nums));
        // output: true
    }
}
