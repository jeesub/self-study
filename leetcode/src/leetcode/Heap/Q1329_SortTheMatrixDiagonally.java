package leetcode.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1329. Sort the Matrix Diagonally.
 * 1. Move start pointer from mat[mat.length - 1][0] to mat[0][0] and sort diagonally.
 * 2. Move start pointer from mat[0][1] to mat[0][mat[0].length - 1] and sort diagonally.
 * TC: O(m * n * log(min(m, n)))
 * SC: O(min(m, n))
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1329_SortTheMatrixDiagonally {
    public static int[][] diagonalSort(int[][] mat) {
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        for (int i = mat.length - 1; i >= 0; i--) {
            sortOneLine(mat, minHeap, i, 0);
        }
        for (int j = 1; j < mat[0].length; j++) {
            sortOneLine(mat, minHeap, 0, j);
        }
        return mat;
    }

    private static void sortOneLine(int[][] mat, PriorityQueue<Integer> minHeap, int startRow, int startCol) {
        int row = startRow;
        int col = startCol;
        while (row < mat.length && col < mat[0].length) {
            minHeap.add(mat[row++][col++]);
        }
        row = startRow;
        col = startCol;
        while (row < mat.length && col < mat[0].length) {
            mat[row++][col++] = minHeap.poll();
        }
    }

    public static void main(String[] args) {
        int[][] mat = {{3, 2, 1}, {2, 2, 1}};
        diagonalSort(mat);
        for (int[] each : mat) {
            System.out.println(Arrays.toString(each));
        }
        // output: [[2, 1, 1], [2, 3, 2]]
    }
}
