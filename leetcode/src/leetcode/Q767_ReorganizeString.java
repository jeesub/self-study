package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Q 767. Reorganize String.
 * Count every character.
 * Put nodes(char, count) into a maxHeap ordered by count.
 * Get two from the maxHeap and append.
 * Re-put into the maxHeap.
 * If maxHeap has only one node and count > 1, return empty string.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q767_ReorganizeString {
    public static String reorganizeString(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            int newCount = charMap.getOrDefault(curr, 0) + 1;
            charMap.put(curr, newCount);
        }

        PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> (b.count - a.count));
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            maxHeap.add(new Node(entry.getKey(), entry.getValue()));
        }

        StringBuilder sb = new StringBuilder();
        while (maxHeap.size() >= 2) {
            Node first = maxHeap.remove();
            Node second = maxHeap.remove();

            sb.append(first.getAlphabet());
            first.decreaseCount();
            sb.append(second.getAlphabet());
            second.decreaseCount();

            if (first.getCount() > 0) {
                maxHeap.add(first);
            }
            if (second.getCount() > 0) {
                maxHeap.add(second);
            }
        }
        if (!maxHeap.isEmpty()) {
            Node rest = maxHeap.remove();
            if (rest.getCount() >= 2) {
                return "";
            }
            sb.append(rest.getAlphabet());
        }
        return sb.toString();
    }

    private static class Node {
        private char alphabet;
        private int count;

        public Node(char newAlphabet, int newCount) {
            alphabet = newAlphabet;
            count = newCount;
        }

        public char getAlphabet() {
            return alphabet;
        }

        public int getCount() {
            return count;
        }

        public void decreaseCount() {
            count--;
        }
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaabcedefghijk";
        System.out.println(reorganizeString(s));
        // output: "aeaeakadabacagahajfai"
    }

}
