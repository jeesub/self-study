package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q30. Substring with Concatenation of All Words.
 * 
 * DFS (0.. the last possible start index)
 *     while (!wordsMap.isEmpty() && wordsMap contains substring)
 *         a pointer jump, remove substring from wordsMap
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q30_SubstringWithConcatenationOfAllWords {

    public static List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordsMap = initWordsMap(words);
        int len = words[0].length();
        int lastIndex = s.length() - words[0].length() * words.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= lastIndex; i++) {
            if (wordsMap.containsKey(s.substring(i, i + len))) {
                Map<String, Integer> tmpMap = new HashMap<>(wordsMap);
                if (isSubstring(s, tmpMap, len, i)) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    private static Map<String, Integer> initWordsMap(String[] words) {
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            int cnt = wordsMap.getOrDefault(word, 0) + 1;
            wordsMap.put(word, cnt);
        }
        return wordsMap;
    }

    private static boolean isSubstring(String s, Map<String, Integer> wordsMap, int len, int pointer) {
        while (!wordsMap.isEmpty()) {
            String word = s.substring(pointer, pointer + len);
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) - 1);
                wordsMap.remove(word, 0);
                pointer += len;
            } else {
                break;
            }
        }
        return wordsMap.isEmpty();
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.print(findSubstring(s, words));
        // output: [0, 9]
    }

}
