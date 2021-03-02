package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q127. Word Ladder.
 * [BFS]
 * Change every letter and if we have one, add to a queue.
 * Check if the current character is same as target word.
 * If we found a target, return a current transformation.
 * If we could not find a target, return 0
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q127_WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordsSet = buildSet(wordList);
        if (!wordsSet.contains(endWord)) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        Deque<String> deque = new ArrayDeque<>();
        deque.add(beginWord);
        int len = beginWord.length();
        int transformation = 0;

        while (!deque.isEmpty()) {
            transformation++;
            int size = deque.size();
            while (size > 0) {
                String curr = deque.removeFirst();
                if (curr.equals(endWord)) {
                    return transformation;
                }
                for (int i = 0; i < len; i++) {
                    for (int j = 0; j < 26; j++) {
                        char c = (char) ('a' + j);
                        String candidate = curr.substring(0, i) + c + curr.substring(i + 1, len);
                        if (wordsSet.contains(candidate) && !visited.contains(candidate)) {
                            deque.addLast(candidate);
                            visited.add(candidate);
                        }
                    }
                }
                size--;
            }
        }
        return 0;
    }

    private static Set<String> buildSet(List<String> wordList) {
        Set<String> wordsSet = new HashSet<>();
        for (String word : wordList) {
            wordsSet.add(word);
        }
        return wordsSet;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(List.of("hot","dot","dog","lot","log","cog"));
        System.out.println(ladderLength(beginWord, endWord, wordList));
        // output: 5
    }

}
