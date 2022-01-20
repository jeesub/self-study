package List;

/**
 * Getting a Different Number.
 * [Sorting]
 * Move a number to it's index in the first iteration.
 * In the second iteration, if i != arr[i], we found it.
 * If we couldn't find it, it means the smallest number is arr.length.
 *
 *    [2, 3, 1, 0]
 *     ^ swap while arr[i] != i && arr[i] < length
 *       swap(0 & 2)
 * -> [1, 3, 2, 0]
 *     ^ swap(1 & 3)
 * -> [3, 1, 2, 0]
 *     ^ swap(3 & 0)
 * -> [0, 1, 2, 3]
 *     ^ stop because arr[i] == i
 *
 * [2, 4, 0, 1]
 *  ^ swap(2, 0)
 * [0, 4, 2, 1]
 *     ^ do not go into the while loop
 * [0, 4, 2, 1]
 *        ^ do not go into the while loop
 * [0, 4, 2, 1]
 *           ^ swap(4, 1)
 * [0, 1, 2, 4]
 *           ^ stop
 *
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class GettingADifferentNumber {
    static int getDifferentNumber(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i && arr[i] < arr.length) {
                swap(arr, i, arr[i]);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                return i;
            }
        }
        return arr.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 0, 1};
        System.out.println(getDifferentNumber(arr));
        // output: 4
    }
}
