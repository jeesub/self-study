package basics;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * i 				0  1  2  3  4  5  6  7  8
 * value			2  1  3  1  5  2  4  4  3
 * currMax			.  2  .  5  .  .  9  .  .
 * possibleMax		2  .  5  .  9  .  10 11 .
 * possibleMaxIdx	0  .  2  .  4  .  6  7  .
 * made a choice	   v     v        v      
 * 
 * result = [0, 2, 4]
 * 
 * @author jeesublee
 *
 */
public class ArrayHooper {

	public static List<Integer> findMinJumpPath(int[] arr) {
		List<Integer> result = new ArrayList<>();
		int currMax = 0;
		int possibleMax = 0;
		int possibleMaxIdx = 0;

		for (int i = 0; i < arr.length; i++) {
			if (i > currMax) { // have to choose one of the candidates
				if (possibleMax == currMax) { // cannot go further
					return new ArrayList<>();
				}
				currMax = possibleMax;
				result.add(possibleMaxIdx);
			}
			if (i + arr[i] > possibleMax) { // It is a better candidate
				possibleMax = i + arr[i];
				possibleMaxIdx = i;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] arr = {2, 1, 3, 1, 5, 2, 4, 4, 3};
		System.out.println(findMinJumpPath(arr));
		// output: 0, 2, 4
	}

}
