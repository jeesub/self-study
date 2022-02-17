package leetcode.BS;

/**
 * 69. Sqrt(x).
 * [Binary Search]
 * left = 0, right = x
 * while left <= right
 *     if (long)(mid * mid) == x, return mid
 *     if mid * mid > x, right = mid - 1
 *     if mid * mid < x, left = mid + 1
 * return right
 * TC: O(logn)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q69_Sqrtx {
    public static int mySqrt(int x) {
        int left = 0;
        int right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sqMid = (long) mid * mid;
            if (sqMid == x) {
                return mid;
            } else if (sqMid < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
