package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
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
        Deque<String> path = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        dfs(s, dict, path, result, s.length() - 1);
        return result;
    }

    private static void dfs(String s, Set<String> dict, Deque<String> path, List<String> result, int pointer) {
        if (pointer < 0) {
            result.add(buildString(path));
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

    private static String buildString(Deque<String> path) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> itr = path.iterator();
        while (itr.hasNext()) {
            sb.append(itr.next());
            sb.append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "penpineappleapplepen";
        List<String> wordDict = List.of("pen", "pin", "pineapple","apple","pine","leap");
        System.out.println(wordBreak(s, wordDict));
        // output: [pen pine apple apple pen, pen pineapple apple pen]
    }

}
