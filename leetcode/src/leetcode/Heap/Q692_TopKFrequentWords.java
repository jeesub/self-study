package leetcode.Heap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Q692. Top K Frequent Words.
 * [Map & Heap]
 * Build a map<word, frequency>
 * Build a min heap and keep the size k.
 * Build a list from the min heap in reverse order.
 * Return the list of words.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q692_TopKFrequentWords {

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = buildMap(words);
        PriorityQueue<WordAndFrequency> pq = buildPQ(map, k);
        List<String> result = buildResult(pq, k);
        return result;
    }

    private static Map<String, Integer> buildMap(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }

    private static PriorityQueue<WordAndFrequency> buildPQ(Map<String, Integer> map, int k) {
        PriorityQueue<WordAndFrequency> pq = new PriorityQueue<>();
        for (String key : map.keySet()) {
            WordAndFrequency waf = new WordAndFrequency(key, map.get(key));
            pq.offer(waf);

            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq;
    }

    private static List<String> buildResult(PriorityQueue<WordAndFrequency> pq, int k) {
        String[] array = new String[k];
        while (!pq.isEmpty()) {
            array[--k] = pq.poll().word;
        }
        return List.of(array);
    }

    private static class WordAndFrequency implements Comparable<WordAndFrequency> {
        String word;
        int frequency;

        private WordAndFrequency(String newWord, int newFrequency) {
            word = newWord;
            frequency = newFrequency;
        }

        @Override
        public int compareTo(WordAndFrequency other) {
            if (this.frequency != other.frequency) {
                return Integer.compare(this.frequency, other.frequency);
            }
            return other.word.compareTo(this.word);
        }
    }

    public static void main(String[] args) {
        String[] words = {"pen", "pineapple", "apple", "pen"};
        int k = 2;
        System.out.println(topKFrequent(words, k));
        // output: [pen, apple]
    }

}
