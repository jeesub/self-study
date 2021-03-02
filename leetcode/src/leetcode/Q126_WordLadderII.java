package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Q126. Word Ladder II
 * [BFS]
 * Searches every possible path and returns the shortest paths.
 * Starts with building words set and words map.
 * Words map<String, List<String>> will contain possible variations.
 * For example, key: *og, value: [dog, log, cog]
 * Queue will contain paths.
 * When we find a complete path, we can return all possible paths after current layer search.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q126_WordLadderII {

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordsSet = buildWordsSet(wordList);
        if (!wordsSet.contains(endWord)) {
            return new ArrayList<>();
        }
        Map<String, List<String>> wordsMap = buildWordsMap(wordList);

        List<List<String>> result = new ArrayList<>();
        Deque<List<String>> deque = new ArrayDeque<>();
        deque.add(new ArrayList<>(List.of(beginWord)));
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int len = beginWord.length();
        while (!deque.isEmpty()) {
            if (result.size() != 0) {
                return result;
            }
            int size = deque.size();
            Set<String> localVisited = new HashSet<>();
            while (size > 0) {
                List<String> path = deque.removeFirst();
                String currWord = path.get(path.size() - 1);
                char[] charArray = currWord.toCharArray();
                for (int i = 0; i < len; i++) {
                    char tmp = charArray[i];
                    charArray[i] = '*';
                    String keyWord = new String(charArray);
                    for (String word : wordsMap.getOrDefault(keyWord, new ArrayList<>())) {
                        if (!visited.contains(word)) {
                            localVisited.add(word);
                            List<String> newPath = new ArrayList<>(path);
                            newPath.add(word);
                            deque.addLast(newPath);
                            if (word.equals(endWord)) {
                                result.add(newPath);
                            }
                        }
                    }
                    charArray[i] = tmp;
                }
                size--;
            }
            visited.addAll(localVisited);
        }
        return new ArrayList<>();
    }

    private static Set<String> buildWordsSet(List<String> wordList) {
        Set<String> wordsSet = new HashSet<>();
        for (String word : wordList) {
            wordsSet.add(word);
        }
        return wordsSet;
    }

    private static Map<String, List<String>> buildWordsMap(List<String> wordList) {
        Map<String, List<String>> wordsMap = new HashMap<>();
        int len = wordList.get(0).length();
        for (String word : wordList) {
            char[] charArray = word.toCharArray();
            for (int i = 0; i < len; i++) {
                char tmp = charArray[i];
                charArray[i] = '*';
                String keyWord = new String(charArray);
                List<String> list = wordsMap.getOrDefault(keyWord, new ArrayList<>());
                list.add(word);
                wordsMap.put(keyWord, list);
                charArray[i] = tmp;
            }
        }
        return wordsMap;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(List.of("hot","dot","dog","lot","log","cog"));
        System.out.println(findLadders(beginWord, endWord, wordList));
        // output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
    }

}
