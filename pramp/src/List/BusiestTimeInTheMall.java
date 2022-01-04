package List;

/**
 * Busiest Time in The Mall.
 * Iterate through the data while keeping accumulated sum and max.
 * If curr is not the last record of the timestamp, continue.
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class BusiestTimeInTheMall {

    static int findBusiestPeriod(int[][] data) {
        int timestamp = data[0][0];
        int max = 0;
        int acc = 0;

        for (int i = 0; i < data.length; i++) {
            if (data[i][2] == 1) {
                acc += data[i][1];
            } else {
                acc -= data[i][1];
            }

            if (i < data.length - 1 && data[i][0] == data[i + 1][0]) {
                continue;
            }

            if (acc > max) {
                max = acc;
                timestamp = data[i][0];
            }
        }

        return timestamp;
    }

    public static void main(String[] args) {
        int[][] data = { {1487799425, 14, 1},
                {1487799425, 4,  0},
                {1487799425, 2,  0},
                {1487800378, 10, 1},
                {1487801478, 18, 0},
                {1487801478, 18, 1},
                {1487901013, 1,  0},
                {1487901211, 7,  1},
                {1487901211, 7,  0} };
        System.out.println(findBusiestPeriod(data));
        // output: 1487800378
    }
}
