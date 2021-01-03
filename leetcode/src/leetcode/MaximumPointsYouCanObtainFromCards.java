package leetcode;
/**
 * 
 * sliding window
 * init: make an accumulate sum (0..k - 1)
 * 
 * logic
 * for loop (i = 0..k - 1)
 * currPoint = make a window left shift
 * maxPoint = max(prev maxPoint, new maxCandidate)
 * 
 * ex) [1, 2, 3, 4, 5, 6, 1], k = 3
 * currPoint = 1 + 2 + 3 = 6
 * 1) i = 0
 * currPoint - cardPoints[2 (k - i - 1)] + cardPoints[6 (cardPoints.length - i - 1)]
 *     = 6 - 3 + 1 = 4
 * maxPoint = max(6, 4) = 6
 * 2) i = 1
 * currPoint - cardPoints[1] + cardPoints[5]
 *     = 4 - 2 + 6 = 8
 * maxPoint = max(6, 8) = 8
 * 3) i = 2
 * currPoint - cardPoints[0] + cardPoints[4]
 *     = 8 - 1 + 5 = 12
 * maxPoint = max(8, 12) = 12
 * 
 * @author jeesublee
 *
 */
public class MaximumPointsYouCanObtainFromCards {

	public static int maxScore(int[] cardPoints, int k) {
		int len = cardPoints.length;
		int currPoint = 0;
		for (int i = 0; i < k; i++) {
			currPoint += cardPoints[i];
		}
		int maxPoint = currPoint;

		for (int i = 0; i < k; i++) {
			currPoint = currPoint - cardPoints[k - i - 1] + cardPoints[len - i - 1];
			maxPoint = Math.max(maxPoint, currPoint);
		}

		return maxPoint;
	}

	public static void main(String[] args) {
		int[] cardPoints = {1,2,3,4,5,6,1};
		int k = 3;
		System.out.print(maxScore(cardPoints, k));
		// output: 12
	}

}
