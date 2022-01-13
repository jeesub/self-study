package List;

import java.util.Arrays;

/**
 * Array of Array Products.
 * [List]
 * arr = [2, 7, 3, 4]
 * left = [2, 14, 42, 168] -> accumulated products
 * right = [168, 84, 12, 4] <- accumulated products
 * result[i] = left[i - 1] * right[i + 1]
 * TC: O(n)
 * SC: O(n)
 *
 * [Optimized]
 * arr = [2, 7, 3, 4]
 * result = [1, 2, 14, 42] ->
 * result = [84, 24, 48, 42] <- int acc = leftward accumulated products
 * result[i] = result[i] * acc
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class ArrayOfArrayProducts {
    static int[] arrayOfArrayProducts(int[] arr) {
        if (arr.length == 0 || arr.length == 1) {
            return new int[0];
        }

        int[] result = new int[arr.length];
        int acc = 1;
        for (int i = 0; i < arr.length; i++) {
            result[i] = acc;
            acc *= arr[i];
        }

        acc = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            result[i] *= acc;
            acc *= arr[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 3, 4};
        System.out.println(Arrays.toString((arr)));
        // output: [84, 24, 56, 42]
    }
}
