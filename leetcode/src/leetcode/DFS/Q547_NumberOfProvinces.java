package leetcode.DFS;
/**
 * Q547. Number of Provinces.
 * [DFS]
 * boolean[] visited; record the visited cities.
 * Iterate through the cities.
 *   If curr is not visited, dfs. count++.
 * TC: O(n^2), where n is the number of cities
 * SC: O(n), where n is the number of cities
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q547_NumberOfProvinces {

    public static int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int numOfProvinces = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (visited[i]) {
                continue;
            }
            dfs(isConnected, visited, i);
            numOfProvinces++;
        }
        return numOfProvinces;
    }

    private static void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (i == city) {
                continue;
            }
            if (isConnected[city][i] == 1 && !visited[i]) {
                dfs(isConnected, visited, i);
            }
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected));
        // output: 2
    }

}
