package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Q211. Design Add and Search Words Data Structure.
 * [Trie]
 * Use trie to store the data.
 * Search using DFS.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q211_DesignAddAndSearchWordsDataStructure {

    public static final class WordDictionary {
        Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new Node());
                }
                curr = curr.children.get(c);
            }
            curr.isWord = true;
        }

        public boolean search(String word) {
            return dfs(root, word, 0);
        }

        private boolean dfs(Node curr, String word, int pointer) {
            // base case
            char c = word.charAt(pointer);
            if (pointer == word.length() - 1) {
                if (c != '.') {
                    if (curr.children.containsKey(c) && curr.children.get(c).isWord) {
                        return true;
                    }
                    return false;
                }
                for (Node each : curr.children.values()) {
                    if (each.isWord) {
                        return true;
                    }
                }
                return false;
            }

            // curr char is not '.'
            if (c != '.') {
                if (!curr.children.containsKey(c)) {
                    return false;
                }
                curr = curr.children.get(c);
                return dfs(curr, word, pointer + 1);
            }

            // curr char is '.'
            for (Node each : curr.children.values()) {
                if (dfs(each, word, pointer + 1)) {
                    return true;
                }
            }

            return false;
        }

        private static class Node {
            private boolean isWord;
            private Map<Character, Node> children;

            private Node() {
                isWord = false;
                children = new HashMap<>();
            }
        }
    }

    /* Array Version
    public static final class WordDictionary {
        private static int ALPHABET_LENGTH = 26;
        Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                int index = (int) (word.charAt(i) - 'a');
                if (curr.children[index] == null) {
                    curr.children[index] = new Node();
                }
                curr = curr.children[index];
            }
            curr.isEnd = true;
        }

        public boolean search(String word) {
            return dfs(root, word, 0);
        }

        private boolean dfs(Node curr, String word, int pointer) {
            // base case
            int index = (int) (word.charAt(pointer) - 'a');
            if (pointer == word.length() - 1) {
                if (word.charAt(pointer) != '.') {
                    if (curr.children[index] != null && curr.children[index].isEnd) {
                        return true;
                    }
                    return false;
                }
                for (int i = 0; i < ALPHABET_LENGTH; i++) {
                    if (curr.children[i] != null && curr.children[i].isEnd) {
                        return true;
                    }
                }
                return false;
            }

            // curr char is not '.'
            if (word.charAt(pointer) != '.') {
                if (curr.children[index] == null) {
                    return false;
                }
                curr = curr.children[index];
                return dfs(curr, word, pointer + 1);
            }

            // curr char is '.'
            for (int i = 0; i < ALPHABET_LENGTH; i++) {
                if (curr.children[i] == null) {
                    continue;
                }
                if (dfs(curr.children[i], word, pointer + 1)) {
                    return true;
                }
            }

            return false;
        }

        private final class Node {
            private boolean isEnd;
            private Node[] children;

            private Node() {
                isEnd = false;
                children = new Node[ALPHABET_LENGTH];
            }
        }
    }
    */

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("dog");
        wd.addWord("fog");
        wd.addWord("doggy");
        wd.addWord("doggo");
        wd.addWord("foggy");
        System.out.println(wd.search("dog")); // output: true
        System.out.println(wd.search("doggy")); // output: true
        System.out.println(wd.search("fogg")); // output: false
    }

}
