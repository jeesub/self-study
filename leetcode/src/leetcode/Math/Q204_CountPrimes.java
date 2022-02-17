package leetcode.Math;

/**
 * 204. Count Primes.
 * [Sieve of Eratosthenes]
 * keep removing prime number's multiples
 * from 2 to sqrt(n),
 * if curr is already removed, continue
 * if curr is not removed, it's a prime number. remove curr's multiples (from num = curr * curr, until num < n).
 * TC: O(N * loglogN)
 * SC: O(N)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q204_CountPrimes {
    public static int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n];
        for (int i = 2; i * i <= n; i++) {
            if (notPrimes[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                notPrimes[j] = true;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrimes[i]) {
                count++;
            }
        }
        return count;
    }
}
