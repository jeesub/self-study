package leetcode.Windows;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring.
 * [Windows & HashMap]
 * s = "ADOBECODEBANC", t = "ABC"
 * tMap = [(A, 1), (B, 1), (C, 1)]
 *
 * A D O B E C O D E B A N C
 * ^ j // sMap = [(A, 1)]
 *   ^ j // sMap = [(A, 1), (D, 1)]
 *     ..    ^ j // sMap = [(A, 1), (B, 1), (C, 1), (D, 1), (E, 1), (O, 1)]
 *               // contains all elements in tMap -> increase i
 *   ^ i     ^ j // sMap = [(B, 1), (C, 1), (D, 1), (E, 1), (O, 1)]
 *               // doesn't contain all elements -> increase j
 * The way to check if we have all elements.
 * j++ & sMap[char[j]] <= tMap[char[j]] ? numOfElement++
 * sMap[char[i]] <= tMap[char[i]] & i++ ? numOfElement--
 * if numOfElement == t.length(), we have all.
 * TC: O(m + n)
 * SC: O(m + n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q76_MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        Map<Character, Integer> tMap = buildTMap(t);
        Map<Character, Integer> sMap = new HashMap<>();
        int start = 0;
        int end = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
            if (tMap.containsKey(c) && sMap.get(c) <= tMap.get(c)) {
                count++;
            }
            while (i <= j && count == t.length()) {
                if (j - i < end - start) {
                    start = i;
                    end = j;
                }
                c = s.charAt(i);
                if (tMap.containsKey(c) && sMap.get(c) <= tMap.get(c)) {
                    count--;
                }
                sMap.put(c, sMap.get(c) - 1);
                i++;
            }
        }
        if (end == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, end + 1);
    }

    private Map<Character, Integer> buildTMap(String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!tMap.containsKey(c)) {
                tMap.put(c, 0);
            }
            tMap.put(c, tMap.get(c) + 1);
        }
        return tMap;
    }
}
