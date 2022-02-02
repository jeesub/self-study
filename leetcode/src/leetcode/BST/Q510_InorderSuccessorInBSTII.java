package leetcode.Tree;
/**
 * Q510. Inorder Successor in BST II.
 * Case 1. Right child's most left child.
 * Case 2. Most left parent's right parent.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q510_InorderSuccessorInBSTII {

    public static Node inorderSuccessor(Node node) {
        Node successor = null;

        if (node.right != null) {
            successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }

        Node curr = node;
        while (curr.parent != null && curr.parent.val < curr.val) {
            curr = curr.parent;
        }
        if (curr.parent != null && curr.val < curr.parent.val) {
            successor = curr.parent;
        }
        return successor;
    }

    private static class Node {
        private int val;
        private Node left;
        private Node right;
        private Node parent;

        private Node(int newVal) {
            val = newVal;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(2);
        Node left = new Node(1);
        Node right = new Node(3);
        root.left = left;
        root.right = right;
        left.parent = root;
        right.parent = root;
        System.out.println(inorderSuccessor(left).val);
        // output: 2
    }

}
