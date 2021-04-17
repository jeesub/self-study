package btr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Find All Possible Paths Contains Unique Differences.
 * [DFS & BTR]
 * - base case: cannot find an element anymore. Build a list and return.
 * - recursion: find a new difference and move on.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class FindAllPossiblePathsContainUniqueDifferences {

    public static List<List<List<Integer>>> findAllPaths(List<Integer> nums) {
        List<List<List<Integer>>> result = new ArrayList<>();
        List<List<Integer>> path = new ArrayList<>();
        path.add(nums);
        boolean[] visited = new boolean[Math.max(nums.get(0), nums.get(1)) + 1];
        visited[nums.get(0)] = true;
        visited[nums.get(1)] = true;
        dfs(result, path, visited);
        return result;
    }

    private static void dfs(List<List<List<Integer>>> result, List<List<Integer>> path, boolean[] visited) {
        List<Integer> currList = path.get(path.size() - 1);
        Set<Integer> diffs = new HashSet<>();
        for (int i = 0; i < currList.size() - 1; i++) {
            for (int j = i + 1; j < currList.size(); j++) {
                int diff = Math.abs(currList.get(i) - currList.get(j));
                if (visited[diff]) {
                    continue;
                }
                diffs.add(diff);
            }
        }

        if (diffs.isEmpty()) {
            result.add(new ArrayList<>(path));
            return;
        }

        List<Integer> nextPath = new ArrayList<>(currList);
        for (int diff : diffs) {
            nextPath.add(diff);
            visited[diff] = true;
            path.add(new ArrayList<>(nextPath));
            dfs(result, path, visited);
            nextPath.remove(nextPath.size() - 1);
            visited[diff] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<Integer> nums = List.of(7, 2);
        System.out.println(findAllPaths(nums));
        // output: 
        // [[[7, 2], [7, 2, 5], [7, 2, 5, 3], [7, 2, 5, 3, 1], [7, 2, 5, 3, 1, 4], [7, 2, 5, 3, 1, 4, 6]],
        // [[7, 2], [7, 2, 5], [7, 2, 5, 3], [7, 2, 5, 3, 1], [7, 2, 5, 3, 1, 6], [7, 2, 5, 3, 1, 6, 4]],
        // [[7, 2], [7, 2, 5], [7, 2, 5, 3], [7, 2, 5, 3, 4], [7, 2, 5, 3, 4, 1], [7, 2, 5, 3, 4, 1, 6]]]
    }

}
