package leetcode.LinkedList;

import java.util.Iterator;

/**
 * 83. Remove Duplicates from Sorted List.
 * [LinkedList]
 * if prev == curr, prev.next = curr.next
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q83_RemoveDuplicatesFromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE, head);
        ListNode prev = dummyHead;
        ListNode curr = head;
        while (curr != null) {
            if (prev.val == curr.val) {
                prev.next = curr.next;
                curr = curr.next;
            } else {
                prev = curr;
                curr = prev.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        ListNode curr = deleteDuplicates(head);
        Iterator<ListNode> itr = curr.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        // output: 1 2
    }
}
