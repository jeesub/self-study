package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q362. Design Hit Counter.
 * [Queue]
 * Queue<Integer> stores hits' timestamp
 * Poll until peeked value is in the time range.
 * TC(hit method): O(k), where k is the number of elements to poll
 * TC(getHits method): O(k), where k is the number of elements to poll
 * SC: O(n), where n is the number of elements in the time range
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q362_DesignHitCounter {

    private static class HitCounter {
        private static final int TIME_RANGE = 300;
        Deque<Integer> deque;

        private HitCounter() {
            deque = new ArrayDeque<>();
        }

        public void hit(int timestamp) {
            deque.add(timestamp);
            refresh(timestamp);
        }

        public int getHits(int timestamp) {
            refresh(timestamp);
            return deque.size();
        }

        private void refresh(int timestamp) {
            while (!deque.isEmpty() && deque.peek() <= timestamp - TIME_RANGE) {
                deque.remove();
            }
        }
    }

    public static void main(String[] args) {
        HitCounter hc = new HitCounter();
        hc.hit(1);
        hc.hit(2);
        hc.hit(3);
        System.out.println(hc.getHits(4));
        // output: 3
        System.out.println(hc.getHits(301));
        // output: 2
    }

}
