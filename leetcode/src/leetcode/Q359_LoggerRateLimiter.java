package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Q359. Logger Rate Limiter.
 * HASHMAP SOLUTION
 * HashMap<String, Integer>() // <message, timestamp>
 * if hashmap doesn't contain a key (message), put and return true
 * if the message was +10 seconds ago, put and return true
 * else, return false
 * 
 * DEQUE AND SET SOLUTION
 * save the data into deque<timestamp, message> and set<message>
 * make two data structure synchronous
 * when a message arrive, remove +10 seconds ago messages from deque and set
 * if set doesn't contain a message, add and return true
 * else, return false
 * @author jeesublee
 *
 */
public class Q359_LoggerRateLimiter {

	class Logger {
		Map<String, Integer> cache;
		private final int TERM = 10;

		public Logger() {
			cache = new HashMap<>();
		}

		public boolean shouldPrintMessage(int timestamp, String message) {
			if (!cache.containsKey(message)) {
				cache.put(message, timestamp);
				return true;
			}
			if (timestamp - cache.get(message) >= TERM) {
				cache.put(message, timestamp);
				return true;
			}
			return false;
		}
	}

	// DEQUE AND SET SOLUTION
	class Logger2 {
		private final int TERM = 10;
		Deque<Pair> cacheQueue;
		Set<String> cacheSet;

		public Logger2() {
			cacheQueue = new ArrayDeque<>();
			cacheSet = new HashSet<>();
		}

		private class Pair {
			String message;
			int timestamp;

			public Pair(String message, int timestamp) {
				this.message = message;
				this.timestamp = timestamp;
			}
		}

		public boolean shouldPrintMessage(int timestamp, String message) {
			while (!cacheQueue.isEmpty() && cacheQueue.peekFirst().timestamp <= timestamp - TERM) {
				Pair removedPair = cacheQueue.removeFirst();
				cacheSet.remove(removedPair.message);
			}
			if (cacheSet.contains(message)) {
				return false;
			}
			cacheQueue.addLast(new Pair(message, timestamp));
			cacheSet.add(message);
			return true;
		}
	}
}
