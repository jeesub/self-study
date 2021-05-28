package leetcode.Math;

import java.util.Arrays;

/**
 * Q48. Rotate Image
 * [Math]
 * Transpose the matrix.
 * And flip vertically.
 * TC: O(n), where n is the number of elements.
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q48_RotateImage {

    public static void rotate(int[][] matrix) {
        transpose(matrix);
        flip(matrix);
    }

    private static void transpose(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                swap(matrix, i, j, length - j - 1, length - i - 1);
            }
        }
    }

    private static void flip(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            for (int j = 0; j < length; j++) {
                swap(matrix, i, j, length - i - 1, j);
            }
        }
    }

    private static void swap(int[][] matrix, int rowOne, int colOne, int rowTwo, int colTwo) {
        int tmp = matrix[rowOne][colOne];
        matrix[rowOne][colOne] = matrix[rowTwo][colTwo];
        matrix[rowTwo][colTwo] = tmp;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        // output:
        // [7, 4, 1]
        // [8, 5, 2]
        // [9, 6, 3]
    }

}
