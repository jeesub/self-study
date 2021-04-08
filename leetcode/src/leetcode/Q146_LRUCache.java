package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Q146. LRU Cache.
 * 
 * 1. HashMap and Doubly Linked List HashMap stores data of key and LinkedList
 * Node Doublely LinkedList stores data of key and value LinkedList has dummy
 * head and dummy tail
 * 
 * [GET] if we don't have a key, return -1 else, move the node to head and
 * return the value
 * 
 * [PUT] if we have a key, find the node and update it move the node to the
 * start of the LinkedList else, make a new node and put it in the start of the
 * LinkedList
 *
 * [OVER CAPACITY] if the size of LinkedList is higher than capacity, remove the
 * last element of the LinkedList remove it from the HashMap
 * 
 * 2. LinkedHashMap Extends LinkedHashMap. Override removeEldestEntry method.
 * 
 * @author jeesublee
 */
public class Q146_LRUCache {

    private static class LRUCache {
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

    @SuppressWarnings("serial")
    private static class LinkedHashMapLRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LinkedHashMapLRUCache(int newCapacity) {
            super(newCapacity, 0.75F, true);
            capacity = newCapacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return super.size() > capacity;
        }
    }

    public static void main(String[] args) {
        LRUCache c1 = new LRUCache(2);
        c1.put(1, 1);
        c1.put(2, 2);
        c1.get(1);
        c1.put(3, 3);
        System.out.println(c1.get(2) == -1);
        // output: true
        LinkedHashMapLRUCache c2 = new LinkedHashMapLRUCache(2);
        c2.put(1, 1);
        c2.put(2, 2);
        c2.get(1);
        c2.put(3, 3);
        System.out.println(c2.get(2) == -1);
        // output: true
    }
}
