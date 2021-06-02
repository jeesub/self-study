package leetcode.MapSet;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Q706. Design HashMap.
 * [HashMap]
 * Separate chaining for handling collisions.
 * Use array to store Object(TreeMap<key, value>).
 * Leetcode solution doesn't include the validateCapacity method.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q706_DesignHashMap {

    private static class MyHashMap {
        private static final int DEFAULT_CAPACITY = 11;

        private Object[] array;
        private int capacity;
        private int size;

        public MyHashMap() {
            array = new Object[DEFAULT_CAPACITY];
            Arrays.fill(array, new TreeMap<Integer, Integer>());
            capacity = DEFAULT_CAPACITY;
            size = 0;
        }

        public void put(int key, int value) {
            validateCapacity();
            @SuppressWarnings("unchecked")
            TreeMap<Integer, Integer> treeMap = (TreeMap<Integer, Integer>) array[key % capacity];
            int prevSize = treeMap.size();
            treeMap.put(key, value);
            if (prevSize != treeMap.size()) {
                size++;
            }
        }

        public int get(int key) {
            @SuppressWarnings("unchecked")
            TreeMap<Integer, Integer> treeMap = (TreeMap<Integer, Integer>) array[key % capacity];
            Integer result = treeMap.get(key);
            return result == null ? -1 : result;
        }

        public void remove(int key) {
            @SuppressWarnings("unchecked")
            TreeMap<Integer, Integer> treeMap = (TreeMap<Integer, Integer>) array[key % capacity];
            int prevSize = treeMap.size();
            treeMap.remove(key);
            if (prevSize != treeMap.size()) {
                size--;
            }
        }

        private void validateCapacity() {
            if (size >= capacity) {
                rehash();
            }
        }

        private void rehash() {
            int newCapacity = getNextCapacity(capacity);
            Object[] newArray = new Object[newCapacity];
            Arrays.fill(newArray, new TreeMap<Integer, Integer>());
            for (Object object : array) {
                @SuppressWarnings("unchecked")
                TreeMap<Integer, Integer> treeMap = (TreeMap<Integer, Integer>) object;
                for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
                    int newIndex = entry.getKey() % newCapacity;
                    @SuppressWarnings("unchecked")
                    TreeMap<Integer, Integer> newTreeMap = (TreeMap<Integer, Integer>) newArray[newIndex];
                    newTreeMap.put(entry.getKey(), entry.getValue());
                }
            }
            array = newArray;
            capacity = newCapacity;
        }

        private static int getNextCapacity(int capacity) {
            int newCapacity = 2 * capacity;
            while (!isPrimeNumber(++newCapacity))
                ;
            return newCapacity;
        }

        private static boolean isPrimeNumber(int num) {
            if (num == 2) {
                return true;
            }
            int boundary = (int) Math.sqrt((double) num);
            for (int i = 2; i <= boundary; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        MyHashMap hm = new MyHashMap();
        for (int i = 0; i < 100; i++) {
            hm.put(i, i);
        }
        for (int i = 0; i < 50; i++) {
            hm.remove(2 * i);
        }
        System.out.println(hm.get(49));
        // output: 49
        System.out.println(hm.get(50));
        // output: -1
        System.out.println(hm.get(100));
        // output: -1
    }

}
