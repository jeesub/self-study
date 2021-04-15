package leetcode;

import java.util.List;

/**
 * Q1428. Leftmost Column with at Least a One.
 * [Bisect Left]
 * Iterate every row and keep the min index.
 * If curr val(mat[i][min index]) == 0 ? continue
 * If curr val(mat[i][min index]) == 1 ? Bisect left and update the min index.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1428_LeftmostColumnWithAtLeastAOne {

    public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int lastIndex = dimensions.get(1) - 1;
        int min = Integer.MAX_VALUE;
        for (int i = dimensions.get(0) -1; i >= 0; i--) {
            int rightPointer = Math.min(lastIndex, min);
            if (binaryMatrix.get(i, rightPointer) == 0) {
                continue;
            }
            min = bisectLeft(binaryMatrix, i, rightPointer);
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }

    private static int bisectLeft(BinaryMatrix binaryMatrix, int row, int rightPointer) {
        int leftPointer = 0;
        int midPointer;
        int left = binaryMatrix.get(row, leftPointer);
        int right = binaryMatrix.get(row, rightPointer);
        while (leftPointer < rightPointer) {
            midPointer = leftPointer + (rightPointer - leftPointer) / 2;
            int mid = binaryMatrix.get(row, midPointer);
            if (mid == 0) {
                leftPointer = midPointer + 1;
                left = binaryMatrix.get(row, leftPointer);
            }
            if (mid == 1) {
                rightPointer = midPointer;
                right = mid;
            }
        }
        return rightPointer;
    }

    private static class BinaryMatrix {
        int[][] matrix;

        private BinaryMatrix(int[][] newMatrix) {
            matrix = newMatrix;
        }

        private int get(int row, int col) {
            return matrix[row][col];
        }

        private List<Integer> dimensions() {
            return List.of(matrix.length, matrix[0].length);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 1}};
        BinaryMatrix binaryMatrix = new BinaryMatrix(matrix);
        System.out.println(leftMostColumnWithOne(binaryMatrix));
        // output: 1
    }
}
