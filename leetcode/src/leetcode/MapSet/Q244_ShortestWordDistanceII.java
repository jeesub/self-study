package leetcode.MapSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q244. Shortest Word Distance II.
 * [HashMap & Two Pointers]
 * Map<word, a list of index>
 * shortest method; use two pointers to find the minimum distance
 * TC of constructor: O(n), where n is the length of input string array
 * TC of shortest method: O(k), where k is the longest length of list in the map
 * SC: O(n), where n is the length of input string array
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q244_ShortestWordDistanceII {

    static class WordDistance {
        private Map<String, List<Integer>> map;

        public WordDistance(String[] wordsDict) {
            map = new HashMap<>();
            buildMap(wordsDict);
        }

        public void buildMap(String[] wordsDict) {
            for (int i = 0; i < wordsDict.length; i++) {
                if (!map.containsKey(wordsDict[i])) {
                    map.put(wordsDict[i], new ArrayList<>());
                }
                map.get(wordsDict[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            int result = Integer.MAX_VALUE;
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            int ptr1 = 0;
            int ptr2 = 0;
            int val1;
            int val2;
            while (ptr1 < list1.size() && ptr2 < list2.size()) {
                val1 = list1.get(ptr1);
                val2 = list2.get(ptr2);
                result = Math.min(result, Math.abs(val1 - val2));
                if (val1 < val2) {
                    ptr1++;
                } else {
                    ptr2++;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String[] wordsDict = {"pen", "pineapple", "apple", "pen"};
        WordDistance wd = new WordDistance(wordsDict);
        System.out.println(wd.shortest("pen", "apple"));
        // output: 1
    }

}
