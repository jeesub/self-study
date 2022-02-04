package List;

/**
 * Drone Flight Planner.
 * [List]
 * max(route[i][2]) - route[0][2]
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class DroneFlightPlanner {
    static int calcDroneMinEnergy(int[][] route) {
        int max = route[0][2];
        for (int[] each : route) {
            max = Math.max(max, each[2]);
        }
        return max - route[0][2];
    }

    public static void main(String[] args) {
        int[][] route = {{0, 2, 10}, {3, 5, 0}, {10, 20, 6}, {10, 15, 15}, {5, 10, 7}};
        System.out.println(calcDroneMinEnergy(route));
        // output: 5
    }
}
