package leetcode.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1146. Snapshot Array.
 * List<List<Pair(snapId, val)>>
 * int currSnapId = 0
 *
 * Constructor: build a List<List<Pair>>
 * TC: O(n)
 * SC: O(n)
 *
 * set(index, val):
 *     if list.get(index).last.snapId == currSnapId ? update list.get(index).last
 *     else, list.get(index).add(new Pair(currSnapId, val))
 * TC: O(1)
 * SC: O(1)
 *
 * snap(): currSnapId++
 * TC: O(1)
 * SC: O(1)
 *
 * get(index, snapId): list.get(index).binarySearch(snapId)
 * TC: O(logK), K = numOfSnaps
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1146_SnapshotArray {
    class SnapshotArray {
        private List<List<Pair>> list = new ArrayList<>();
        private int currSnapId = 0;

        public SnapshotArray(int length) {
            for (int i = 0; i < length; i++) {
                list.add(new ArrayList<>());
                list.get(i).add(new Pair());
            }
        }

        public void set(int index, int val) {
            List<Pair> nums = list.get(index);
            if (nums.get(nums.size() - 1).snapId == currSnapId) {
                nums.set(nums.size() - 1, new Pair(currSnapId, val));
            } else {
                nums.add(new Pair(currSnapId, val));
            }
        }

        public int snap() {
            currSnapId++;
            return currSnapId - 1;
        }

        public int get(int index, int snapId) {
            List<Pair> nums = list.get(index);
            int numsIndex = Collections.binarySearch(nums, new Pair(snapId, -1));
            if (numsIndex < 0) {
                numsIndex = -numsIndex - 2;
            }
            return nums.get(numsIndex).val;
        }

        private static class Pair implements Comparable<Pair> {
            int snapId;
            int val;

            private Pair() {
                snapId = 0;
                val = 0;
            }

            private Pair(int newSnapId, int newVal) {
                snapId = newSnapId;
                val = newVal;
            }

            @Override
            public int compareTo(Pair other) {
                return Integer.compare(this.snapId, other.snapId);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Pair)) {
                    return false;
                }
                Pair other = (Pair) o;
                return this.snapId == other.snapId;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("[").append(snapId).append(", ").append(val).append("]");
                return sb.toString();
            }
        }
    }
}
