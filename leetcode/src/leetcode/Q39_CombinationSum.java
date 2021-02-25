package leetcode;

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
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            List<Integer> path = new ArrayList<>();
            path.add(candidates[i]);
            dfs(candidates, target, result, path, i, candidates[i]);
        }
        return result;
    }
    
    private static void dfs(int[] candidates, int target, List<List<Integer>> result, List<Integer> path, int pointer, int sum) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = pointer; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, target, result, path, i, sum + candidates[i]);
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
