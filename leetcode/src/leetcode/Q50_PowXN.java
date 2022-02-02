package leetcode;
/**
 * Q50. Pow(x,n)
 * [Math]
 * [Iterative Solution]
 * TC: O(logN)
 * SC: O(1)
 * [Recursive Solution]
 * Base case: n = 0, return 1.
 * if n is even, return (recursion(x, n / 2))^2
 * if n is odd, return (recursion(x, n / 2))^2 * x
 * TC: O(logN)
 * SC: O(logN)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q50_PowXN {

	public static double myPow(double x, int n) {
		double result = 1.0;
		long absN = Math.abs((long) n);
		while (absN > 0) {
			if ((absN & 1) == 1) {
				result *= x;
			}
			absN /= 2;
			x *= x;
		}
		return n > 0 ? result : 1 / result;
	}

	public static double myPowRecursion(double x, int n) {
		long absN = Math.abs((long)n);
		double result = calPow(x, absN);
		return n > 0 ? result : 1 / result;
	}

	private static double calPow(double x, long n) {
		if (n == 0) {
			return 1d;
		}

		double half = calPow(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		}
		return half * half * x;
	}

	public static void main(String[] args) {
		double x1 = 2.00000; int n1 = 10;
		//Output: 1024.00000
		System.out.println(myPow(x1, n1));
		System.out.println(myPowRecursion(x1, n1));

		double x2 = 2.10000; int n2 = 3;
		//Output: 9.26100
		System.out.println(myPow(x2, n2));
		System.out.println(myPowRecursion(x1, n1));

		double x3 = 2.00000; int n3 = -2;
		//Output: 0.25000
		System.out.println(myPow(x3, n3));
		System.out.println(myPowRecursion(x1, n1));
	}

}
