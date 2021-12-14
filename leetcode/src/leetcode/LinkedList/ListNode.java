package leetcode.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * ListNode.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class ListNode implements Iterable<ListNode> {
    int val;
    ListNode next;

    public ListNode() {
        val = 0;
    }

    public ListNode(int newVal) {
        val = newVal;
    }

    public ListNode(int newVal, ListNode newNext) {
        val = newVal;
        next = newNext;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ListNode)) {
            return false;
        }
        ListNode other = (ListNode) o;
        return this.val == other.val && this.next.equals(other.next);
    }

    @Override
    public String toString() {
        return Integer.valueOf(val).toString();
    }

    @Override
    public Iterator<ListNode> iterator() {
        return new NodeIterator(this);
    }

    private static class NodeIterator implements Iterator<ListNode> {
        ListNode next;

        public NodeIterator(ListNode head) {
            next = head;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public ListNode next() {
            if (next == null) {
                throw new NoSuchElementException("There is no next node!");
            }
            ListNode toReturn = next;
            next = next.next;
            return toReturn;
        }
    }
}
