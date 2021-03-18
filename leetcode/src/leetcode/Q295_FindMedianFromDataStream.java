package leetcode;

import java.util.PriorityQueue;

/**
 * Q295. Find Median from Data Stream.
 * Use min heap and max heap.
 * A max heap keeps under median numbers.
 * A min heap keeps over median numbers.
 * Make min heap size == max heap size, or max heap size = min heap size + 1.
 * Median will be always peekable.
 * 
 * [Add Num]
 * Decide where a new num has to go and put into a heap.
 * Make two heaps' size balanced.
 * 
 * [Find Median]
 * If min heap size == max heap size, return avg(minHeap's peek value, maxHeap's peek value).
 * If min heap size > max heap size, return minHeap's peek value.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q295_FindMedianFromDataStream {

    public final static class MedianFinder {
        PriorityQueue<Integer> underMedianMaxHeap;
        PriorityQueue<Integer> overMedianMinHeap;

        public MedianFinder() {
            underMedianMaxHeap = new PriorityQueue<Integer>((a, b) -> (b.compareTo(a)));
            overMedianMinHeap = new PriorityQueue<Integer>();
        }

        public void addNum(int num) {
            if (underMedianMaxHeap.isEmpty()) {
                underMedianMaxHeap.offer(num);
                return;
            }

            if (underMedianMaxHeap.peek() >= num) {
                underMedianMaxHeap.offer(num);
                while (underMedianMaxHeap.size() - 1 > overMedianMinHeap.size()) {
                    overMedianMinHeap.offer(underMedianMaxHeap.poll());
                }
                return;
            }

            overMedianMinHeap.offer(num);
            while (overMedianMinHeap.size() > underMedianMaxHeap.size()) {
                underMedianMaxHeap.offer(overMedianMinHeap.poll());
            }
            return;
        }

        public double findMedian() {
            if (underMedianMaxHeap.size() == overMedianMinHeap.size()) {
                return (double) (underMedianMaxHeap.peek() + overMedianMinHeap.peek()) / 2;
            }
            return (double) (underMedianMaxHeap.peek());
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(6);
        medianFinder.addNum(5);
        medianFinder.addNum(3);
        medianFinder.addNum(4);
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        // output: 3.5
    }

}
