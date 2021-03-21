package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Q1192. Critical Connections in a Network.
 * Use Tarjan's algorithm.
 * Make a rank array.
 * [DFS]
 * If next vertex is not seen, DFS with rank + 1
 * If next vertex is seen and has rank larger than curr, it's a critical connection.
 * If next vertex is seen but has rank smaller than curr, it's not a critical connection.
 *   update curr's rank as next's rank.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1192_CriticalConnectionsInANetwork {

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, Set<Integer>> graphMap = init(connections);
        List<List<Integer>> result = new ArrayList<>();
        int[] ranks = new int[n];
        dfs(graphMap, ranks, result, -1, 0, 1);
        return result;
    }

    private static void dfs(Map<Integer, Set<Integer>> graphMap, int[] ranks, List<List<Integer>> result, int prev, int curr,
            int rank) {
        ranks[curr] = rank;
        for (int next : graphMap.getOrDefault(curr, new HashSet<>())) {
            if (next == prev) {
                continue;
            }
            if (ranks[next] == 0) {
                dfs(graphMap, ranks, result, curr, next, rank + 1);
            }
            if (ranks[next] > rank) {
                result.add(List.of(curr, next));
            } else {
                ranks[curr] = Math.min(ranks[curr], ranks[next]);
            }
        }
    }

    private static Map<Integer, Set<Integer>> init(List<List<Integer>> connections) {
        Map<Integer, Set<Integer>> graphMap = new HashMap<>();
        for (List<Integer> list : connections) {
            Set<Integer> firstSet = graphMap.getOrDefault(list.get(0), new HashSet<>());
            firstSet.add(list.get(1));
            graphMap.put(list.get(0), firstSet);
            Set<Integer> secondSet = graphMap.getOrDefault(list.get(1), new HashSet<>());
            secondSet.add(list.get(0));
            graphMap.put(list.get(1), secondSet);
        }
        return graphMap;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Integer>> connections = List.of(List.of(1, 0), List.of(2, 0), List.of(3, 2), List.of(4, 2), List.of(4, 3), List.of(3, 0), List.of(4, 0));
        System.out.println(criticalConnections(n, connections));
        // output: [0, 1]
    }

}
