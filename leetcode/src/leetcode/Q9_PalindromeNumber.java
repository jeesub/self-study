package leetcode;
/**
 * Q9. Palindrome Number.
 * If the number is negative, return false
 * If x % 10 != x / digits, return false
 * Iterate with (x % digits) / 10 while digits > 9
 * (because of the input like 100021, we cannot use x > 9 as a condition of while loop)
 * @author jeesublee
 */
public class Q9_PalindromeNumber {

	public static boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		int digits = (int) Math.pow(10, (int) Math.log10(x));
		while (digits > 9) {
			if (x % 10 != x / digits) {
				return false;
			}
			x %= digits;
			x /= 10;
			digits /= 100;
		}
		return true;
	}

	public static void main(String[] args) {
		int x = 123454321;
		System.out.println(isPalindrome(x));
		// output: true
		int y = 10000021;
		System.out.println(isPalindrome(y));
		// output: false
	}

}
