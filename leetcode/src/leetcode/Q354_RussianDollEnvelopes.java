package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Q354. Russian Doll Envelopes.
 * sort by 1. asc width 2. des height
 * find LIS of height
 * 
 * [2, 3], [5, 4], [6, 7], [6, 4]
 *     ^       ^       ^
 * 
 * Get a size of Strictly Increasing Array
 * find using binarySearch
 * case 1. the position <= size
 * 		=> replace
 * case 2. the position > size
 * 		=> add
 * 
 * @author jeesublee
 */
public class Q354_RussianDollEnvelopes {

	public static int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, (a, b) -> {
			if (a[0] != b[0]) {
				return a[0] - b[0];
			} else {
				return b[1] - a[1];
			}
		});

		return findLIS(envelopes);
	}

	private static int findLIS(int[][] envelopes) {
		List<Integer> sia = new ArrayList<>();
		for (int i = 0; i < envelopes.length; i++) {
			int height = envelopes[i][1];
			int pos = Collections.binarySearch(sia, height);
			pos = pos >= 0 ? pos : Math.abs(pos + 1);
			if (pos < sia.size()) {
				sia.set(pos, height);
			} else {
				sia.add(height);
			}
		}
		return sia.size();
	}

	public static void main(String[] args) {
		int[][] input = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
		System.out.println(maxEnvelopes(input));
		// output: 3
	}

}
