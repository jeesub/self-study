package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Diff Between Two Strings.
 * [DP]
 *
 * Fill the table backwards
 * if char1 = char2, dp[i][j] = dp[i + 1][j + 1]
 * else, dp[i][j] = min(dp[i + 1][j], dp[i][j + 1]) + 1
 *
 *   | A B D F F G H X
 * --------------------
 * A | 4 5 6 7 6 7 8 7
 * B | 5 4 5 6 5 6 7 6
 * C | 6 5 4 5 4 5 6 5
 * D | 5 4 3 4 3 4 5 4
 * E | 6 5 4 3 2 3 4 3
 * F | 5 4 3 2 1 2 3 2
 * G | 6 5 4 3 2 1 2 1
 * X | 7 6 5 4 3 2 1 0
 *
 * From the (0, 0), go to the (m, n)
 * when it's moving to [i + 1][j + 1], list.add(SOURCE)
 * when it's moving to [i + 1][j], list.add(-SOURCE)
 * when it's moving to [i][j + 1], list.add(+TARGET)
 *
 * TC: O(m * n)
 * SC: O(m * n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class DiffBetweenTwoStrings {
    static String[] diffBetweenTwoStrings(String source, String target) {
        int m = source.length();
        int n = target.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = dp[i + 1][n] + 1;
        }
        for (int j = n - 1; j >= 0; j--) {
            dp[m][j] = dp[m][j + 1] + 1;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (source.charAt(i) == target.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }

        List<String> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (source.charAt(i) == target.charAt(j)) {
                list.add(Character.toString(source.charAt(i)));
                i++;
                j++;
            } else if (dp[i + 1][j] <= dp[i][j + 1]) {
                list.add("-" + source.charAt(i++));
            } else {
                list.add("+" + target.charAt(j++));
            }
        }

        while (i < m) {
            list.add("-" + source.charAt(i++));
        }
        while (j < n) {
            list.add("+" + target.charAt(j++));
        }

        String[] array = new String[list.size()];
        for (int k = 0; k < list.size(); k++) {
            array[k] = list.get(k);
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(diffBetweenTwoStrings("ABCDEFG", "ABDFFGH")));
        // output: ["A", "B", "-C", "D", "-E", "F", "+F", "G", "+H"]
        System.out.println(Arrays.toString(diffBetweenTwoStrings("CABAAABBC", "CBBC")));
        // output: ["C","-A","B","-A","-A","-A","B","-B","C"]
    }
}
