package leetcode.DP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Q1048. Longest String Chain.
 * [DP; DFS with Memoization]
 * Make a set of words.
 * DFS
 *   If map has the word, we already checked. return the value.
 *   Check the shorter chain. ex) 'ab' -> check if there is 'a' or 'b'
 *     If there is not, return 1. The longest length is 1.
 *     Return the longest length + 1.
 * Memoization: Map<word, the longest chain length>
 *
 * TC: O(n * l^2), where n is the number of words and l is the length of the longest word.
 * SC: O(n), where n is the number of words
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1048_LongestStringChain {

    public static int longestStrChain(String[] words) {
        Set<String> set = new HashSet<>(List.of(words));
        Map<String, Integer> map = new HashMap<>();
        int maxLength = 0;

        for (String word : set) {
            maxLength = Math.max(maxLength, getLongestChain(word, set, map));
        }

        return maxLength;
    }

    private static int getLongestChain(String word, Set<String> set, Map<String, Integer> map) {
        if (map.containsKey(word)) {
            return map.get(word);
        }

        int currMax = 0;

        for (int i = 0; i < word.length(); i++) {
            String candidate = word.substring(0, i) + word.substring(i + 1, word.length());
            if (set.contains(candidate)) {
                currMax = Math.max(currMax, getLongestChain(candidate, set, map));
            }
        }

        map.put(word, ++currMax);
        return currMax;
    }

    public static void main(String[] args) {
        String[] words = {"o", "do", "dog", "dogs", "cat", "at", "sun", "sunny"};
        System.out.println(longestStrChain(words));
        // output: 4
    }

}
