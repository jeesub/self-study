package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q17. Letter Combinations of a Phone Number.
 * BTR
 * when a length of path == a length of digits, add to a result
 * 
 * @author jeesublee
 */
public class Q17_LetterCombinationsOfAPhoneNumber {
	private static List<List<Character>> LETTERS = Arrays.asList(
		Arrays.asList(),					// 0
		Arrays.asList(),					// 1
		Arrays.asList('a', 'b', 'c'),		// 2
		Arrays.asList('d', 'e', 'f'),		// 3
		Arrays.asList('g', 'h', 'i'),		// 4
		Arrays.asList('j', 'k', 'l'),		// 5
		Arrays.asList('m', 'n', 'o'),		// 6
		Arrays.asList('p', 'q', 'r', 's'),	// 7
		Arrays.asList('t', 'u', 'v'),		// 8
		Arrays.asList('w', 'x', 'y', 'z')	// 9
	);

	public static List<String> letterCombinations(String digits) {
		if (digits.length() == 0) {
			return new ArrayList<>();
		}
		List<String> result = new ArrayList<>();
		buildCombinations(digits, result, new StringBuilder(), 0);
		return result;
	}

	private static void buildCombinations(String digits, List<String> result, StringBuilder path, int ptr) {
		if (path.length() == digits.length()) {
			result.add(path.toString());
			return;
		}
		int currNum = Character.getNumericValue(digits.charAt(ptr));
		int length = path.length();
		for (Character c : LETTERS.get(currNum)) {
			path.append(c);
			buildCombinations(digits, result, path, ptr + 1);
			path.setLength(length);
		}
	}

	public static void main(String[] args) {
		String digits = "37";
		System.out.print(letterCombinations(digits));
		// output : [dp, dq, dr, ds, ep, eq, er, es, fp, fq, fr, fs]
	}
}
