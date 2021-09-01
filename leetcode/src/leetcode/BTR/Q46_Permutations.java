package leetcode.BTR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 46. Permutations.
 * [BTR]
 * base case: has all elements -> add to a result
 * Go to the next round with each element.
 * TC: O(n!), where n is the number of elements
 * SC: O(n), where n is the number of elements
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q46_Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        btr(result, path, visited, nums);
        return result;
    }

    private static void btr(List<List<Integer>> result, List<Integer> path, Set<Integer> visited, int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int num : nums) {
            if (visited.contains(num)) {
                continue;
            }
            path.add(num);
            visited.add(num);
            btr(result, path, visited, nums);
            path.remove(path.size() - 1);
            visited.remove(num);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
        // output: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    }

}
