package leetcode;
/**
 * Q7. Reverse Integer.
 * calculate mod and save it
 * times 10 and calculate again
 *
 * overflow
 * 2^1 = 2, 2^2 = 4, 2^3 = 8, 2^4 = 16, 2^5 = 32
 * ... 2^31 = ...8
 * Integer.MAX_VALUE = ...7
 * Integer.MIN_VALUE = -...8
 * Therefore,
 * if res > Integer.MAX_VALUE / 10 or
 * res == Integer.MAX_VALUE / 10 && x % 10 > 7
 * then, it will cause overflow
 * @author jeesublee
 *
 */
public class Q7_ReverseInteger {

	public static int reverse(int x) {
		int res = 0;
		while (x != 0) {
			if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && x % 10 > 7)) {
				// 2^31 = 2147483648
				// Integer.MAX_VALUE = 2147483647
				return 0;
			}
			if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && x % 10 < -8)) {
				// 2^31 = 2147483648
				// Integer.MIN_VALUE = -214783648
				return 0;
			}
			res *= 10;
			res += x % 10;
			x /= 10;
		}
		return res;
	}

	public static void main(String[] args) {
		int x = -12345;
		System.out.println(reverse(x));
		// output: -54321
		int y = 1563857512;
		System.out.println(reverse(y));
		// output: 0
	}

}
