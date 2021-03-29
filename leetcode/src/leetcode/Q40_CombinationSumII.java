package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q40. Combination Sum II
 * [BTR]
 * Sort the array and BTR.
 * We should avoid duplicates.
 * [1, 1, 1, 2]
 *  o  o  o  is valid.
 *  o  o  x  is valid.
 *  o  x  x  is valid.
 *  x  x  x  is valid.
 *  o  x  o  is invalid.
 *  x  o  o  is invalid.
 *  In case we go without the current number, we should skip the next same numbers.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q40_CombinationSumII {

    // Solution 1
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        btr(candidates, path, result, target, 0);
        
        return result;
    }

    // for loop BTR solution
    private static void btr(int[] candidates, List<Integer> path, List<List<Integer>> result, int target, int index) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = index; i < candidates.length; i++) {
            // pruning
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

    // Solution 2
    public static List<List<Integer>> combinationSum2Recursion(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        btr2(candidates, path, result, target, 0);
        
        return result;
    }

    // recursion BTR solution
    private static void btr2(int[] candidates, List<Integer> path, List<List<Integer>> result, int target, int index) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (target < 0 || index >= candidates.length) {
            return;
        }

        // pruning
        if (candidates[index] > target) {
            return;
        }
        path.add(candidates[index]);
        btr(candidates, path, result, target - candidates[index], index + 1);
        path.remove(path.size() -1);
        
        int nextIndex = index + 1;
        while (nextIndex < candidates.length && candidates[index] == candidates[nextIndex]) {
            nextIndex++;
        }
        btr(candidates, path, result, target, nextIndex);
    } 

    public static void main(String[] args) {
        int[] candidates = {2, 1, 2, 5, 2};
        int target = 5;
        System.out.println(combinationSum2(candidates, target));
        System.out.println(combinationSum2Recursion(candidates, target));
        // output: [[1, 2, 2], [5]]
    }

}
