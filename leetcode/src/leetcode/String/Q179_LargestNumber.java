package leetcode.String;

import java.util.Arrays;

/**
 * 179. Largest Number.
 * [String]
 *
 * 5, 54, 53, 598
 * 598 > 5 > 54 > 53
 *       ^ as large as 55
 *
 * 1. convert numbers to strings
 * 2. sort the string array
 *    combine two numbers in two ways, and compare them
 *    if the largest number is "0", return "0"
 * 3. combine all and return it
 * TC: O(NlogN)
 * SC: O(N)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q179_LargestNumber {
    private static final String ZERO = "0";

    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, (a, b) -> {
            String ab = a + b;
            String ba = b + a;
            return ba.compareTo(ab);
        });

        if (strs[0].equals(ZERO)) {
            return ZERO;
        }

        StringBuilder sb = new StringBuilder();
        for (String each : strs) {
            sb.append(each);
        }

        return sb.toString();
    }
}
