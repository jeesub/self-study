package leetcode.BTR;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning.
 * [DP & BTR & DFS]
 * aabcba
 * build a start and end of palindromes list
 * 0: [0, 1]   // s(0..0) and s(0..1) are palindrome
 * 1: [1]      // s(1..1) is a palindrome
 * 2: [2, 4]   // s(2..2) and s(2..4) are palindromes
 * 3: [3]
 * 4: [4]
 * 5: [5]
 *
 * DFS & build a list
 * TC: O(n*2^n)
 * SC: O(n^2)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q131_PalindromePartitioning {
    public static List<List<String>> partition(String s) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            list.add(new ArrayList<>());
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    list.get(i).add(j);
                }
            }
        }

        List<String> path = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        dfs(s, path, list, result, 0);
        return result;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        if (i == j) {
            return true;
        }
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(String s, List<String> path, List<List<Integer>> list, List<List<String>> result, int idx) {
        if (idx >= s.length()) {
            result.add(new ArrayList(path));
            return;
        }

        for (int num : list.get(idx)) {
            path.add(s.substring(idx, num + 1));
            dfs(s, path, list, result, num + 1);
            path.remove(path.size() - 1);
        }
    }
}
