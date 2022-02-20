package leetcode.Trie;

/**
 * 208. Implement Trie (Prefix Tree).
 * [Trie]
 * Trie[26] children: contains next characters
 * boolean isWord: current character is the last character of a word?
 *
 * insert():
 * if the current character is the end, mark it and finish.
 * if we don't have character child, make new one.
 * move on to the child.
 * TC: O(n)
 * SC: O(n)
 *
 * search():
 * if the current character is the last one, return isWord.
 * if we don't have character child, return false.
 * return the result from the child.
 * TC: O(n)
 * SC: O(n)
 *
 * startWith:
 * same as search(), except for returning true if the curr char is the last one.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q208_ImplementTriePrefixTree {
    class Trie {
        private Trie[] children;
        private boolean isWord;

        public Trie() {
            children = new Trie[26];
            isWord = false;
        }

        public void insert(String word) {
            if (word == null || word.length() == 0) {
                throw new IllegalArgumentException("word cannot be null or empty.");
            }
            insert(word, 0);
        }

        private void insert(String word, int index) {
            if (index >= word.length()) {
                isWord = true;
                return;
            }

            if (children[word.charAt(index) - 'a'] == null) {
                children[word.charAt(index) - 'a'] = new Trie();
            }
            children[word.charAt(index) - 'a'].insert(word, index + 1);
        }

        public boolean search(String word) {
            if (word == null || word.length() == 0) {
                throw new IllegalArgumentException("word cannot be null or empty.");
            }
            return search(word, 0);
        }

        private boolean search(String word, int index) {
            if (index == word.length()) {
                return isWord;
            }

            if (children[word.charAt(index) - 'a'] == null) {
                return false;
            }

            return children[word.charAt(index) - 'a'].search(word, index + 1);
        }

        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.length() == 0) {
                throw new IllegalArgumentException("prefix cannot be null or empty.");
            }

            return startsWith(prefix, 0);
        }

        private boolean startsWith(String prefix, int index) {
            if (index >= prefix.length()) {
                return true;
            }

            if (children[prefix.charAt(index) - 'a'] == null) {
                return false;
            }

            return children[prefix.charAt(index) - 'a'].startsWith(prefix, index + 1);
        }
    }
}
