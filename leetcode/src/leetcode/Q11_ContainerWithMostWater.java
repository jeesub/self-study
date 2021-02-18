package leetcode;
/**
 * Q11. Container With Most Water.
 * Two Pointers
 * i starts from the start point
 * j starts from the end point
 * to make a larger area, we give up the shorter line
 * when we pick a new lines, calculate new area
 * @author jeesublee
 *
 */
public class Q11_ContainerWithMostWater {

	public static int maxArea(int[] height) {
		int i = 0;
		int j = height.length - 1;
		int maxArea = 0;
		while (i < j) {
			int newArea = (j - i) * Math.min(height[i], height[j]);
			maxArea = Math.max(maxArea, newArea);
			if (height[i] < height[j]) {
				i++;
			} else {
				j--;
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[] height = {1,8,6,2,5,4,8,3,7};
		System.out.print(maxArea(height));
		// output: 49
	}

}
