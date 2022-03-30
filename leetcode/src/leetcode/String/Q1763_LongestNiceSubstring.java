package leetcode.String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1763. Longest Nice Substring.
 * [String: Divide and Conquer]
 * Y a z a B A a y Y
 *     ^ no 'Z' in the string -> partition
 *         ^ no 'b' in the string -> partition
 * nice candidates: 'Ya', 'a', and 'AayY'
 * Recursively check the candidates.
 * TC: O(nlogn)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1763_LongestNiceSubstring {
    private static final String EMPTY = "";

    public String longestNiceSubstring(String s) {
        return longestNiceSubstring(s, 0, s.length() - 1);
    }

    private String longestNiceSubstring(String s, int left, int right) {
        if (right - left < 1) {
            return EMPTY;
        }

        Set<Character> set = new HashSet<>();
        for (int i = left; i <= right; i++) {
            set.add(s.charAt(i));
        }

        List<Integer> partition = new ArrayList<>();
        partition.add(left - 1);
        for (int i = left; i <= right; i++) {
            if (set.contains(Character.toLowerCase(s.charAt(i)))
                    && set.contains(Character.toUpperCase(s.charAt(i)))) {
                continue;
            }
            partition.add(i);
        }
        partition.add(right + 1);

        if (partition.size() == 2) {
            return s.substring(left, right + 1);
        }

        String result = EMPTY;
        for (int i = 1; i < partition.size(); i++) {
            String candidate = longestNiceSubstring(s, partition.get(i - 1) + 1, partition.get(i) - 1);
            if (candidate.length() > result.length()) {
                result = candidate;
            }
        }
        return result;
    }
}
