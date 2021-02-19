package leetcode;
/**
 * Q1041. Robot Bounded In Circle.
 * The robot has to come back to the start point.
 * After four times of instructions, the robot should be at the start point.
 * Or, the robot will move away from the start point as instructions go.
 * In order to come back to the start point,
 * 1. the robot has to be at the start point
 * 2. or the robot has to face non-top directions
 *    if so, the robot will come back to the start point after four instructions
 * 
 *             0    1      2       3
 * direction = top, right, bottom, left
 * clockwise rotation: (curr direction + 1) % 4
 * anticlockwise rotation: (curr direction + 3) % 4
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1041_RobotBoundedInCircle {
    public static boolean isRobotBounded(String instructions) {
        char[] path = instructions.toCharArray();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direction = 0;
        int[] curr = {0, 0};
        for (char c : path) {
            if (c == 'G') {
                curr[0] += directions[direction][0];
                curr[1] += directions[direction][1];
            } else if (c == 'L') {
                direction = (direction + 1) % 4;
            } else if (c == 'R') {
                direction = (direction + 3) % 4;
            }
        }
        return (curr[0] == 0 && curr[1] == 0) || direction != 0;
    }

    public static void main(String[] args) {
        String instructions = "GGLRR";
        System.out.println(isRobotBounded(instructions));
        // output: true
    }
}
