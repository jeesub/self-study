package basics;
/**
 * Bisect left and right.
 * 
 * [Bisect Left]
 * Mid = left + (right - left) / 2.
 * Right pointer faintly goes down.
 * Left pointer actively goes up.
 * [1, 2, 2, 2, 2, 3], target = 2
 *  ^     *        ^
 *  ^  *  ^
 *  ^* ^
 *     ^^*
 * 
 * [Bisect Right]
 * Mid = left + (right - left + 1) / 2.
 * (Want mid to be right one if we have two options)
 * Left pointer faintly goes up.
 * Right pointer actively goes down.
 * [1, 2, 2, 2, 2, 3], target = 2
 *  ^        *     ^
 *           ^  *  ^
 *              ^ *^
 *              ^^*
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Bisect {

    public static int bisectLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    public static int bisectRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 2, 2, 3};
        System.out.print(bisectLeft(nums1, 2) == 1);
        System.out.print(" ");
        System.out.print(bisectRight(nums1, 2) == 4);
        System.out.print(" ");
        int[] nums2 = {1, 1, 2, 2, 2, 2, 3, 3};
        System.out.print(bisectLeft(nums2, 2) == 2);
        System.out.print(" ");
        System.out.print(bisectRight(nums2, 2) == 5);
        System.out.print(" ");
        int[] nums3 = {1, 1, 1, 2, 3, 3, 3};
        System.out.print(bisectLeft(nums3, 2) == 3);
        System.out.print(" ");
        System.out.print(bisectRight(nums3, 2) == 3);
        System.out.print(" ");
        int[] nums4 = {1, 1, 1, 1, 2, 3, 3, 3, 3};
        System.out.print(bisectLeft(nums4, 2) == 4);
        System.out.print(" ");
        System.out.print(bisectRight(nums4, 2) == 4);
        System.out.print(" ");
        int[] nums5 = {1, 2, 3};
        System.out.print(bisectLeft(nums5, 0) == -1);
        System.out.print(" ");
        System.out.print(bisectRight(nums5, 4) == -1);
        System.out.print(" ");
        System.out.print(bisectLeft(nums5, 4) == -1);
        System.out.print(" ");
        System.out.print(bisectRight(nums5, 0) == -1);
        // output: all true
    }
}
