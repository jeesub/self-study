package leetcode.MapSet;

/**
 * 825. Friends Of Appropriate Ages.
 * [HashMap]
 * Build a count array, index = age, value = frequency.
 * From 1 to 120, get the valid number of requests at age i & count += valid num * freq[i]
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q825_FriendsOfAppropriateAges {
    public static int numFriendRequests(int[] ages) {
        int[] freq = new int[121];
        for (int age : ages) {
            freq[age]++;
        }

        int count = 0;
        for (int i = 1; i < freq.length; i++) {
            int currCount = 0;
            for (int j = 1; j <= i; j++) {
                if (freq[j] == 0 || !isValid(i, j)) {
                    continue;
                }

                if (i != j) {
                    currCount += freq[j];
                } else {
                    currCount += freq[j] - 1;
                }
            }
            count += currCount * freq[i];
        }
        return count;
    }

    private static boolean isValid(int ageX, int ageY) {
        if (2 * ageY <= ageX + 14) {
            return false;
        }
        if (ageY > 100 && ageX < 100) {
            return false;
        }
        return true;
    }
}
