package leetcode.Tree;

/**
 * 116. Populating Next Right Pointers in Each Node.
 * [Tree & Recursion]
 * if curr is a leaf, return
 * curr.left.next = curr.right
 * curr.right.next = curr.next.left (if curr.next != null)
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q116_PopulatingNextRightPointersInEachNode {
    public static Node connect(Node root) {
        if (root == null || root.left == null) {
            return root;
        }
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
