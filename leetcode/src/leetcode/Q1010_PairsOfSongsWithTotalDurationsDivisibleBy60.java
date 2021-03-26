package leetcode;

/**
 * Q1010. Pairs of Songs With Total Durations Divisible by 60.
 * Iterate through the time array and count the pair.
 * If curr time has a pair in the array, count += number of pairs.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1010_PairsOfSongsWithTotalDurationsDivisibleBy60 {

    public static int numPairsDivisibleBy60(int[] times) {
        int[] timeArray = new int[60];
        int count = 0;
        for (int time : times) {
            time %= 60;
            int pairTime = (60 - (time % 60)) % 60;
            count += timeArray[pairTime];
            timeArray[time]++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] times = {10, 30, 20, 50, 120, 190, 320, 60};
        System.out.println(numPairsDivisibleBy60(times));
        // output: 3
    }

}
