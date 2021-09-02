package leetcode.MapSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Q128. Longest Consecutive Sequence.
 * [Set]
 * Put every elements in a set.
 * Iterate through the elements and check if the current element is start of new sequence.
 * If so, check the length of the sequence.
 * TC: O(n), where n is the number of elements
 * SC: O(n), where n is the number of elements
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q128_LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxLength = 0;

        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }

            int length = 1;
            while (set.contains(++num)) {
                length++;
            }

            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {1, 100, 3, 2, 4, 200, 5, 300};
        System.out.println(longestConsecutive(nums));
        // output: 5
    }

}
