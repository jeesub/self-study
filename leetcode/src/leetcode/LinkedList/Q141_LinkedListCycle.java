package leetcode.LinkedList;

/**
 * 141. Linked List Cycle.
 * [Linked List: Cycle]
 * runner runs & walker walks
 * if runner == null, it doesn't have a cycle
 * if runner == walker, it has a cycle
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q141_LinkedListCycle {
    public static boolean hasCycle(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (runner == walker) {
                return true;
            }
        }
        return false;
    }
}
