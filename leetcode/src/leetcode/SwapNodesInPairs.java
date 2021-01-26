package leetcode;
/**
 * 
 * if we have the first node and the second node,
 * the first node's next is the result of the swap process of second's next node
 * the second node's next is the first node
 * and we can return the second node
 * 
 * @author jeesublee
 *
 */
public class SwapNodesInPairs {

	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = swapPairs(newHead.next);
		newHead.next = head;
		return newHead;
	}

	public static class ListNode {
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
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		System.out.print(swapPairs(head));
		// output: 2 - 1 - 4 - 3
	}

}
