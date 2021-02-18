package leetcode;
/**
 * Q206. Reverse Linked List.
 * [Iterative]
 * NULL -> 1 -> 2 -> 3 -> 4 -> 5 -> NULL
 *         ^    ^
 *        prev curr
 *
 * while curr != null,
 * tmp = curr.next
 * curr.next = prev
 * prev = curr
 * curr = tmp
 * 
 * [Recursive]
 * 1 -> 2 -> 3 -> 4 -> 5 -> NULL
 * base case: head.next == null, return head
 * main logic: curr.next.next = curr
 * return head
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q206_ReverseLinkedList {

    public static ListNode iterativelyReverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    public static ListNode recursivelyReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = recursivelyReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for (int i = 2; i <= 5; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        ListNode reversedHead = iterativelyReverseList(head);
        System.out.println(reversedHead);
        // output: 5 -> 4 -> 3 -> 2 -> 1
        System.out.println(recursivelyReverseList(reversedHead));
        // output: 1 -> 2 -> 3 -> 4 -> 5
    }

}
