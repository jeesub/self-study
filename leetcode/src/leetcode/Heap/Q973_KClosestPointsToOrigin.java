package leetcode.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Q973. K Closest Points to Origin.
 * Max Heap sorted by distance with size K.
 * put elements into the Heap.
 * If size is larger than K, remove.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q973_KClosestPointsToOrigin {

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>(
            (a, b) -> ((b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]))
        );
        
        for (int[] point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > k) {
                maxHeap.remove();
            }
        }
        
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.remove();
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] points = {{3, 3}, {-5, 1}, {2, 1}};
        int k = 2;
        for (int[] each : kClosest(points, k)) {
            System.out.print(Arrays.toString(each));
        }
        // [[3, 3], [2, 1]]
    }

}
