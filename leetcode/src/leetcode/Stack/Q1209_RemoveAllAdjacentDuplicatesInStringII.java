package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q1209. Remove All Adjacent Duplicates in String II.
 * [Stack]
 * Stores a Pair<char, continuous freq> in a deque.
 *
 * Iterate through the input string.
 * If deque is empty,
 *   Add curr and continue.
 * If the last char == curr char, merge it.
 * Else, add new one.
 * And then if we can remove the last one,
 *   Remove it.
 *
 * Rebuild the result and return it.
 *
 * TC: O(n), where n is the length of input string.
 * SC: O(n), where n is the length of input string.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1209_RemoveAllAdjacentDuplicatesInStringII {

    public static String removeDuplicates(String s, int k) {
        Deque<Pair> deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (deque.isEmpty()) {
                deque.add(new Pair(curr));
                continue;
            }

            if (deque.peekLast().letter == curr) {
                deque.peekLast().freq++;
            } else {
                deque.add(new Pair(curr));
            }

            if (deque.peekLast().freq == k) {
                deque.removeLast();
            }
        }

        return buildResult(deque);
    }

    private static String buildResult(Deque<Pair> deque) {
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            Pair curr = deque.removeFirst();
            for (int i = 0; i < curr.freq; i++) {
                sb.append(curr.letter);
            }
        }
        return sb.toString();
    }

    private static class Pair {
        private char letter;
        private int freq;

        private Pair(char newLetter) {
            letter = newLetter;
            freq = 1;
        }
    }

    public static void main(String[] args) {
        String s = "pbbcggttciiippooaais";
        int k = 2;
        System.out.println(removeDuplicates(s, k));
        // output: ps
    }

}
