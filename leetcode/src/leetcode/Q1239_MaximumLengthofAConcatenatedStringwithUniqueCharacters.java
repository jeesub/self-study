package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Q1239. Maximum Length of a Concatenated String with Unique Characters.
 * iterate through arr
 * 	    - add into a list
 * 	    - make a combination with each string in the list
 * 	        - and check the uniqueness --> add into a list
 * 
 * iterate through the list
 * 	    - get the max length
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1239_MaximumLengthofAConcatenatedStringwithUniqueCharacters {

	public static int maxLength(List<String> arr) {
		List<String> result = new ArrayList<>();
		result.add("");

		for (String str : arr) {
			if (!uniq(str)) {
				continue;
			}

			List<String> tmpResult = new ArrayList<>();
			tmpResult.add(str);

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

	private static boolean uniq(String s) {
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
		List<String> arr = Arrays.asList("cha","r","act","ers");
		System.out.println(maxLength(arr));
		// output: 6
	}

}
