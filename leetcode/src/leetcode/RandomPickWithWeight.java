package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * keep an accumulated sum array
 * in this case, we can return a result in O(logN) using BS
 * w = [3, 14, 1, 7]
 * acc = [3, 17, 18, 25]
 * get a random number(from 1 to total) using rand function
 *
 * @author jeesublee
 *
 */
public class RandomPickWithWeight {
	public static class Solution {
		private int[] acc;
		private int total;
		private Random rand;

		public Solution(int[] w) {
			this.acc = new int[w.length];
			this.total = 0;
			this.rand = new Random(); // create instance of Random class
			for (int i = 0; i < acc.length; i++) {
				total += w[i];
				acc[i] = total;
			}
		}

		public int pickIndex() {
			// Generate random integers in range 0 to total
			int pickedNum = rand.nextInt(total) + 1;
			int pickedIdx = Arrays.binarySearch(acc, pickedNum);
			if (pickedIdx < 0) {
				pickedIdx = Math.abs(pickedIdx + 1);
			}
			return pickedIdx;
		}
	}
	
	public static void main(String[] args) {
		// ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
		// [[[1,3]],[],[],[],[],[]]
		int[] w = {1, 3};
		Solution obj = new Solution(w);
		for (int i = 0; i < 10; i++) {
			System.out.print(obj.pickIndex());
		}
	}
}
