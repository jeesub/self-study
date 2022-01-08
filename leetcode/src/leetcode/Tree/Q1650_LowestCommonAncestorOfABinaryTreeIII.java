package leetcode.Tree;

import java.util.HashSet;
import java.util.Set;

/**
 * [DFS]
 * Start from the first node, dfs to the root and add all the path to a set.
 * While dfs the second node, if current node is in the set, it's the LCA.
 * TC: O(h), where h is the deepest depth of input nodes
 * SC: O(h), where h is the deepest depth of input nodes
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1650_LowestCommonAncestorOfABinaryTreeIII {
    public static Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> path = new HashSet<>();
        Node curr = p;
        while (curr != null) {
            path.add(curr);
            curr = curr.parent;
        }

        curr = q;
        while (curr != null && !path.contains(curr)) {
            curr = curr.parent;
        }

        return curr;
    }

    private static class Node {
        private int val;
        private Node left;
        private Node right;
        private Node parent;

        private Node(int newVal) {
            val = newVal;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            Node other = (Node) o;
            return this.val == other.val;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(val);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.left.parent = root;
        root.right = new Node(2);
        root.right.parent = root;
        System.out.println(lowestCommonAncestor(root.left, root.right).val);
        // output: 0
    }
}
