package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q224. Basic Calculator.
 * Iterate through the String.
 * Keep the prev number, operator, and current number.
 * If we encounter a new operator or parenthesis,
 *  calculate prev num, operator, and cur num. And update prev num, operator, and curr num.
 * If current char is open parenthesis, store prev num and operator into a stack.
 *  And reset prev num, operator, and curr num.
 * If current char is close parenthesis, get prev num and operator. And calculate.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q224_BasicCalculator {
    private static final Set<Character> OPERATORS = new HashSet<>(List.of('+', '-'));
    private static final char OPEN_PARENTHESIS = '(';
    private static final char CLOSE_PARENTHESIS = ')';

    public static int calculate(String s) {
        Deque<Object> deque = new ArrayDeque<>();
        char[] c = s.toCharArray();
        int prevNum = 0;
        int currNum = 0;
        char operator = '+';
        for (int i = 0; i < c.length; i++) {
            char currC = c[i];
            if (currC == ' ') {
                continue;
            }
            if (OPERATORS.contains(currC)) {
                prevNum = calculate(prevNum, currNum, operator);
                currNum = 0;
                operator = currC;
                continue;
            }
            if (currC == OPEN_PARENTHESIS) {
                deque.add(prevNum);
                deque.add(operator);
                prevNum = 0;
                currNum = 0;
                operator = '+';
                continue;
            }
            if (currC == CLOSE_PARENTHESIS) {
                currNum = calculate(prevNum, currNum, operator);
                operator = (char) deque.removeLast();
                prevNum = (int) deque.removeLast();
                prevNum = calculate(prevNum, currNum, operator);
                currNum = 0;
                operator = '+';
                continue;
            }
            int currInt = currC - '0';
            currNum = currNum * 10 + currInt;
        }
        return calculate(prevNum, currNum, operator);
    }

    private static int calculate(int prevNum, int currNum, char operator) {
        if (operator == '+') {
            return prevNum + currNum;
        }
        if (operator == '-') {
            return prevNum - currNum;
        }
        throw new IllegalArgumentException("Wrong operator");
    }

    public static void main(String[] args) {
        String s = "64 + 36 - (1 + 3 - 2 + 8) + 11";
        System.out.println(calculate(s));
        // output: 101
    }

}
