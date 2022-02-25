package leetcode.BS;

import java.util.ArrayList;
import java.util.List;

/**
 * 658. Find K Closest Elements.
 * [Binary Search & Windows]
 * [1, 2, 2, 2, 2, 2, 2, 4, 5, 6]
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 5, x = 4
 *  <--------------> possible start point (0..len - k)
 *  ^ left         ^ right
 *        ^ mid (start)  ^ mid + k (end)
 * while left < right
 * if (arr[mid + k] < x), left = mid + 1 // to handle the case, e.g. [1,2,2,2,2,3], x = 3
 * else if abs(arr[mid] - x) <= abs(arr[mid + k] - x), right = mid
 * else, left = mid + 1
 * list = [arr[left]..arr[left + k - 1]]
 * TC: O(log(n - k) + k)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q658_FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr[mid + k] < x) {
                left = mid + 1;
            } else if (Math.abs(x - arr[mid]) <= Math.abs(arr[mid + k] - x)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
