package leetcode.Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q642. Design Search Autocomplete System.
 * [Trie]
 * Each trie layer has an array(a-z and space) and list of 3 hot sentences.
 * Map<String, Integer> stores every input sentence and times.
 *
 * Implementation
 * Trie class has Trie array and a list of nodes.
 * First, make nodes and build tries.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q642_DesignSearchAutocompleteSystem {

    private static class AutocompleteSystem {
        private static final char SPACE = ' ';
        private static final char POUND = '#';

        private Map<String, Integer> map;
        private Trie root;
        private Trie currTrie;
        private StringBuilder currInput;

        public AutocompleteSystem(String[] sentences, int[] times) {
            map = new HashMap<>();
            root = new Trie();
            currTrie = root;
            currInput = new StringBuilder();

            for (int i = 0; i < sentences.length; i++) {
                map.put(sentences[i], times[i]);
                add(sentences[i]);
            }
        }

        private void add(String sentence) {
            char first = sentence.charAt(0);
            int index = getIndex(first);
            if (index == -1) {
                throw new RuntimeException("Wrong input sentence");
            }
            if (root.array[index] == null) {
                root.array[index] = new Trie();
            }
            add(sentence, 0, root.array[index]);
        }

        private void add(String sentence, int pointer, Trie trie) {
            if (pointer >= sentence.length()) {
                return;
            }

            if (trie.list == null) {
                trie.list = new ArrayList<>();
            }
            trie.list.remove(sentence);
            trie.list.add(sentence);
            Collections.sort(trie.list, new SentenceComparator());
            while (trie.list.size() > 3) {
                trie.list.remove(trie.list.size() - 1);
            }

            if (pointer + 1 >= sentence.length()) {
                return;
            }

            int index = getIndex(sentence.charAt(pointer + 1));
            if (index == -1) {
                return;
            }
            if (trie.array[index] == null) {
                trie.array[index] = new Trie();
            }
            add(sentence, pointer + 1, trie.array[index]);
        }

        private class SentenceComparator implements Comparator<String> {
            @Override
            public int compare(String a, String b) {
                int aTimes = map.getOrDefault(a, 0);
                int bTimes = map.getOrDefault(b, 0);
                if (aTimes == bTimes) {
                    return a.compareTo(b);
                }
                return Integer.compare(bTimes, aTimes);
            }
        }

        public List<String> input(char c) {
            if (c == POUND) {
                String newSentence = currInput.toString();

                map.put(newSentence, map.getOrDefault(newSentence, 0) + 1);
                add(newSentence);

                currTrie = root;
                currInput.setLength(0);

                return new ArrayList<>();
            }

            if (currTrie != null) {
                int index = getIndex(c);
                currTrie = currTrie.array[index];
            }
            currInput.append(c);

            if (currTrie == null || currTrie.list == null) {
                return new ArrayList<>();
            }

            return new ArrayList<>(currTrie.list);
        }

        private static int getIndex(char c) {
            if (c == SPACE) {
                return 26;
            }
            if (c - 'a' >= 26 || c - 'a' < 0) {
                return -1;
            }
            return c - 'a';
        }

        private class Trie {
            Trie[] array;
            List<String> list;

            private Trie() {
                array = new Trie[27];
                list = null;
            }
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "i am korean", "iroman", "i love java"};
        int[] times = {5, 3, 2, 2};
        AutocompleteSystem acs = new AutocompleteSystem(sentences, times);
        System.out.println(acs.input('i'));
        System.out.println(acs.input(' '));
        System.out.println(acs.input('l'));
        System.out.println(acs.input('o'));
        System.out.println(acs.input('v'));
        System.out.println(acs.input('e'));
        System.out.println(acs.input(' '));
        System.out.println(acs.input('a'));
        System.out.println(acs.input('l'));
        System.out.println(acs.input('l'));
        System.out.println(acs.input('#'));
        System.out.println(acs.input('i'));
        System.out.println(acs.input(' '));
        System.out.println(acs.input('l'));
        System.out.println(acs.input('#'));
        // output:
        // [i love you, i am korean, i love java]
        // [i love you, i am korean, i love java]
        // [i love you, i love java]
        // [i love you, i love java]
        // [i love you, i love java]
        // [i love you, i love java]
        // [i love you, i love java]
        // []
        // []
        // []
        // []
        // [i love you, i am korean, i love java]
        // [i love you, i am korean, i love java]
        // [i love you, i love java, i love all]
        // []
    }

}
