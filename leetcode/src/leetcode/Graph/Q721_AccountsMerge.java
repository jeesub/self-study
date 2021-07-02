package leetcode.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Q721. Accounts Merge.
 * [Union Find]
 * Each email as a vertex. Make a disjoint set.
 * Make a getParent method and use it to build the result list.
 * TC: O(n), where n is the number of accounts
 * SC: O(n), where n is the number of accounts
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q721_AccountsMerge {

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> mailNameMap = buildMailNameMap(accounts);
        List<String> mailList = buildMailList(accounts);
        Map<String, Integer> mailIndexMap = buildMailIndexMap(mailList);

        DisjointSet disjointSet = new DisjointSet(mailList.size());

        for (List<String> account : accounts) {
            int mailOne = mailIndexMap.get(account.get(1));
            for (int i = 2; i < account.size(); i++) {
                disjointSet.union(mailOne, mailIndexMap.get(account.get(i)));
            }
        }

        Map<Integer, List<String>> resultMap = new HashMap<>();
        for (int i = 0; i < mailList.size(); i++) {
            int parent = disjointSet.getParent(i);
            if (!resultMap.containsKey(parent)) {
                resultMap.put(parent, new ArrayList<>());
            }
            List<String> list = resultMap.get(parent);
            String email = mailList.get(i);
            list.add(email);
        }

        List<List<String>> result = new ArrayList<>(resultMap.values());
        for (List<String> list : result) {
            Collections.sort(list);
            list.add(0, mailNameMap.get(list.get(0)));
        }
        return result;
    }

    private static Map<String, String> buildMailNameMap(List<List<String>> accounts) {
        Map<String, String> mailNameMap = new HashMap<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                mailNameMap.put(account.get(i), name);
            }
        }
        return mailNameMap;
    }

    private static List<String> buildMailList(List<List<String>> accounts) {
        Set<String> set = new HashSet<>();
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                set.add(account.get(i));
            }
        }
        return new ArrayList<>(set);
    }

    private static Map<String, Integer> buildMailIndexMap(List<String> mailList) {
        Map<String, Integer> mailIndexMap = new HashMap<>();
        for (int i = 0; i < mailList.size(); i++) {
            mailIndexMap.put(mailList.get(i), i);
        }
        return mailIndexMap;
    }

    private static class DisjointSet {
        private int[] parent;
        private int[] rank;

        private DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        private void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX == parentY) {
                return;
            }

            if (rank[parentX] < rank[parentY]) {
                parent[parentY] = parentX;
            } else {
                parent[parentX] = parentY;
            }

            if (rank[parentX] == rank[parentY]) {
                rank[parentX]++;
            }
        }

        private int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        private int getParent(int x) {
            find(x);
            return parent[x];
        }
    }

    public static void main(String[] args) {
        List<List<String>> accounts = List.of(
                List.of("Jeesub", "jeesubl@a.com", "jeesubl@b.com", "jeesubl@c.com"),
                List.of("Sheldon", "sheldon@b.com", "sheldon@c.com", "sheldon@a.com"),
                List.of("Jeesub", "jeesubl@d.com", "jeesubl@a")
                );
        System.out.println(accountsMerge(accounts));
        // output: [[Jeesub, jeesubl@a.com, jeesubl@b.com, jeesubl@c.com], [Sheldon, sheldon@a.com, sheldon@b.com, sheldon@c.com], [Jeesub, jeesubl@a, jeesubl@d.com]]
    }

}
