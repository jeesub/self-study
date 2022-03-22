package leetcode.TreeMapTreeSet;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 716. Max Stack.
 * [TreeMap & LinkedList]
 * Use TreeMap<int x, List<Node>> & LinkedList<Node>
 *
 * push
 * TC: O(logn)
 * SC: O(1)
 *
 * pop
 * TC: O(logn)
 * SC: O(1)
 *
 * top
 * TC: O(1)
 * SC: O(1)
 *
 * peekMax
 * TC: O(logn)
 * SC: O(1)
 *
 * popMax
 * TC: O(logn)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q716_MaxStack {
    class MaxStack {
        private static final Node DUMMY_NODE = new Node(-1);

        private TreeMap<Integer, List<Node>> treeMap = new TreeMap<>();
        private Node head = DUMMY_NODE;
        private Node tail = DUMMY_NODE;

        public MaxStack() {
            head.next = tail;
            tail.prev = head;
        }

        public void push(int x) {
            Node newNode = new Node(x, tail.prev, tail);
            if (!treeMap.containsKey(x)) {
                treeMap.put(x, new ArrayList<>());
            }
            treeMap.get(x).add(newNode);
        }

        public int pop() {
            Node toRemove = tail.prev;
            toRemove.cutOff();
            List<Node> list = treeMap.get(toRemove.val);
            list.remove(list.size() - 1);
            if (list.isEmpty()) {
                treeMap.remove(toRemove.val);
            }
            return toRemove.val;
        }

        public int top() {
            return tail.prev.val;
        }

        public int peekMax() {
            return treeMap.lastKey();
        }

        public int popMax() {
            List<Node> list = treeMap.lastEntry().getValue();
            Node lastNode = list.remove(list.size() - 1);
            lastNode.cutOff();
            if (list.isEmpty()) {
                treeMap.remove(lastNode.val);
            }
            return lastNode.val;
        }

        private static class Node {
            Node prev;
            Node next;
            int val;

            private Node(int newVal) {
                val = newVal;
                prev = null;
                next = null;
            }

            private Node(int newVal, Node newPrev, Node newNext) {
                val = newVal;
                newPrev.next = this;
                this.prev = newPrev;
                newNext.prev = this;
                this.next = newNext;
            }

            private void cutOff() {
                if (this.prev != null) {
                    this.prev.next = this.next;
                }
                if (this.next != null) {
                    this.next.prev = this.prev;
                }
                this.prev = null;
                this.next = null;
            }
        }
    }
}
