package leetcode.Math;

/**
 * 1304. Find N Unique Integers Sum up to Zero.
 * [Math]
 * Fill the array with (-n/2 .. n/2)
 * If n is odd, include 0.
 * Else, exclude 0.
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1304_FindNUniqueIntegersSumUpToZero {
    public int[] sumZero(int n) {
        int[] result = new int[n];
        for (int i = 0; i < n / 2; i++) {
            result[2 * i] = -i - 1;
            result[2 * i + 1] = i + 1;
        }
        return result;
    }
}
