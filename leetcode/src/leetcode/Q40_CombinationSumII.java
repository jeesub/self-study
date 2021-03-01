package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q40. Combination Sum II
 * [BTR]
 * Sort the candidates.
 * In iteration, we should see each number only once.
 * Starts btr(idx = 0).
 * We need to avoid duplicate cases.
 * If we've never seen a number, we should include it.
 * ex) [1, 2, 2, 2, 5]
 *            ^
 *    In btr(idx = 2), it's the first time to see this number.
 *    -> We should include this number.
 *    In btr(idx = 1) and btr(idx = 0), it's not the first time to see this number.
 *    -> We should not include this number because, it'll make a duplicate.
 * 
 *               ^
 *    In btr(idx = 2), we've already seen this number in btr(idx = 3).
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q40_CombinationSumII {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        btr(candidates, path, result, target, 0);
        
        return result;
    }

    private static void btr(int[] candidates, List<Integer> path, List<List<Integer>> result, int target, int index) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (i > index && candidates[i - 1] == candidates[i]) {
                continue;
            }
            path.add(candidates[i]);
            btr(candidates, path, result, target - candidates[i], i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 1, 2, 5, 2};
        int target = 5;
        System.out.println(combinationSum2(candidates, target));
        // output: [[1, 2, 2], [5]]
    }

}
