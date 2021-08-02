package leetcode.Windows;
/**
 * Q134. Gas Station.
 * [Two Pointers]
 * i and j start at 0.
 * j goes as further as can.
 * If total traveled distance == total length, we traveled around. Return i.
 * If we cannot go further, i++.
 * If i comes back to the start point,
 * it is not able to travel around. Return -1.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q134_GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] netGas = buildNetGas(gas, cost);
        int distance = 0;
        int tank = 0;
        int j = 0;
        for (int i = 0; i < netGas.length; i++) {
            while (tank >= 0) {
                if (distance == netGas.length) {
                    return i;
                }
                tank += netGas[j];
                j = (j + 1) % netGas.length;
                distance++;
            }

            tank -= netGas[i];
            distance--;
        }

        return -1;
    }

    private static int[] buildNetGas(int[] gas, int[] cost) {
        int[] netGas = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            netGas[i] = gas[i] - cost[i];
        }
        return netGas;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit(gas, cost));
        // output: 3
    }

}
