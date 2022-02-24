package leetcode.Windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1868. Product of Two Run-Length Encoded Arrays.
 * [Two Pointers]
 * [[1,3],[2,5]]
 *   ^
 * [[4,2],[2,3],[5,3]]
 *   ^
 * num i = 1, len i = 3
 * num j = 4, len j = 2
 * new element = (num i * num j, min(len i, len j))
 * if new element's val == list.last's val ? merge : add
 * len i -= min(len i, len j), if len i == 0 ? i++ // len i = 1
 * len j -= min(len i, len j), if len j == 0 ? j++ // len j = 0, j++
 * [[1,3],[2,5]]
 *   ^
 * [[4,2],[2,3],[5,3]]
 *         ^
 * num i = 1, len i = 1
 * num j = 2, len j = 3
 * new element = (num i * num j, min(len i, len j))
 * if new element's val == list.last's val ? merge : add
 * len i -= min(len i, len j), if len i == 0 ? i++ // len i = 0, i++
 * len j -= min(len i, len j), if len j == 0 ? j++ // len j = 2
 * TC: O(m + n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1868_ProductOfTwoRunLengthEncodedArrays {
    public static List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        int p1 = 0;
        int p2 = 0;
        int num1 = 0;
        int len1 = 0;
        int num2 = 0;
        int len2 = 0;
        List<List<Integer>> list = new ArrayList<>();
        while (p1 < encoded1.length) {
            if (len1 == 0) {
                num1 = encoded1[p1][0];
                len1 = encoded1[p1][1];
            }
            if (len2 == 0) {
                num2 = encoded2[p2][0];
                len2 = encoded2[p2][1];
            }
            int product = num1 * num2;
            int minLen = Math.min(len1, len2);
            if (list.size() != 0 && list.get(list.size() -1).get(0).equals(product)) {
                List<Integer> prev = list.get(list.size() - 1);
                prev.set(1, prev.get(1) + minLen);
            } else {
                list.add(Arrays.asList(product, minLen));
            }
            len1 -= minLen;
            len2 -= minLen;
            if (len1 == 0) {
                p1++;
            }
            if (len2 == 0) {
                p2++;
            }
        }
        return list;
    }
}
