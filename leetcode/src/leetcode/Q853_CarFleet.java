package leetcode;

import java.util.PriorityQueue;

/**
 * Q853. Car Fleet.
 * time = distance / speed
 * Iterate through the array sorted by position in descending order.
 * Keep max value. It's a bottle neck.
 * curr <= max ? continue
 * curr > max ? max is new bottle neck. count++.
 * position =      [10, 8, 0, 5, 3] => [10, 8, 5, 3, 0]
 * speed =         [2, 4, 1, 1, 3]  => [2, 4, 1, 3, 1]
 * arriving time = [1, 1, 12, 7, 3] => [1, 1, 7, 3, 12]
 *                                      ^ 1st bottle neck
 *                                            ^ 2nd bottle neck
 *                                                  ^ 3rd bottle neck
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q853_CarFleet {

    public static int carFleet(int target, int[] position, int[] speed) {
        if (position.length < 1) {
            return 0;
        }

        PriorityQueue<Car> heap = new PriorityQueue<>();
        double targetInDouble = (double) target;
        for (int i = 0; i < position.length; i++) {
            double time = (targetInDouble - position[i]) / speed[i];
            heap.offer(new Car(position[i], time));
        }

        Car bottleNeck = heap.poll();
        int fleet = 1;
        while (!heap.isEmpty()) {
            Car curr = heap.poll();
            if (curr.time > bottleNeck.time) {
                bottleNeck = curr;
                fleet++;
            }
        }
        return fleet;
    }

    private static class Car implements Comparable<Car> {
        int position;
        double time;

        private Car(int newPosition, double newTime) {
            position = newPosition;
            time = newTime;
        }

        @Override
        public int compareTo(Car other) {
            if (this.position != other.position) {
                return Integer.compare(other.position, this.position);
            }
            return Double.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) {
        int target = 12;
        int[] position = {10,8,0,5,3};
        int[] speed = {2,4,1,1,3};
        System.out.println(carFleet(target, position, speed));
        // output: 3
    }

}
