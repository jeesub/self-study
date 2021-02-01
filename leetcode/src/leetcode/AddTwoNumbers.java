package leetcode;
/**
 * 
 * while l1 != null || l2 != null || carry != 0
 *   (if node == null, let's say the value is zero)
 *   resNode.val = (l1 + l2 + carry) % 10
 *   carry = (l1 + l2 + carry) % 10
 *   l1 = l1.next, l2 = l2.next (if possible)
 * 
 * @author jeesublee
 *
 */
public class AddTwoNumbers {

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode curr = dummyHead;

		int carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			int val1 = (l1 != null) ? l1.val : 0;
			int val2 = (l2 != null) ? l2.val : 0;

			int sum = val1 + val2 + carry;
			curr.next = new ListNode(sum % 10);
			carry = sum / 10;

			curr = curr.next;
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}

		return dummyHead.next;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);

		System.out.print(addTwoNumbers(l1, l2));
		// output: 7 - 0 - 8
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
				sb.append(curr.val + " - ");
				curr = curr.next;
			}
			sb.setLength(sb.length() - 3);
			return sb.toString();
		}
	}

}
