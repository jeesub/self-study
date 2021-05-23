package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * TreeNode.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class TreeNode {
    private static final String NULL = "null";
    private static final String DELIMITER = ",";

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {}

    public TreeNode(int newVal) {
        val = newVal;
    }

    public TreeNode(String data) {
        deserialize(data);
    }

    private void deserialize(String data) {
        Deque<String> deque = buildDeque(data);
        if (deque.isEmpty()) {
            return;
        }
        if (deque.peekFirst().equals(NULL)) {
            return;
        }
        val = Integer.valueOf(deque.remove());
        preorderDeserialize(deque, this);
    }

    private static Deque<String> buildDeque(String data) {
        String[] array = data.split(DELIMITER);
        Deque<String> deque = new ArrayDeque<>();
        for (String each : array) {
            each = each.trim();
            deque.add(each);
        }
        return deque;
    }

    private static void preorderDeserialize(Deque<String> deque, TreeNode curr) {
        if (curr == null) {
            return;
        }

        curr.left = buildNode(deque.remove());
        preorderDeserialize(deque, curr.left);
        curr.right = buildNode(deque.remove());
        preorderDeserialize(deque, curr.right);
    }

    private static TreeNode buildNode(String value) {
        if (value.equals(NULL)) {
            return null;
        }
        return new TreeNode(Integer.valueOf(value));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("preorder: [");
        preorderTraverse(this, sb);
        sb.setLength(sb.length() - 2);
        sb.append("], inorder: [");
        inorderTraverse(this, sb);
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    private void preorderTraverse(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        sb.append(node.val);
        sb.append(", ");
        preorderTraverse(node.left, sb);
        preorderTraverse(node.right, sb);
    }

    private void inorderTraverse(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        inorderTraverse(node.left, sb);
        sb.append(node.val);
        sb.append(", ");
        inorderTraverse(node.right, sb);
    }
}
