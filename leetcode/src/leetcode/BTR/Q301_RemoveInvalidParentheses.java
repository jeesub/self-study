package leetcode.BTR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 301. Remove Invalid Parentheses.
 * [BTR & BFS]
 * (a)(b))()(
 *       ^ // We need to remove one close parenthesis
 *          ^ We need to remove one open parenthesis
 * We need to remove two parentheses, but we don't know what to remove.
 * BTR BFS.
 * TC: O(2^n)
 * SC: O(2^n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q301_RemoveInvalidParentheses {
    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    public static List<String> removeInvalidParentheses(String s) {
        if (isValid(s)) {
            return List.of(s);
        }

        int numOfRemoval = getNumOfRemoval(s);
        Set<String> prevSet = new HashSet<>();
        prevSet.add(s);
        while (!prevSet.isEmpty()) {
            Set<String> nextSet = new HashSet<>();
            for (String curr : prevSet) {
                for (int i = 0; i < curr.length(); i++) {
                    if (curr.charAt(i) != OPEN && curr.charAt(i) != CLOSE) {
                        continue;
                    }
                    String newString = curr.substring(0, i) + curr.substring(i + 1, curr.length());
                    nextSet.add(newString);
                }
            }
            if (--numOfRemoval > 0) {
                prevSet = nextSet;
                continue;
            }

            List<String> list = new ArrayList<>();
            for (String each : nextSet) {
                if (isValid(each)) {
                    list.add(each);
                }
            }
            return list;
        }
        return new ArrayList<>();
    }

    private static boolean isValid(String s) {
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == OPEN) {
                balance++;
            } else if (s.charAt(i) == CLOSE) {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    private static int getNumOfRemoval(String s) {
        int close = 0;
        int open = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == OPEN) {
                open++;
            }
            if (s.charAt(i) == CLOSE) {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
        }
        return open + close;
    }

    public static void main(String[] args) {
        String s = "(a)(b))()(";
        System.out.println(removeInvalidParentheses(s));
        // output: [(a)(b)(), (a(b))()]
    }
}
