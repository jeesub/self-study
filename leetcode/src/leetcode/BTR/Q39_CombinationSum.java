package leetcode.BTR;

import java.util.ArrayList;
import java.util.List;

/**
 * Q39. Combination Sum.
 * [BTR]
 * At a point, we can choose a number larger than or equal to itself.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q39_CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, path, result, target, 0);
        return result;
    }
    
    private static void dfs(int[] candidates, List<Integer> path, List<List<Integer>> result, int target, int index) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, path, result, target - candidates[i], i);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 5, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
        // output: [[2, 2, 3], [2, 5], [7]]
    }

}
