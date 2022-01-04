package BS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find The Duplicates.
 *
 * [Two Pointers]
 * TC: O(m + n)
 * SC: O(n)
 *
 * [Binary Search]
 * TC: O(n * log(m))
 * SC: O(n)
 *
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class FindTheDuplicates {
    /**
     * Two pointers.
     */
    static int[] findDuplicatesTwoPointers(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        List<Integer> result = new ArrayList<>();
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                result.add(arr1[i]);
                i++;
                j++;
            }
        }

        int[] resultArr = new int[result.size()];
        for (int k = 0; k < resultArr.length; k++) {
            resultArr[k] = result.get(k);
        }

        return resultArr;
    }

    /**
     * Binary Search.
     */
    static int[] findDuplicates(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr1) {
            if (binarySearch(arr2, num)) {
                list.add(num);
            }
        }

        int[] result = new int[list.size()];
        for (int k = 0; k < result.length; k++) {
            result[k] = list.get(k);
        }

        return result;
    }

    private static boolean binarySearch(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == num) {
                return true;
            }

            if (arr[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 5, 7};
        int[] arr2 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21};
        System.out.println(Arrays.toString(findDuplicatesTwoPointers(arr1, arr2)));
        System.out.println(Arrays.toString(findDuplicates(arr1, arr2)));
        // output: [3, 5, 7]
    }
}
