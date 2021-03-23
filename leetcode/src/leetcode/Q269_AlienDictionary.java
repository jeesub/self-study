package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 269. Alien Dictionary.
 * [Topological Sorting]
 * If input is invalid, return empty String.
 * Build a Map<Char, Set<Char>> stores nodes and edges (from a key to an element in a set).
 * Deque only stores characters that have zero in degree.
 * Iterate through the graph.
 * After iteration, if we have used all characters, return it.
 * Else, it means we have a cycle. Return empty string.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q269_AlienDictionary {

    public static String alienOrder(String[] words) {
        if (!isValid(words)) {
            return "";
        }

        Map<Character, Set<Character>> map = initMap(words);
        buildMap(words, map);

        int[] indegrees = buildIndegrees(map);

        Deque<Character> deque = initDeque(indegrees, map);
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            char curr = deque.removeFirst();
            sb.append(curr);
            for (char c : map.getOrDefault(curr, new HashSet<>())) {
                indegrees[c - 'a']--;
                if (indegrees[c - 'a'] == 0) {
                    deque.addLast(c);
                }
            }
        }

        return sb.length() == map.size() ? sb.toString() : "";
    }

    private static boolean isValid(String[] words) {
        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            if (word1.length() > word2.length() && word1.substring(0, word2.length()).equals(word2)) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Set<Character>> initMap(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!map.containsKey(c)) {
                    map.put(c, new HashSet<>());
                }
            }
        }
        return map;
    }

    private static void buildMap(String[] words, Map<Character, Set<Character>> map) {
        for (int i = 1; i < words.length; i++) {
            char[] word1 = words[i - 1].toCharArray();
            char[] word2 = words[i].toCharArray();
            for (int j = 0; j < Math.min(word1.length, word2.length); j++) {
                if (word1[j] == word2[j]) {
                    continue;
                }
                map.get(word1[j]).add(word2[j]);
                break;
            }
        }
        return;
    }

    private static int[] buildIndegrees(Map<Character, Set<Character>> map) {
        int[] indegrees = new int[26];
        for (Set<Character> set : map.values()) {
            for (char c : set) {
                indegrees[c - 'a']++;
            }
        }
        return indegrees;
    }

    private static Deque<Character> initDeque(int[] indegrees, Map<Character, Set<Character>> map) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0 && map.containsKey((char) ('a' + i))) {
                deque.addLast((char) ('a' + i));
            }
        }
        return deque;
    }

    public static void main(String[] args) {
        String[] words = {"wrt","wrf","er","ett","rftt"};
        System.out.println(alienOrder(words));
        // output: wertf
    }

}
