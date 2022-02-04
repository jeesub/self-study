package leetcode.LinkedList;

/**
 * 708. Insert into a Sorted Circular Linked List.
 * [LinkedList]
 * 1. the value is in between somewhere
 *     if prev.val <= insert value && insert value <= curr.val,
 * 2. the value is bigger than every node && curr is the smallest node
 *     if curr.val < prev.val && prev.val < insert value,
 * 3. the value is smaller than every node && curr is the smallest node
 *     if curr.val < prev.val && insert value < curr.val,
 * => prev.next = new node, new node.next = curr
 *
 * edge cases
 * 1. head == null
 * 2. head.next = head
 *
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q708_InsertIntoASortedCircularLinkedList {
    public static Node insert(Node head, int insertVal) {
        Node toInsert = new Node(insertVal);

        if (head == null) {
            toInsert.next = toInsert;
            return toInsert;
        }

        if (head.next == head) {
            head.next = toInsert;
            toInsert.next = head;
            return head;
        }

        Node prev = head;
        Node curr = head.next;
        while (curr != head) {
            if (prev.val <= insertVal && insertVal <= curr.val) {
                break;
            }
            if (curr.val < prev.val && prev.val <= insertVal) {
                break;
            }
            if (curr.val < prev.val && insertVal <= curr.val) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        prev.next = toInsert;
        toInsert.next = curr;
        return head;
    }

    private static class Node {
        public int val;
        public Node next;

        public Node(int newVal) {
            val = newVal;
        }
    }
}
