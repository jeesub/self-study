package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q227. Basic Calculator II.
 * [Stack]
 * Iterate through the input string.
 * Keep the recent operator.
 * If curr is number
 *   accumulate the number ex) prev number * 10 + curr number
 * If curr is an operator
 *   Put the accumulated number into the stack.
 *   If curr is + or -
 *     put the next number into the stack.
 *   If curr is * or /
 *     calculate the prev number and the next number and put the result into the stack.
 * TC: O(n), where n is the length of the input string.
 * SC: O(n), where n is the length of the input string.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q227_BasicCalculatorII {

    private static final Set<Character> operators = new HashSet<>(List.of('+', '-', '*', '/'));

    public static int calculate(String s) {
        Deque<Integer> deque = new ArrayDeque<>();
        char operator = '+';
        int num = 0;
    
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
    
            if (Character.isDigit(curr)) {
                num = num * 10 + (curr - '0');
                continue;
            }
    
            if (operators.contains(curr)) {
                calculate(deque, num, operator);
                operator = curr;
                num = 0;
            }
        }
        calculate(deque, num, operator);

        int result = 0;
        while (!deque.isEmpty()) {
            result += deque.remove();
        }
        return result;
    }
    
    private static void calculate(Deque<Integer> deque, int num, char operator) {
        if (operator == '+') {
            deque.add(num);
            return;
        }
        if (operator == '-') {
            deque.add(-num);
            return;
        }
        if (operator == '*') {
            deque.add(deque.removeLast() * num);
            return;
        }
        if (operator == '/') {
            deque.add(deque.removeLast() / num);
            return;
        }
    }

    public static void main(String[] args) {
        String s = "3 + 2 * 2";
        System.out.println(calculate(s));
        // output: 7
    }

}
