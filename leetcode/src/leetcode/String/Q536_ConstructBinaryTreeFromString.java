package leetcode.String;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 536. Construct Binary Tree from String.
 * [String & Stack]
 * 4(2(3)(1))(6(5))
 * ^ current node
 * 4(2(3)(1))(6(5))
 *   <-----> left
 *     ^ left's left
 *        ^ left's right
 * 4(2(3)(1))(6(5))
 *            <--> right
 *              ^ right's left
 *
 * 4 ( 2 ( 3 ) ( 1 ) ) ( 6 ( 5 ) ) // String s
 * 0 1   2   1 2   1 0 1   2   1 0 // balance
 *
 * case 1. is '-', isNegative = true
 * case 2. is digit, num = num * 10 + curr number
 * case 3. is '(',
 *     balance++
 *     if stack size < balance,
 *         build node
 *         if stack is not empty, connect peek node & curr node
 *         add curr node to the stack
 *     else, continue
 * case 4. is ')',
 *     if stack size > balance,
 *         pop
 *     else,
 *         build node
 *         if stack is not empty, connect peek node & curr node
 *     balance--
 *
 * TC: O(n)
 * SC: O(h)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q536_ConstructBinaryTreeFromString {
    public static TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        boolean isNegative = false;
        int num = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') {
                isNegative = true;
            } else if (Character.isDigit(c)) {
                num = num * 10 + Character.getNumericValue(c);
            } else if (c == '(') {
                balance++;
                if (deque.size() >= balance) {
                    continue;
                }
                TreeNode curr = new TreeNode(isNegative ? -num : num);
                connectTwoNodes(deque, curr);
                deque.add(curr);
                isNegative = false;
                num = 0;
            } else {
                if (deque.size() > balance) {
                    deque.removeLast();
                } else {
                    TreeNode curr = new TreeNode(isNegative ? -num : num);
                    connectTwoNodes(deque, curr);
                    isNegative = false;
                    num = 0;
                }
                balance--;
            }
        }

        if (deque.isEmpty()) {
            return new TreeNode(isNegative ? -num : num);
        }

        return deque.remove();
    }

    private static void connectTwoNodes(Deque<TreeNode> deque, TreeNode curr) {
        if (deque.isEmpty()) {
            return;
        }

        if (deque.peekLast().left == null) {
            deque.peekLast().left = curr;
        } else {
            deque.peekLast().right = curr;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        private TreeNode(int newVal) {
            val = newVal;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            preorder(sb, this);
            sb.setLength(sb.length() - 2);
            sb.append("]");
            return sb.toString();
        }

        private static void preorder(StringBuilder sb, TreeNode curr) {
            if (curr == null) {
                return;
            }
            sb.append(curr.val).append(", ");
            preorder(sb, curr.left);
            preorder(sb, curr.right);
        }
    }

    public static void main(String[] args) {
        String s = "-1(-2)(-3)";
        System.out.println(str2tree(s));
        // output: [-1, -2, -3]
    }
}
