package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q588. Design In-Memory File System.
 * FileSystem should have a Directory instance.
 * Directory class has a Map<String (dir name), Directory (sub directory)> as a variable.
 * Directory class has a Map<String (file name), String (content)> as a variable.
 *
 * [ls]
 * If it is a file path, return it's file name.
 * If it is a directory path, return it's directories and files as a list after sorting.
 *
 * [mkdir]
 * Using while loop, make directories.
 *
 * [addContentToFile]
 * Using while loop, make directories if necessary.
 * Record a file in the Map<File, Content>
 *
 * [readContentFromFile]
 * Find the map and return the content.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q588_DesignInMemoryFileSystem {

    public static class FileSystem {
        private Directory rootDir;

        public FileSystem() {
            rootDir = new Directory("root");
        }

        public List<String> ls(String path) {
            String[] eachPath = path.split("/");
            if (eachPath.length == 0) {
                return rootDir.getDirectoriesAndFiles();
            }
            String targetName = eachPath[eachPath.length - 1];
            Directory currDir = rootDir;
            for (int i = 1; i < eachPath.length - 1; i++) {
                currDir = currDir.getDirectory(eachPath[i]);
            }
            if (currDir.hasDirectory(targetName)) {
                return currDir.getDirectory(targetName).getDirectoriesAndFiles();
            }
            if (currDir.hasFile(targetName)) {
                return List.of(targetName);
            }
            throw new IllegalArgumentException("Wrong path");
        }

        public void mkdir(String path) {
            String[] eachPath = path.split("/");
            Directory currDir = rootDir;
            for (int i = 1; i < eachPath.length; i++) {
                if (!currDir.hasDirectory(eachPath[i])) {
                    currDir.addDirectory(eachPath[i]);
                }
                currDir = currDir.getDirectory(eachPath[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            String[] eachPath = filePath.split("/");
            String targetName = eachPath[eachPath.length - 1];
            Directory currDir = rootDir;
            for (int i = 1; i < eachPath.length - 1; i++) {
                if (!currDir.hasDirectory(eachPath[i])) {
                    currDir.addDirectory(eachPath[i]);
                }
                currDir = currDir.getDirectory(eachPath[i]);
            }
            currDir.addFile(targetName, content);
        }

        public String readContentFromFile(String filePath) {
            String[] eachPath = filePath.split("/");
            String targetName = eachPath[eachPath.length - 1];
            Directory currDir = rootDir;
            for (int i = 1; i < eachPath.length - 1; i++) {
                currDir = currDir.directories.get(eachPath[i]);
            }
            return currDir.readFile(targetName);
        }

        private final class Directory {
            private Map<String, Directory> directories;
            private Map<String, String> files;

            private Directory(String newName) {
                directories = new HashMap<String, Directory>();
                files = new HashMap<String, String>();
            }

            private void addDirectory(String newName) {
                directories.put(newName, new Directory(newName));
            }

            private boolean hasDirectory(String name) {
                return directories.containsKey(name);
            }

            private Directory getDirectory(String name) {
                return directories.get(name);
            }

            private List<String> getDirectoriesAndFiles() {
                List<String> result = new ArrayList<>();
                for (String each : directories.keySet()) {
                    result.add(each);
                }
                for (String each : files.keySet()) {
                    result.add(each);
                }
                Collections.sort(result);
                return result;
            }

            private void addFile(String newName, String newContent) {
                newContent = files.getOrDefault(newName, new String()) + newContent;
                files.put(newName, newContent);
            }

            private boolean hasFile(String name) {
                return files.containsKey(name);
            }

            private String readFile(String name) {
                return files.get(name);
            }
        }
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.ls("/"));
        // output: []
        fs.mkdir("/a/b/c");
        System.out.println(fs.ls("/"));
        // output: [a]
        fs.addContentToFile("/a/b/c/d", "Hello, world");
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
        // output: Hello, world
        fs.mkdir("/a/b/c/e");
        System.out.println(fs.ls("/a/b/c"));
        // output: [d, e]
    }

}
