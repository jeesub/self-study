package leetcode;
/**
 * Consecutive Numbers Sum returns the number of possible consecutive sum.
 * ex) N = 21
 * = 21 = (20 + 1)
 * = 10 + 11 = (9 + 1) + (9 + 2)
 * = 6 + 7 + 8 = (5 + 1) + (5 + 2) + (5 + 3)
 * = 1 + 2 + 3 + 4 + 5 + 6 = (0 + 1) + (0 + 2) + (0 + 3) + (0 + 4) + (0 + 5) + (0 + 6)
 * = (x + 1) + (x + 2) + ... + (x + k)
 * = xk + (1 + 2 + ... + k)
 * where x is a seed value & k is a number of elements
 * 
 * N = xk + k(k + 1)/2
 * xk = N - k(k + 1)/2
 * x = (N - k(k + 1)/2) / k
 * x should be a valid integer
 *
 * maxK
 * sum(1..maxK) <= N
 * maxK * (maxK + 1) / 2 <= N
 * maxK^2 + maxK - 2N <= 0
 * maxK <= (-1 +-sqrt(1 + 8N)) / 2
 * maxK <= sqrt(2N + 0.25) - 0.5
 * 
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class ConsecutiveNumbersSum {

    public static int consecutiveNumbersSum(int N) {
        int cnt = 0;
        int maxK = (int) (Math.sqrt(2 * N + 0.25) - 0.5);
        for (int i = 1; i <= maxK; i++) {
            if ((N - i * (i + 1) / 2) % i == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int N = 21;
        System.out.print(consecutiveNumbersSum(N));
        // output: 4
    }

}
