package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Words Concatenation.
 * Iterate through the given String.
 * DFS; If words set has substring word, remove the word from the set and continue.
 *      If cannot find a word, return false.
 *      If words set.size == 0 ? return true.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class WordsConcatenation {

    public static List<Integer> findSubstring(String s, Set<String> words, int length) {
        int totalLength = length * words.size();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= s.length() - totalLength; i++) {
            if (!words.contains(s.substring(i, i + length))) {
                continue;
            }
            if (dfs(s, i, new HashSet<>(words), length)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean dfs(String s, int i, Set<String> words, int length) {
        while (!words.isEmpty()) {
            String candidate = s.substring(i, i + length);
            if (!words.contains(candidate)) {
                return false;
            }
            i += length;
            words.remove(candidate);
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "catdogcatdogdoghogdogcat";
        Set<String> words = new HashSet<>(List.of("dog", "cat"));
        System.out.println(findSubstring(s, words, 3));
        // output: [0, 3, 6, 18]
    }
}
