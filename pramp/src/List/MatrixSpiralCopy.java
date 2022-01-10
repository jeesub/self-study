package List;

import java.util.Arrays;

/**
 * Matrix Spiral Copy.
 * TC: O(m * n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class MatrixSpiralCopy {
    public static int[] spiralCopy(int[][] inputMatrix) {
        int top = 0;
        int right = inputMatrix[0].length - 1;
        int bottom = inputMatrix.length - 1;
        int left = 0;
        int[] result = new int[inputMatrix.length * inputMatrix[0].length];
        int count = 0;

        while (top <= bottom && left <= right) {
            // top
            for (int i = left; i <= right; i++) {
                result[count++] = inputMatrix[top][i];
            }
            top++;

            // right
            for (int i = top; i <= bottom; i++) {
                result[count++] = inputMatrix[i][right];
            }
            right--;

            // bottom
            if (top > bottom || left > right) {
                break;
            }
            for (int i = right; i >= left; i--) {
                result[count++] = inputMatrix[bottom][i];
            }
            bottom--;

            // left
            if (top > bottom || left > right) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                result[count++] = inputMatrix[i][left];
            }
            left++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] inputMatrix = {{1, 2, 3}};
        System.out.println(Arrays.toString(spiralCopy(inputMatrix)));
    }
}
