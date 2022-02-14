package leetcode.DP;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle.
 * [DP]
 * dp[i][0] = 1
 * dp[i][last] = 1
 * dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j], while j < (j's length + 1) / 2
 * and reversely iterate the ilst and add numbers
 * if the target size is odd, skip the first element
 *
 * dp[0][0] = 1
 * dp[1][0] = 1, dp[1][1] = 1
 * dp[2][0] = 1, dp[2][1] = dp[1][0] + dp[1][1] = 2, dp[2][2] = 1
 * ...
 *
 * TC: O(n^2)
 * SC: O(n^2)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q118_PascalsTriangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevList = result.get(i - 1);
            List<Integer> currList = new ArrayList<>();
            currList.add(1);
            for (int j = 1; j < (i + 2) / 2; j++) {
                currList.add(prevList.get(j - 1) + prevList.get(j));
            }
            int k = i % 2 == 0 ? currList.size() - 2 : currList.size() - 1;
            while (k >= 0) {
                currList.add(currList.get(k--));
            }
            result.add(currList);
        }
        return result;
    }
}
