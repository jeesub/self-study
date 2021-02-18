package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Q13. Roman to Integer.
 * Iterate from the end to the start
 * if current value is smaller than previous value, it means current value is negative
 * @author jeesublee
 */
public class Q13_RomanToInteger {

	public static int romanToInt(String s) {
		Map<Character, Integer> values = init();
		int num = 0;
		int prevValue = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			int currValue = values.get(s.charAt(i));
			if (currValue < prevValue) {
				num -= currValue;
			} else {
				num += currValue;
			}
			prevValue = currValue;
		}
		return num;
	}

	private static Map<Character, Integer> init() {
		Map<Character, Integer> values = new HashMap<>();
		values.put('I', 1);
		values.put('V', 5);
		values.put('X', 10);
		values.put('L', 50);
		values.put('C', 100);
		values.put('D', 500);
		values.put('M', 1000);
		return values;
	}

	public static void main(String[] args) {
		String s = "MCMXCIV";
		System.out.print(romanToInt(s));
		// output: 1994
	}

}
