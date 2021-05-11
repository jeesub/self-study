package leetcode.List;

import java.util.ArrayList;
import java.util.List;

/**
 * Q54. Spiral Matrix.
 * Keep track of outer bounds.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q54_SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;
        int left = 0;

        while (true) {
            // top
            if (top > bottom) {
                break;
            }
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // right
            if (right < left) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // bottom
            if (bottom < top) {
                break;
            }
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom--;

            // left
            if (left > right) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;

        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(matrix));
        // output: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }

}
