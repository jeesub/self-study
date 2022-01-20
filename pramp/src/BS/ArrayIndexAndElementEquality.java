package BS;

/**
 * Array Index & Element Equality.
 * [Binary Search]
 * [-8,0,2,5]
 *     ^ index: 1, value: 0 // value < index -> go to right
 *       ^ index: 2, value: 2 // value == index -> found it!
 * [-1,0,3,6]
 *     ^ index: 1, value: 0 // value < index -> go to right
 *       ^ index: 2, value: 3 // value > index -> go to left
 *         left > right // stop and return -1
 * [-1, 3, 10]
 *      ^ index: 1, value: 3 // value > inder -> go to left
 *  ^ index: 0, value: -1 // value < index -> go to right
 *    left > right // stop and return -1
 * TC: O(logN)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class ArrayIndexAndElementEquality {
    static int indexEqualsValueSearch(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == mid && (mid == 0 || arr[mid - 1] != mid - 1)) {
                return mid;
            }
            if (arr[mid] >= mid) {
                right = mid - 1;
            } else if (arr[mid] < mid) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-8, 0, 2, 5};
        System.out.println(indexEqualsValueSearch(arr));
        // output: 2
    }
}
