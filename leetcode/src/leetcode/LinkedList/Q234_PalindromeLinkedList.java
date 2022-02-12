package leetcode.LinkedList;

/**
 * 234. Palindrome Linked List.
 * [Linked List]
 * 1. Reverse the later half.
 *     prev = start node of the later half
 *     curr = prev.next
 *     prev.next = null
 *     while (curr != null)
 *         tmp = curr.next
 *         curr.next = prev
 *         prev = curr
 *         curr = tmp
 *     prev = new start node of the later half
 * 2. Check if the former half and later half matches
 *     former = head
 *     later = prev
 *     while (later != null)
 *         if (former.val != later.val), no palindrome
 *         former = former.next
 *         later = later.next
 * 3. Re-reverse the later half.
 *
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q234_PalindromeLinkedList {
    public static boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }
        ListNode laterHead = findLaterHead(head);
        ListNode newLaterHead = reverse(laterHead);
        boolean isPalindrome = isPalindrome(head, newLaterHead);
        reverse(newLaterHead);
        return isPalindrome;
    }

    private static ListNode findLaterHead(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while (runner != null) {
            walker = walker.next;
            if (runner.next != null) {
                runner = runner.next.next;
            } else {
                runner = runner.next;
            }
        }
        return walker;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = head;
        ListNode curr = head.next;
        prev.next = null;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    private static boolean isPalindrome(ListNode former, ListNode later) {
        while (later != null) {
            if (former.val != later.val) {
                return false;
            }
            former = former.next;
            later = later.next;
        }
        return true;
    }
}
