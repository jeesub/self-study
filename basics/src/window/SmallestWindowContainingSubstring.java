package window;

import java.util.HashMap;
import java.util.Map;

/**
 * Smallest Window containing Substring.
 * Use two pointers, slow pointer i and faster pointer j.
 * Fast pointer stops if string(i..j) has all characters of the pattern.
 * If j - i is the smallest so far, record it.
 * Slow pointer starts to walk and stops if string(i..j) doesn't have all chars.
 * If j - (i - 1) is the smallest so far, record it.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class SmallestWindowContainingSubstring {

    public static String findSmallestWindow(String s, String p) {
        Map<Character, Integer> patternMap = init(p);
        Map<Character, Integer> ongoingMap = new HashMap<>();
        char[] c = s.toCharArray();
        int[] result = {0, s.length() - 1};
        int i = 0;
        int matched = 0;
        for (int j = 0; j < s.length(); j++) {
            if (!patternMap.containsKey(c[j])) {
                continue;
            }
            int newCountJ = ongoingMap.getOrDefault(c[j], 0) + 1;
            ongoingMap.put(c[j], newCountJ);
            if (newCountJ <= patternMap.get(c[j])) {
                matched++;
            }
            if (matched == p.length() && result[1] - result[0] > j - i) {
                result[0] = i;
                result[1] = j;
            }

            while (matched == p.length()) {
                if (patternMap.containsKey(c[i])) {
                    int newCountI = ongoingMap.get(c[i]) - 1;
                    ongoingMap.put(c[i], newCountI);
                    if (newCountI < patternMap.get(c[i])) {
                        matched--;
                    }
                }
                i++;
                if (matched == p.length() && result[1] - result[0] > j - i) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        if (result[0] == 0 && result[1] == s.length() - 1) {
            return "";
        }
        return s.substring(result[0], result[1] + 1);
    }

    private static Map<Character, Integer> init(String p) {
        Map<Character, Integer> patternMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            patternMap.put(c, patternMap.getOrDefault(c, 0) + 1);
        }
        return patternMap;
    }

    public static void main(String[] args) {
        String s = "abdbcadbad";
        String p = "abc";
        System.out.println(findSmallestWindow(s, p));
        // output: bca
    }

}
