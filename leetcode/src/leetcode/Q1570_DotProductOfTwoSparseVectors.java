package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Q1570. Dot Product of Two Sparse Vectors.
 * Keep non-zeros in a HashMap<index, value>.
 */
public class Q1570_DotProductOfTwoSparseVectors {

    public static class SparseVector {
        Map<Integer, Integer> map;

        SparseVector(int[] nums) {
            map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    map.put(i, nums[i]);
                }
            }
        }

        public int size() {
            return map.size();
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int result = 0;
            if (this.size() < vec.size()) {
                for (int index : map.keySet()) {
                    if (!vec.map.containsKey(index)) {
                        continue;
                    }
                    result += map.get(index) * vec.map.get(index);
                }
            } else {
                for (int index : vec.map.keySet()) {
                    if (!map.containsKey(index)) {
                        continue;
                    }
                    result += map.get(index) * vec.map.get(index);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        // nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
        int[] nums1 = {1, 0, 0, 2, 3};
        SparseVector vec1 = new SparseVector(nums1);
        int[] nums2 = {0, 3, 0, 4, 0};
        SparseVector vec2 = new SparseVector(nums2);
        System.out.println(vec1.dotProduct(vec2));
        // output: 8
    }

}
