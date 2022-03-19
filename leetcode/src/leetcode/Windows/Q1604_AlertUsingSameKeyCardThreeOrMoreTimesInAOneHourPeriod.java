package leetcode.Windows;

import java.util.*;

/**
 * 1604. Alert Using Same Key-Card Three or More Times in a One Hour Period.
 * [Windows]
 * 1. Build a HashMap<keyName, List<keyTime>>
 * 2. Sort the lists.
 * 3. Iterate through the list and check if the worker gets an alert.
 *    (list[i] + an hour >= list[i + 2])
 * 4. Sort the result list and return it.
 * TC: O(klogk), k = list.size()
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1604_AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<String>> map = initMap(keyName, keyTime);
        List<String> result = new ArrayList<>();
        for (String name : map.keySet()) {
            if (isGettingAlert(map.get(name))) {
                result.add(name);
            }
        }
        Collections.sort(result);
        return result;
    }

    private Map<String, List<String>> initMap(String[] keyName, String[] keyTime) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            if (!map.containsKey(keyName[i])) {
                map.put(keyName[i], new ArrayList<>());
            }
            map.get(keyName[i]).add(keyTime[i]);
        }
        return map;
    }

    private boolean isGettingAlert(List<String> list) {
        Collections.sort(list);
        for (int i = 0, j = 2; j < list.size(); i++, j++) {
            char[] arr = list.get(i).toCharArray();
            if (arr[1] != '9') {
                arr[1]++;
            } else {
                arr[0]++;
                arr[1] = '0';
            }
            String oneHourLater = new String(arr);
            if (oneHourLater.compareTo(list.get(j)) >= 0) {
                return true;
            }
        }
        return false;
    }
}
