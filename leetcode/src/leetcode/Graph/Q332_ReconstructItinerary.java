package leetcode.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Q332. Reconstruct Itinerary.
 * [Eulerian Path]
 * Map<from, MinHeap<to> lexicographically ordered>
 * DFS in lexical order.
 * If it's stuck, the current stucked path should be placed in the behind.
 * If it's not stuck, it's in a good place.
 * -> DFS and put the current city in the front of the result list.
 *    Fill the list in reversed order.
 * TC: O(e * log(k)), where e is the number of tickets and k is the number of destination from an airport
 * SC: O(v + e), where v is the number of airports and e is the number of tickets
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q332_ReconstructItinerary {

    private static final String START = "JFK";

    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = buildMap(tickets);
        LinkedList<String> result = new LinkedList<>();
        dfs(map, result, START);
        return result;
    }

    private static void dfs(Map<String, PriorityQueue<String>> map, LinkedList<String> result, String curr) {
        if (map.containsKey(curr)) {
            PriorityQueue<String> minHeap = map.get(curr);
            while (!minHeap.isEmpty()) {
                dfs(map, result, minHeap.poll());
            }
        }
        result.addFirst(curr);
    }

    private static Map<String, PriorityQueue<String>> buildMap(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            if (!map.containsKey(ticket.get(0))) {
                map.put(ticket.get(0), new PriorityQueue<>());
            }
            PriorityQueue<String> minHeap = map.get(ticket.get(0));
            minHeap.add(ticket.get(1));
        }
        return map;
    }

    public static void main(String[] args) {
        List<List<String>> tickets = List.of(List.of("JFK","SFO"),List.of("JFK","ATL"),List.of("SFO","ATL"),List.of("ATL","JFK"),List.of("ATL","SFO"));
        System.out.println(findItinerary(tickets));
        // output: [JFK, ATL, JFK, SFO, ATL, SFO]
    }

}
