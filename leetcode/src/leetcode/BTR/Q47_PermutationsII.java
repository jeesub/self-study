package leetcode.BTR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q47. Permutations II.
 * [BTR]
 * Sort the input array.
 * While traversing, if curr num == prev num && prev num isn't on the list, skip it.
 * TC: O(n!), where n is the number of elements
 * SC: O(n), where n is the number of elements
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q47_PermutationsII {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        btr(path, result, visited, nums);

        return result;
    }

    private static void btr(List<Integer> path, List<List<Integer>> result, boolean[] visited, int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            visited[i] = true;
            btr(path, result, visited, nums);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(permuteUnique(nums));
        // [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
    }

}
