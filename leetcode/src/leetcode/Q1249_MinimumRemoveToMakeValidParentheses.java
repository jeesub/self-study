package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Q1249. Minimum Remove to Make Valid Parentheses.
 * Iterate throught the String.
 * In a stack, keeps index of open parentheses.
 * If curr char is close parentheses, we need to pop.
 *   If the stack is empty, it is invalid close parentheses.
 * After iteration, if we have open parentheses in a stack, thery are invalid.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1249_MinimumRemoveToMakeValidParentheses {

    public static String minRemoveToMakeValid(String s) {
        LinkedList<Integer> haveToRemove = new LinkedList<>();
        Deque<Integer> openers = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openers.addLast(i);
            }
            if (c == ')') {
                if (openers.isEmpty()) {
                    haveToRemove.addLast(i);
                } else {
                    openers.removeLast();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!openers.isEmpty() && openers.peekFirst() == i) {
                openers.removeFirst();
                continue;
            }
            if (haveToRemove.size() != 0 && haveToRemove.peekFirst() == i) {
                haveToRemove.removeFirst();
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "a(b(c)d)e)f)g(h)i)j(k)";
        System.out.println(minRemoveToMakeValid(s));
        // a(b(c)d)efg(h)ij(k)
    }

}
