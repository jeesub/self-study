package leetcode;
/**
 * Trapping Rain Water.
 * at the point of i, we can store water with height min(left max, right max) - curr height
 * 
 * int[] leftMaxSoFar: max height of (0..i)
 * int[] rightMaxSoFar: max height of (length..i)
 * volume[i] = min(leftMaxSoFar[i], rightMaxSoFar[i]) - height[i]
 * 
 * @author Jeesub Lee (jeesubl)
 *
 */
public class TrappingRainWater {

    public static int trap(int[] height) {
        int[] leftMaxHeight = initLeftMaxHeight(height);
        int[] rightMaxHeight = initRightMaxHeight(height);
        int volume = 0;
        for (int i = 0; i < height.length; i++) {
            volume += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - height[i];
        }
        return volume;
    }

    private static int[] initLeftMaxHeight(int[] height) {
        int[] leftMaxHeight = new int[height.length];
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            max = Math.max(max, height[i]);
            leftMaxHeight[i] = max;
        }
        return leftMaxHeight;
    }

    private static int[] initRightMaxHeight(int[] height) {
        int[] rightMaxHeight = new int[height.length];
        int max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            rightMaxHeight[i] = max;
        }
        return rightMaxHeight;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.print(trap(height));
        // output: 6
    }

}
