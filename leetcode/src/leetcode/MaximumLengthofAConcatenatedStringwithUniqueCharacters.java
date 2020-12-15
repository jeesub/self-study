package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumLengthofAConcatenatedStringwithUniqueCharacters {

	/**
	 * iterate through arr
	 * 		- add into a list
	 * 		- make a combination with each string in the list
	 * 			- and check the uniqueness --> add into a list
	 * 
	 * iterate through the list
	 * 		- get the max length
	 */

	public int maxLength(List<String> arr) {
		List<String> result = new ArrayList<>();
		result.add("");
		for (String str : arr) {
			List<String> tmpResult = new ArrayList<>();

			if (uniq(str)) {
				tmpResult.add(str);
			}

			for (String res : result) {
				String conc = new StringBuilder(res).append(str).toString();
				if (uniq(conc)) {
					tmpResult.add(conc);
				}
			}
			result.addAll(tmpResult);
		}

		return result.stream().mapToInt(s -> s.length()).max().getAsInt();
	}

	private boolean uniq(String s) {
		boolean[] duplicate = new boolean[26];
		for (char c : s.toCharArray()) {
			if (duplicate[c - 'a']) {
				return false;
			}
			duplicate[c - 'a'] = true;
		}
		return true;
	}

	public static void main(String[] args) {
		MaximumLengthofAConcatenatedStringwithUniqueCharacters solution = new MaximumLengthofAConcatenatedStringwithUniqueCharacters();
		List<String> arr = Arrays.asList("cha","r","act","ers");
		System.out.println(solution.maxLength(arr));
		// output: 6
	}

}
