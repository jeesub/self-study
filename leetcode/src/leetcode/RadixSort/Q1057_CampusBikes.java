package leetcode.RadixSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1057. Campus Bikes.
 * [Radix Sort]
 * Make nodes containing worker, bike, and distance.
 * Sort and iterate through the nodes.
 * If the worker or the bike is taken, continue.
 *
 * TC: O(n), where n is the longest length of workers and bikes
 * SC: O(n), where n is the longest length of workers and bikes
 *
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1057_CampusBikes {
    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        List<Node> list = buildList(workers, bikes);

        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (int[] worker : workers) {
            maxX = Math.max(maxX, worker[0]);
            maxY = Math.max(maxY, worker[1]);
        }
        for (int[] bike : bikes) {
            maxX = Math.max(maxX, bike[0]);
            maxY = Math.max(maxY, bike[1]);
        }

        List<List<Node>> sortedBuckets = radixSort(list, maxX + maxY + 2);

        int[] workersBike = new int[workers.length];
        Arrays.fill(workersBike, -1);
        boolean[] visitedBikes = new boolean[bikes.length];
        int numOfAssignment = 0;
        for (List<Node> nodes : sortedBuckets) {
            for (Node each : nodes) {
                if (workersBike[each.worker] != -1 || visitedBikes[each.bike]) {
                    continue;
                }

                workersBike[each.worker] = each.bike;
                visitedBikes[each.bike] = true;
                if (++numOfAssignment == workers.length) {
                    return workersBike;
                }
            }
        }
        return workersBike;
    }

    private static List<Node> buildList(int[][] workers, int[][] bikes) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int distance = Math.abs(workers[i][0] - bikes[j][0])
                        + Math.abs(workers[i][1] - bikes[j][1]);
                list.add(new Node(i, j, distance));
            }
        }
        return list;
    }

    private static List<List<Node>> radixSort(List<Node> list, int max) {
        List<List<Node>> buckets = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Node each : list) {
            buckets.get(each.distance).add(each);
        }

        return buckets;
    }

    private static class Node implements Comparable<Node> {
        private int worker;
        private int bike;
        private int distance;

        private Node(int newWorker, int newBike, int newDistance) {
            worker = newWorker;
            bike = newBike;
            distance = newDistance;
        }

        @Override
        public int compareTo(Node other) {
            if (this.distance != other.distance) {
                return Integer.compare(this.distance, other.distance);
            }

            if (this.worker != other.worker) {
                return Integer.compare(this.worker, other.worker);
            }

            return Integer.compare(this.bike, other.bike);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Node)) {
                return false;
            }
            Node other = (Node) o;

            return this.distance == other.distance
                    && this.worker == other.worker
                    && this.bike == other.bike;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[worker: ").append(worker);
            sb.append(", bike: ").append(bike);
            sb.append(", distance: ").append(distance);
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        int[][] workers = {{0, 0}, {1, 1}, {2, 0}};
        int[][] bikes = {{1, 0}, {2, 2}, {2, 1}};
        System.out.println(Arrays.toString(assignBikes(workers, bikes)));
        // output: [0, 2, 1]
    }
}
