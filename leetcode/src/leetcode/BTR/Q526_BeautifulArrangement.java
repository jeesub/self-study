package leetcode.BTR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 526. Beautiful Arrangement.
 *
 * [BTR]
 * (1..n) make possible numbers lists.
 * DFS through the lists backwards.
 *
 * TC: O(k), where k is the number of valid permutations.
 * SC: O(n^2), where n is the input n.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q526_BeautifulArrangement {
    public static int countArrangement(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be a positive number");
        }        
        
        List<List<Integer>> possibleList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            possibleList.add(new ArrayList<>());
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    possibleList.get(i).add(j);
                }
            }
        }
        
        return dfs(possibleList, new HashSet<>(), n, n);
    }
    
    private static int dfs(List<List<Integer>> possibleList, Set<Integer> path, int n, int index) {
        if (index == 1) {
            int count = 0;
            for (Integer num : possibleList.get(index)) {
                if (path.contains(num)) {
                    continue;
                }
                count++;
            }
            return count;
        }
        
        int count = 0;
        for (Integer num : possibleList.get(index)) {
            if (path.contains(num)) {
                continue;
            }
            path.add(num);
            count += dfs(possibleList, path, n, index - 1);
            path.remove(num);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countArrangement(15));
        // output: 24679
    }

}
