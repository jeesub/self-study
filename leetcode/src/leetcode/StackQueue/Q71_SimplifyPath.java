package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 71. Simplify Path.
 *  * [Stack]
 *  * Track the path using a deque.
 *  * Split the path using "/" and iterate it.
 *  * case 1. empty string, continue.
 *  * case 2. ".", continue.
 *  * case 3. "..", removeFirst from the deque.
 *  * case 4. a word, addLast to the deque.
 *  * TC: O(n), where n is the length of the input string.
 *  * SC: O(n), where n is the length of the input string.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q71_SimplifyPath {
    public static String simplifyPath(String path) {
        String[] arr = path.split("/");
        Deque<String> deque = new ArrayDeque<>();
        for (String each : arr) {
            if (each.equals("") || each.equals(".")) {
                continue;
            }
            if (each.equals("..")) {
                if (!deque.isEmpty()) {
                    deque.removeLast();
                }
                continue;
            }
            deque.addLast(each);
        }

        if (deque.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/");
            sb.append(deque.removeFirst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String path = "/a/./b/../../c/";
        System.out.println(simplifyPath(path));
        // output: "/c"
    }
}
