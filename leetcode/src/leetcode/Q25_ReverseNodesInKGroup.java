package leetcode;
/**
 * Q25. Reverse Nodes in k-Group.
 * 1 - 2 - 3 - 4 - 5 - 6 and k = 3
 * ^
 * head
 * 1 - 2 - 3 - 4 - 5 - 6
 *     ^
 * new head = curr
 * curr = curr.next
 * new head.next = previous new head
 * 2 - 1 - 3 - 4 - 5 - 6
 *         ^
 * new head = curr
 * curr = curr.next
 * new head.next = previous new head
 * 3 - 2 - 1 - 4 - 5 - 6
 * 
 * @author jeesublee
 */
public class Q25_ReverseNodesInKGroup {

	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode newHead = null;
		ListNode tail = null;
		ListNode runner = head;
		while (runner != null) {
			int cnt = 0;
			while (runner != null && cnt < k) {
				runner = runner.next;
				cnt++;
			}
			if (cnt == k) {
				if (newHead == null) {
					newHead = getNewHead(head, k);
					tail = head;
					tail.next = runner;
				} else {
					ListNode nextHead = tail.next;
					tail.next = getNewHead(nextHead, k);
					tail = nextHead;
					tail.next = runner;
				}
			}
		}
		return newHead;
	}

	private static ListNode getNewHead(ListNode head, int k) {
		ListNode newHead = null;
		ListNode curr = head;
		while (k > 0) {
			ListNode prevHead = newHead;
			newHead = curr;
			curr = curr.next;
			newHead.next = prevHead;
			k--;
		}
		return newHead;
	}

	private static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			ListNode curr = this;
			while (curr != null) {
				sb.append(curr.val);
				sb.append(" - ");
				curr = curr.next;
			}
			sb.setLength(sb.length() - 3);
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode curr = head;
		for (int i = 2; i <= 10; i++) {
			curr.next = new ListNode(i);
			curr = curr.next;
		}
		int k = 3;
		System.out.print(reverseKGroup(head, k));
		// output: 3 - 2 - 1 - 6 - 5 - 4 - 9 - 8 - 7 - 10
	}

}
