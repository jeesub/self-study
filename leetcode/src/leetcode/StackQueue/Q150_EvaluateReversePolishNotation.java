package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

/**
 * 150. Evaluate Reverse Polish Notation.
 * [Stack]
 * if curr is digit, put it into the stack
 * if curr is an operator, calculate two recent numbers and put it back to the stack
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q150_EvaluateReversePolishNotation {
    private static final Set<String> operators = Set.of("+", "-", "*", "/");

    public static int evalRPN(String[] tokens) {
        Deque<Integer> deque = new ArrayDeque<>();

        for (String token : tokens) {
            if (!operators.contains(token)) {
                int num = Integer.parseInt(token);
                deque.add(num);
                continue;
            }
            int num2 = deque.removeLast();
            int num1 = deque.removeLast();
            if (token.equals("+")) {
                deque.add(num1 + num2);
            } else if (token.equals("-")) {
                deque.add(num1 - num2);
            } else if (token.equals("*")) {
                deque.add(num1 * num2);
            } else {
                deque.add(num1 / num2);
            }
        }

        return deque.remove();
    }
}
