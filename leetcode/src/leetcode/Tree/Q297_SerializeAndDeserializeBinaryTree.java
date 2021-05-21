package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q297. Serialize and Deserialize Binary Tree.
 * Use preorder traversal.
 * [1, 2, null, null, 3, 4, n, n, 5, n, n].
 *
 * [Serialize]
 * Use a StringBuilder and recursion.
 * It's a preorder traversal.
 * Append current node first.
 * And call recursive method on left node and right node.
 * TC: O(n), where n is the number of nodes.
 * SC: O(n), where n is the number of nodes.
 *
 * [Deserialize]
 * Use a recursion.
 * Build left child and right child when we are dealing with a parent node.
 * The reason is, we need to know the parent to connect the parent and children.
 * TC: O(n), where n is the number of nodes.
 * SC: O(n), where n is the number of nodes.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q297_SerializeAndDeserializeBinaryTree {

    private static final String NULL = "n";
    private static final String DELIMITER = ",";

    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorderSerialize(sb, root);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private static void preorderSerialize(StringBuilder sb, TreeNode curr) {
        if (curr == null) {
            sb.append(NULL).append(DELIMITER);
            return;
        }
        sb.append(curr.val).append(DELIMITER);
        preorderSerialize(sb, curr.left);
        preorderSerialize(sb, curr.right);
    }

    public  static TreeNode deserialize(String data) {
        Deque<String> deque = buildDeque(data);
        if (deque.isEmpty()) {
            return null;
        }
        TreeNode root = buildNode(deque.remove());
        preorderDeserialize(deque, root);

        return root;
    }

    private static Deque<String> buildDeque(String data) {
        String[] array = data.split(DELIMITER);
        Deque<String> deque = new ArrayDeque<>();
        for (String each : array) {
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String serialized = serialize(root);
        System.out.println(serialized);
        // output: 1,2,n,n,3,4,n,n,5,n,n
        TreeNode deserialized = deserialize(serialized);
        System.out.println(deserialized);
        // output: preorder: [1, 2, 3, 4, 5], inorder: [2, 1, 4, 3, 5]
    }

}
