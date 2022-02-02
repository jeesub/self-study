package leetcode.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List.
 * [Binary Search Tree]
 * Inorder Traverse while keeping prevNode and currNode.
 * prevNode.right = currNode. currNode.left = prevNode.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q426_ConvertBinarySearchTreeToSortedDoublyLinkedList {
    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = new LinkedList<>();
        pushLeft(root, deque);
        Node head = deque.peekLast();

        Node prev = head;
        Node curr = null;
        while (!deque.isEmpty()) {
            curr = deque.removeLast();
            pushLeft(curr.right, deque);

            curr.left = prev;
            prev.right = curr;
            prev = curr;
        }

        head.left = curr;
        curr.right = head;

        return head;
    }

    private static void pushLeft(Node curr, Deque<Node> deque) {
        while (curr != null) {
            deque.add(curr);
            curr = curr.left;
        }
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
