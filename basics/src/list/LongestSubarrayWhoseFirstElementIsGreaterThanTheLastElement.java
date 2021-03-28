package list;
/**
 * Longest Subarray Whose First Element is Greater Than The Last Element.
 * Brute force.
 * Using double for loop, compare the longest length.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class LongestSubarrayWhoseFirstElementIsGreaterThanTheLastElement {

    public static int findLongestLength(int[] nums) {
        int longestLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums.length - i <= longestLength) {
                return longestLength;
            }
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    longestLength = Math.max(longestLength, j - i + 1);
                    break;
                }
            }
        }
        return longestLength;
    }

    public static void main(String[] args) {
        int[] nums = {-7, -2, -1, -11, 5, 8, -6};
        System.out.println(findLongestLength(nums));
        // output: 6
    }

}
