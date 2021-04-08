package string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

/**
 * Convert Infix to Postfix.
 * ((A-(B/C)) * ((D/E) - F))
 * => ABC/-DE/F-*
 * [Stack]
 * Iterate through the String.
 * curr char == operator ? add to stack.
 * curr char == letter ? append to a StringBuilder.
 * curr char == close parenthesis ? pop and append it.
 * After all, pop and append it.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class ConvertInfixToPostfix {
    private static final Set<Character> OPERATORS = Set.of('+', '-', '*', '/');

    public static String convert(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                continue;
            }
            if (c == ')') {
                result.append(deque.removeLast());
                continue;
            }
            if (OPERATORS.contains(c)) {
                deque.addLast(c);
                continue;
            }
            if (Character.isAlphabetic(c)) {
                result.append(c);
            }
        }
        while (!deque.isEmpty()) {
            result.append(deque.removeLast());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "((A-(B/C)) * ((D/E) - F))";
        System.out.println(convert(s));
        // output: ABC/-DE/F-*
        String s2 = "((((A*B)-C)+D)*E)-(F+G)";
        System.out.println(convert(s2));
        // output: AB*C-D+E*FG+-
    }

}
