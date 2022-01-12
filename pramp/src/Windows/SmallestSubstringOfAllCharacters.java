package Windows;

import java.util.HashMap;
import java.util.Map;

/**
 * Smallest Substring of All Characters
 * xyyzyzyx  // for (j < str.length())
 *    ^      // j walks. if we don't have all the characters, we don't have to touch i.
 *  ^        // i++ -> while (i <= j) && we have all the characters
 *           // i stops at the moment we don't have all the characters
 *           // compare result & substring(i - 1, j + 1)
 *
 * xyyz -> zyx
 *
 * Map
 * x: 0
 * y: 2
 * z: 1
 *
 * TC: O(m + n), where m = arr.length and n = str.length()
 * SC: O(m)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class SmallestSubstringOfAllCharacters {
    static String getShortestUniqueSubstring(char[] arr, String str) {
        Map<Character, Integer> map = buildMap(arr);
        int count = 0;
        int i = 0;
        String result = "";

        for (int j = 0; j < str.length(); j++) {
            if (!map.containsKey(str.charAt(j))) {
                continue;
            }

            if (map.get(str.charAt(j)) == 0) {
                count++;
            }
            map.put(str.charAt(j), map.get(str.charAt(j)) + 1);
            if (count != map.size()) {
                continue;
            }
            
            while (i <= j && count == map.size()) {
                if (!map.containsKey(str.charAt(i))) {
                    i++;
                    continue;
                }
                map.put(str.charAt(i), map.get(str.charAt(i)) - 1);
                if (map.get(str.charAt(i)) == 0) {
                    count--;
                }
                i++;
            }
            if (result.equals("") || result.length() > j - i + 1) {
                result = str.substring(i - 1, j + 1);
            }
        }

        return result;
    }

    private static Map<Character, Integer> buildMap(char[] arr) {
        Map<Character, Integer> map = new HashMap<>();
        for (char each : arr) {
            map.put(each, 0);
        }
        return map;
    }

    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c', 'd'};
        String str = "abbccdbcabdbc";
        System.out.println(getShortestUniqueSubstring(arr, str));
        // output: "cabd"
    }
}
