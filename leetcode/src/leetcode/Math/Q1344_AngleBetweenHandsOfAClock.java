package leetcode.Math;

/**
 * 1344. Angle Between Hands of a Clock.
 * [Math]
 * <calculate hour angle>
 * hour %= 12
 * hour angle = 30 * hour + minutes / 2
 * <calculate minutes angle>
 * minutes angle = 6 * minutes
 *
 * candidate angle 1 = abs(hour angle - minutes angle)
 * candidate angle 2 = 360 - angle 1
 * TC: O(1)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1344_AngleBetweenHandsOfAClock {
    public static double angleClock(int hour, int minutes) {
        double hourAngle = 30d * (hour % 12) + (double) minutes / 2;
        double minutesAngle = 6d * minutes;
        double angle = Math.abs(hourAngle - minutesAngle);
        angle = Math.min(angle, 360 - angle);
        return angle;
    }
}
