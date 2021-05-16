package leetcode.MapSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q811. Subdomain Visit Count.
 * [Map]
 * Build a map<domain, count>, and construct the result.
 * TC: O(n), where n is the length of cpdomains.
 * SC: O(n), where n is the length of cpdomains.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q811_SubdomainVisitCount {

    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String each : cpdomains) {
            String[] pair = each.split(" ");
            int count = Integer.valueOf(pair[0]);
            String domain = pair[1];
            int currCount = map.getOrDefault(domain, 0) + count;
            map.put(domain, currCount);
            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) != '.') {
                    continue;
                }
                String currDomain = domain.substring(i + 1, domain.length());
                currCount = map.getOrDefault(currDomain, 0) + count;
                map.put(currDomain, currCount);
            }
        }

        List<String> result = new ArrayList<>();
        for (String key : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(map.get(key).toString());
            sb.append(" ");
            sb.append(key);
            result.add(sb.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        String[] cpdomains = {"300 jeesub.com", "100 etc.cmu.edu"};
        System.out.println(subdomainVisits(cpdomains));
        // output: [300 com, 100 cmu.edu, 100 etc.cmu.edu, 300 jeesub.com, 100 edu]
    }

}
