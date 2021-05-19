package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q1047. Remove All Adjacent Duplicates In String.
 * [Stack]
 * Iterate through the input string.
 * If curr char == peek char, remove.
 * Else, put curr char.
 * TC: O(n), where n is the length of input string.
 * SC: O(n), where n is the length of input string.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1047_RemoveAllAdjacentDuplicatesInString {

    public static String removeDuplicates(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (deque.isEmpty() || deque.peekLast() != curr) {
                deque.add(curr);
            } else {
                deque.removeLast();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.removeFirst());
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "pabbaa";
        System.out.println(removeDuplicates(s));
        // output: pa
    }

}
