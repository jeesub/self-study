package leetcode;

public class PowXN {

	public double myPow(double x, int n) {
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

	public static void main(String[] args) {
		PowXN solution = new PowXN();
		double x1 = 2.00000; int n1 = 10;
		//Output: 1024.00000
		System.out.println(solution.myPow(x1, n1));

		double x2 = 2.10000; int n2 = 3;
		//Output: 9.26100
		System.out.println(solution.myPow(x2, n2));

		double x3 = 2.00000; int n3 = -2;
		//Output: 0.25000
		System.out.println(solution.myPow(x3, n3));
	}

}
