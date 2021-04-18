package dp;
/**
 * Jump Game.
 * Input: [2, 3, 0, 1, 4]
 *         ^      ]
 *         ^  ^         ]
 * 
 * i = 0 (nums[0] = 2)
 *   possible max index = 2
 *   current max index = 2
 *   jump count + 1
 * i = 1 (nums[1] = 3)
 *   possible max index = 3
 *   current max index = 2 (Do not update yet)
 * i = 2 (nums[2] = 0)
 *   possible max index = 3
 *   current max index = 2 (We need to choose now where to jump) -> 3
 *   where to go? go to the possible max we have (3) -> jump count + 1
 *   
 * Output: 2
 * 
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q7_JumpGame {
    public static int getMinJump(int[] nums) {
        int possibleMax = 0;
        int currentMax = 0;
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            possibleMax = Math.max(possibleMax, i + nums[i]);
            if (possibleMax >= nums.length - 1) {
                count++;
                break;
            }
            if (i == currentMax) {
                if (currentMax >= possibleMax) {
                    return -1;
                }
                currentMax = possibleMax;
                count++;
            }
        }
        return possibleMax < nums.length - 1 ? -1 : count;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 0, 0, 4};
        System.out.println(getMinJump(nums1));
        // output: -1
        int[] nums2 = {2, 3, 0, 1, 4};
        System.out.println(getMinJump(nums2));
        // output: 2
        int[] nums3 = {2, 2, 0, 1, 4};
        System.out.println(getMinJump(nums3));
        // output: 3
    }
}
