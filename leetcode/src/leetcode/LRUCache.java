package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * HashMap stores data of key and LinkedList Node
 * Doublely LinkedList stores data of key and value
 * LinkedList has dummy head and dummy tail
 * 
 * GET
 * if we don't have a key, return -1
 * else, move the node to head
 * and return the value
 * 
 * PUT
 * if we have a key, 
 * find the node and update it
 * move the node to the start of the LinkedList
 * else,
 * make a new node and put it in the start of the LinkedList
 *
 * OVER CAPACITY
 * if the size of LinkedList is higher than capacity,
 * remove the last element of the LinkedList
 * remove it from the HashMap
 * 
 * @author jeesublee
 *
 */
public class LRUCache {
	private int capacity;
	private Map<Integer, DNode> cache;
	private DNode head;
	private DNode tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<Integer, DNode>();
		this.head = new DNode(-1, -1);
		this.tail = new DNode(-1, -1);
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		if (!cache.containsKey(key)) {
			return -1;
		}
		DNode node = cache.get(key);
		removeNode(node);
		addToHead(node);
		return node.value;
	}

	public void put(int key, int value) {
		DNode node;
		if (!cache.containsKey(key)) {
			node = new DNode(key, value);
			cache.put(key, node);
		} else {
			node = cache.get(key);
			node.value = value;
			removeNode(node);
		}
		addToHead(node);

		if (cache.size() > capacity) {
			node = tail.prev;
			cache.remove(node.key);
			removeNode(node);
		}
		return;
	}

	private class DNode {
		int key;
		int value;
		DNode prev;
		DNode next;

		public DNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private void addToHead(DNode node) {
		node.next = head.next;
		node.prev = head;
		head.next.prev = node;
		head.next = node;
		return;
	}

	private void removeNode(DNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		return;
	}
}
