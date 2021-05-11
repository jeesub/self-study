package leetcode.DP;
/**	
 * Q91. Decode Ways.
 * DP
 * check if number at the pointer can be a one digit number and can be a two digits number
 * if it can be a valid number, get the previous number and save to it
 * 
 * if the number is a valid one digit number, it means that the previous parts can be valid
 * so get the counts from the previous column
 * 
 * if the number is a valid two digits number, it means that the previous parts of myself can be valid
 * so get the counts from the second previous column
 * 
 *    x 1 1 2 2 3
 *  -+----------
 *  1|1 1 1 2 (is a valid one digit number, get values from dp[i][j - 1] and dp[i + 1][j - 1])
 *  2|0 0 1 1 (is a valid two digits number, get values from dp[i - 1][j - 2] and dp[i][j - 2])
 *
 * dp[0, 3] means, we want to count the previous part (11) because 2 is valid
 * as a result, we now have (1 1 2) and (11 2)
 * 
 * dp[1, 3] means, we want to count the previous part (1) because 12 is valid
 * as a result, we now have (1 12)
 *
 *    x 1 1 2 2 3
 *  -+----------
 *  1|1 1 1 2 3 5
 *  2|0 0 1 1 2 3
 *  
 *  dp[0, 5] means, we want to count the previous part (1122) because 3 is valid
 *  according to dp[0, 4], we can make 3 cases, when we use 2 as a valid one digit number
 *  and as we can see, when the previous one is 22, we can make 2 cases
 *  so, if we use 3 as a valid one digit number, we can make 3 + 2 = 5 cases
 *  
 * @author jeesublee
 */
public class Q91_DecodeWays {

	public static int numDecodings(String s) {
		int[][] dp = new int[2][s.length() + 1];
		dp[0][0] = 1;
		for (int j = 1; j < s.length() + 1; j++) {
			if (isValidOneDigit(s, j - 1)) {
				dp[0][j] = dp[0][j - 1] + dp[1][j - 1];
			}
			if (isValidTwoDigits(s, j - 1)) {
				dp[1][j] = dp[0][j - 2] + dp[1][j - 2];
			}
		}
		return dp[0][s.length()] + dp[1][s.length()];
	}

	private static boolean isValidOneDigit(String s, int pointer) {
		int num = Character.getNumericValue(s.charAt(pointer));
		if (num > 0 && num < 10) {
			return true;
		}
		return false;
	}

	private static boolean isValidTwoDigits(String s, int pointer) {
		if (pointer == 0) {
			return false;
		}
		int num = Character.getNumericValue(s.charAt(pointer - 1)) * 10 + Character.getNumericValue(s.charAt(pointer));
		if (num > 9 && num <= 26) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String s = "11223";
		System.out.print(numDecodings(s));
		// output : 8
	}

}
