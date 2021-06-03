package leetcode.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * Q138. Copy List with Random Pointer.
 * [Map]
 * Map<Original Node, Copied Node>
 * Iterate through the original nodes and copy nodes.
 * In the second iteration, connect next pointers.
 * In the third iteration, connect random pointers.
 * TC: O(n), where n is the length of input nodes.
 * SC: O(n), where n is the length of input nodes.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q138_CopyListWithRandomPointer {

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();

        Node curr = head;
        while (curr != null) {
            Node copied = new Node(curr.val);
            map.put(curr, copied);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node copied = map.get(curr);
            copied.next = map.get(curr.next);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node copied = map.get(curr);
            copied.random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(val).append(", ");

            if (next == null) {
                sb.append("null, ");
            } else {
                sb.append(next.val).append(", ");
            }

            if (random == null) {
                sb.append("null");
            } else {
                sb.append(random.val);
            }

            sb.append("] ");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.random = head;
        Node copied = copyRandomList(head);
        while (copied != null) {
            System.out.print(copied);
            copied = copied.next;
        }
        // output: [1, 2, null] [2, 3, 1] [3, null, null]
    }

}
