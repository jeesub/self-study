package leetcode;
/**
 * 
 * use two pointers
 * the first pointer starts and go n steps alone
 * and then the second pointer starts
 * when the first pointer is null, it means the second pointer is on the Nth from the end
 * to remove the Nth node, the second pointer stops at (N+1)th from the end
 * then, (N+1)th Node.next = (N+1)th Node.next.next
 * 
 * what if Nth node is head?
 * head.next is new head
 * 
 * @author jeesublee
 *
 */
public class RemoveNthNodeFromEndOfList {
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;
		ListNode first = dummyHead;
		ListNode second = dummyHead;
		for (int i = 0; i <= n; i++) {
			first = first.next;
		}
		while (first != null) {
			first = first.next;
			second = second.next;
		}
		second.next = second.next.next;
		return dummyHead.next;
	}

	private static class ListNode {
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			ListNode curr = this;
			while (curr != null) {
				sb.append(curr.val);
				sb.append(" - ");
				curr = curr.next;
			}
			sb.setLength(sb.length() - 2);
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode curr = head;
		for (int i = 2; i <= 5; i++) {
			curr.next = new ListNode(i);
			curr = curr.next;
		}
		int n = 2;
		head = removeNthFromEnd(head, n);
		System.out.println(head);
		// output: 1 - 2 - 3 - 5
	}
}
