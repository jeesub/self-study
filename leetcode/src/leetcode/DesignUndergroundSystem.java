package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Design Underground System.
 * 
 * [Check in and Check out]
 * Map (id, start station & start time)
 * At checkin, save the data of id, start station and start time.
 * At checkout, calculate a travel time and record it.
 * Remove the travel data from the map.
 * 
 * [Travel Data]
 * Map (start station + salt + end station, average time, number of journeys)
 * The map can return average time in O(1).
 * Update an average time: 
 * newAvg = (avg * # of journey + new time) / (# of journey + 1) 
 *        = avg * (# of journey) / (# of journey + 1) + new time / (# of journey + 1)
 *        = avg / (# of journey + 1) * (# of journey) + new time / (# of journey + 1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class DesignUndergroundSystem {
    private final String DELIMITER = "$";
    private Map<Integer, CheckIn> checkInMap;
    private Map<String, AverageJourney> journeyMap;
        
    public DesignUndergroundSystem() {
        checkInMap = new HashMap<>();
        journeyMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkInMap.remove(id);
        String journey = checkIn.station + DELIMITER + stationName;
        int newTime = t - checkIn.startTime;
        if (journeyMap.containsKey(journey)) {
            AverageJourney averageJourney = journeyMap.get(journey);
            averageJourney.add(newTime);
        } else {
            AverageJourney newJourneyTime = new AverageJourney((double) newTime, 1);
            journeyMap.put(journey, newJourneyTime);
        }
    }
    
    public double getAverageTime(String startStation, String endStation) {
        return journeyMap.get(startStation + DELIMITER + endStation).average;
    }
    
    private class CheckIn {
        String station;
        int startTime;
        
        public CheckIn(String newStation, int newStartTime) {
            station = newStation;
            startTime = newStartTime;
        }
    }
    
    private class AverageJourney {
        double average;
        int numberOfJourney;
        
        public AverageJourney(double newAverage, int newNumberOfJourney) {
            average = newAverage;
            numberOfJourney = newNumberOfJourney;
        }
        
        private void add(int newTime) {
            double newAverage = average / (numberOfJourney + 1) * numberOfJourney
                + (double) newTime / (numberOfJourney + 1);
            this.average = newAverage;
            this.numberOfJourney++;
        }
    }
}
