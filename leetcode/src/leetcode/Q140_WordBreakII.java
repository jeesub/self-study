package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Q140. Word Break II.
 * [DFS]
 * If a substring word s(i..j + 1) is in the dictionary, start a new DFS in s(0..i).
 * If start pointer = 0 and the word is in the dictionary, build a sentence.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q140_WordBreakII {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        LinkedList<String> path = new LinkedList<>();
        List<LinkedList<String>> result = new ArrayList<>();
        dfs(s, dict, path, result, s.length() - 1);
        return buildResult(result);
    }

    private static void dfs(String s, Set<String> dict, LinkedList<String> path, List<LinkedList<String>> result,
            int pointer) {
        if (pointer < 0) {
            result.add(new LinkedList<>(path));
            return;
        }
        for (int i = pointer; i >= 0; i--) {
            if (dict.contains(s.substring(i, pointer + 1))) {
                path.addFirst(s.substring(i, pointer + 1));
                dfs(s, dict, path, result, i - 1);
                path.removeFirst();
            }
        }
        return;
    }

    private static List<String> buildResult(List<LinkedList<String>> lists) {
        List<String> result = new ArrayList<>();
        for (LinkedList<String> list : lists) {
            StringBuilder sb = new StringBuilder();
            for (String word : list) {
                sb.append(word);
                sb.append(" ");
            }
            sb.setLength(sb.length() - 1);
            result.add(sb.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "penpineappleapplepen";
        List<String> wordDict = List.of("pen","pineapple","apple","pine","leap");
        System.out.println(wordBreak(s, wordDict));
        // output: [pen pine apple apple pen, pen pineapple apple pen]
    }

}
