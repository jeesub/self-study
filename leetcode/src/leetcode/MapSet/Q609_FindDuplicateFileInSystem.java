package leetcode.MapSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q609. Find Duplicate File in System.
 * [HashMap]
 * Build a Map<content, a list of files>.
 * If the size of list >= 2, it's duplicate.
 * TC: O(n), where n is the number of files
 * SC: O(n), where n is the number of files
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q609_FindDuplicateFileInSystem {

    public static List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] item = path.split(" ");
            for (int i = 1; i < item.length; i++) {
                String[] file = item[i].split("\\(");
                String content = file[1].substring(0, file[1].length() - 1);
                if (!map.containsKey(content)) {
                    map.put(content, new ArrayList<>());
                }
                StringBuilder fullFile = new StringBuilder();
                fullFile.append(item[0]).append("/").append(file[0]);
                map.get(content).add(fullFile.toString());
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : map.values()) {
            if (list.size() > 1) {
                result.add(list);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"};
        System.out.println(findDuplicate(paths));
        // output: [[root/a/2.txt, root/c/d/4.txt], [root/a/1.txt, root/c/3.txt]]
    }

}
