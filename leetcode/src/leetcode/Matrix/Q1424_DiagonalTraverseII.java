package leetcode.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 1424. Diagonal Traverse II.
 * [Matrix & MinHeap]
 * Iterate through the nums backward
 * and build a List<List<Integer>> list where list[i + j][value]
 * TC: O(n), where n is the number of elements
 * SC: O(n), where n is the number of elements
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1424_DiagonalTraverseII {
    public static int[] findDiagonalOrder(List<List<Integer>> nums) {
        int maxMPlusN = nums.size();
        int numOfElements = 0;
        for (int i = 0; i < nums.size(); i++) {
            numOfElements += nums.get(i).size();
            maxMPlusN = Math.max(maxMPlusN, i + nums.get(i).size() + 1);
        }

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < maxMPlusN - 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = nums.size() - 1; i >= 0; i--) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                list.get(i + j).add(nums.get(i).get(j));
            }
        }

        int[] result = new int[numOfElements];
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                result[index++] = list.get(i).get(j);
            }
        }
        return result;
    }
}
