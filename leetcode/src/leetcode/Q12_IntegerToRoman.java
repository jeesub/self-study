package leetcode;
/**
 * Q12. Integer to Roman.
 * Greedy Approach
 * 1984 = 1000 + 900 + (100 + 10 + 10 + 10) + 4
 * Let's think 900 (1000 - 100) as one character, CM
 * Like number system we use, we need to fill the larger one first
 * to write 900, we need to use CM (900) because, CM is bigger than D (500)
 * to wirte 800, we need to use D (500) and then use C (100)
 * 
 * We have options of 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9 , 5, 4,  1
 * We can write it as M   , CM , D  , CD , C  , XC, L , XL, X , IX, V, IV, I
 * @author jeesublee
 */
public class Q12_IntegerToRoman {

	public static String intToRoman(int num) {
		int[] nums = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] romans = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int remains = num;
		int ptr = 0;
		StringBuilder roman = new StringBuilder();
		while (remains > 0) {
			while (remains >= nums[ptr]) {
				roman.append(romans[ptr]);
				remains -= nums[ptr];
			}
			while (ptr < nums.length && remains < nums[ptr]) {
				ptr++;
			}
		}
		return roman.toString();
	}

	public static void main(String[] args) {
		int num = 1994;
		System.out.print(intToRoman(num));
		// output: MCMXCIV
	}

}
