package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Longest Valid Parentheses.
 * 
 * A stack will store the possible end point.
 * 
 * Use a stack to store the openers' indices and end points.
 * The purpose of storing end points is to deal with a case like ()().
 * We should know the valid end point instead of start point.
 * If curr char is opener, put it into the stack.
 * Else,
 *     pop it.
 *     If stack is empty, it's invalid and curr point is a new end point.
 *         Put curr point into the stack.
 *     The last element in the stack should indicate the end point.
 *     New valid length should be (curr point - peek last value).
 * 
 * To handle the start point, starts with putting -1 in the stack.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(-1);
        int maxLength = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.addLast(i);
            } else {
                stack.removeLast();
                if (stack.isEmpty()) {
                    stack.addLast(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peekLast());
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "()))((()()))()())";
        System.out.print(longestValidParentheses(s));
        // output: 12
    }

}
