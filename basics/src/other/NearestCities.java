package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Nearest Cities.
 * https://aonecode.com/amazon-online-assessment-nearest-cities
 * 
 * Make three maps.
 * 1. City name: Point
 * 2. X coordinate: City names
 * 3. Y coordinate: City names
 * 
 * Iterate through the queried points, find the nearest city.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class NearestCities {

    public static String[] findNearestCities(String[] points, int[] xCoordinates, int[] yCoordinates,
            String[] queriedPoints) {
        Map<String, Point> map = initMap(points, xCoordinates, yCoordinates);
        Map<Integer, List<String>> xMap = initCoordinatesMap(points, xCoordinates);
        Map<Integer, List<String>> yMap = initCoordinatesMap(points, yCoordinates);

        String[] result = new String[queriedPoints.length];
        for (int i = 0; i < queriedPoints.length; i++) {
            result[i] = findNearestCity(queriedPoints[i], map, xMap, yMap);
        }
        return result;
    }

    private static Map<String, Point> initMap(String[] points, int[] xCoordinates, int[] yCoordinates) {
        Map<String, Point> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.put(points[i], new Point(xCoordinates[i], yCoordinates[i]));
        }
        return map;
    }

    private static Map<Integer, List<String>> initCoordinatesMap(String[] points, int[] coordinates) {
        Map<Integer, List<String>> coordinatesMap = new HashMap<>();
        for (int i = 0; i < coordinates.length; i++) {
            List<String> list = coordinatesMap.getOrDefault(coordinates[i], new ArrayList<>());
            list.add(points[i]);
            coordinatesMap.put(coordinates[i], list);
        }
        return coordinatesMap;
    }

    private static String findNearestCity(String queried, Map<String, Point> map,
            Map<Integer, List<String>> xMap, Map<Integer, List<String>> yMap) {
        String nearestCity = "NONE";
        int shortestDistance = Integer.MAX_VALUE;
        int x = map.get(queried).getX();
        int y = map.get(queried).getY();

        if (xMap.containsKey(x)) {
            for (String city : xMap.get(x)) {
                if (city.equals(queried)) {
                    continue;
                }
                int newDistance = Math.abs(map.get(city).getY() - y);
                if (newDistance < shortestDistance) {
                    shortestDistance = newDistance;
                    nearestCity = city;
                }
            }
        }

        if (yMap.containsKey(y)) {
            for (String city : yMap.get(y)) {
                if (city.equals(queried)) {
                    continue;
                }
                int newDistance = Math.abs(map.get(city).getX() - x);
                if (newDistance < shortestDistance) {
                    shortestDistance = newDistance;
                    nearestCity = city;
                }
            }
        }

        return nearestCity;
    }

    private static class Point {
        private int x;
        private int y;

        public Point(int newX, int newY) {
            x = newX;
            y = newY;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static void main(String[] args) {
        // int numOfPoints = 3;
        String[] points = {"p1", "p2", "p3"};
        int[] xCoordinates = {30, 20, 10};
        int[] yCoordinates = {30, 20, 30};
        // int numOfQueriedPoints = 3;
        String[] queriedPoints = {"p3", "p2", "p1"};
        System.out.println(Arrays.toString(findNearestCities(points, xCoordinates, yCoordinates, queriedPoints)));
        // output: ["p1", "NONE", "p3"]
    }

}
