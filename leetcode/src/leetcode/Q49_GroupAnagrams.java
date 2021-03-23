package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. Group Anagrams.
 * Use HashMap to store anagrams.
 * Sort characters in string and use it as a key.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q49_GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = buildKey(str);
            map.computeIfAbsent(key, (unused) -> new ArrayList<String>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private static String buildKey(String str) {
        int[] arr = new int[26];
        for (char c : str.toCharArray()) {
            arr[c - 'a']++;
        }

        StringBuilder key = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                continue;
            }
            int n = arr[i];
            while (n > 0) {
                key.append((char) ('a' + i));
                n--;
            }
        }
        return key.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
        // output: [[eat, tea, ate], [bat], [tan, nat]]
    }

}
