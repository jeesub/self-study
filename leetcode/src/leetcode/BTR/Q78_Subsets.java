package leetcode.BTR;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets.
 * [BTR]
 * TC: O(2^n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q78_Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        btr(nums, path, list, 0);
        return list;
    }

    private static void btr(int[] nums, List<Integer> path, List<List<Integer>> list, int i) {
        if (i >= nums.length) {
            list.add(new ArrayList<>(path));
            return;
        }
        btr(nums, path, list, i + 1);
        path.add(nums[i]);
        btr(nums, path, list, i + 1);
        path.remove(path.size() - 1);
    }
}
