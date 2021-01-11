package leetcode;
/**
 * Iterate with a pointer
 * 
 * 1. continue on spaces
 * 2. get the sign value if it is - or +
 * 3. get numbers and make it into one numbrer. If it meets non-digit character, return 0
 *    need to check the range of int
 * 4. when the pointer meets the end or space, return it
 * @author jeesublee
 *
 */
public class StringToInteger {

	public static int myAtoi(String s) {
		int ptr = 0;
		int val = 0;
		boolean isNegative = false;

		// ignore spaces
		while (ptr < s.length() && s.charAt(ptr) == ' ') {
			ptr++;
		}

		// get a sign
		if (ptr < s.length() && s.charAt(ptr) == '-') {
			isNegative = true;
			ptr++;
		} else if (ptr < s.length() && s.charAt(ptr) == '+') {
			ptr++;
		}

		if (ptr < s.length() && !Character.isDigit(s.charAt(ptr))) {
			return 0;
		}

		while (ptr < s.length() && Character.isDigit(s.charAt(ptr))) {
			int curVal = Character.getNumericValue(s.charAt(ptr));
			if (isNegative) {
				if (val > Integer.MAX_VALUE / 10 || val == Integer.MAX_VALUE / 10 && curVal > 8) {
					return Integer.MIN_VALUE;
				}
			} else {
				if (val > Integer.MAX_VALUE / 10 || val == Integer.MAX_VALUE / 10 && curVal > 7) {
					return Integer.MAX_VALUE;
				}
			}
			val = val * 10 + curVal;
			ptr++;
		}
		if (isNegative) {
			return -1 * val;
		}
		return val;
	}

	public static void main(String[] args) {
		String s = "4193 with words";
		System.out.print(myAtoi(s));
		// output: 4193
	}

}
