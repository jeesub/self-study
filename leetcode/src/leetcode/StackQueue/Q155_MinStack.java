package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q155. Min Stack.
 * [Stack]
 * Keep two stacks.
 * Stack 1 keeps all elements
 * Stack 2 keeps new min value
 *   - push: if stack 2 is empty, add to the stack
 *           else if new input <= prev min, add to the stack
 *           else, ignore
 *   - pop: if popped value from stack 1 == top value from stack 2, pop stack 2
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q155_MinStack {

    static class MinStack {
        private Deque<Integer> allDeque;
        private Deque<Integer> minDeque;

        /** initialize your data structure here. */
        public MinStack() {
            allDeque = new ArrayDeque<>();
            minDeque = new ArrayDeque<>();
        }

        public void push(int val) {
            allDeque.addLast(val);

            if (minDeque.isEmpty()) {
                minDeque.addLast(val);
                return;
            }

            if (!minDeque.isEmpty() && val <= minDeque.peekLast()) {
                minDeque.addLast(val);
            }
        }

        public void pop() {
            if (allDeque.isEmpty()) {
                return;
            }

            int val = allDeque.removeLast();
            if (!minDeque.isEmpty() && minDeque.peekLast() == val) {
                minDeque.removeLast();
            }
        }

        public int top() {
            return allDeque.peekLast();
        }

        public int getMin() {
            return minDeque.peekLast();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);
        System.out.println(minStack.getMin());
        // output: 0
        System.out.println(minStack.top());
        // output: 3
        minStack.pop();
        System.out.println(minStack.getMin());
        // output: 0
        System.out.println(minStack.top());
        // output: 2
    }

}
