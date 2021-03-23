package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Q1235. Maximum Profit in Job Scheduling.
 * 
 * 1. DP
 * DP array keeps the maximum profit at the time.
 * Offer jobs into min-heap by ascending order of end time.
 * Iterate through the time.
 * If peak value of min-heap == time, calculate new max profit.
 * Else, dp[i] = dp[i - 1]
 * 
 * 2. TreeSet
 * Jobs in TreeSet keep the maximum profit at the end time.
 * Sort jobs by end time.
 * Add a job into TreeSet, updating the profit to the maximum profit.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1235_MaximumProfitInJobScheduling {

    public static int jobSchedulingArray(int[] startTime, int[] endTime, int[] profit) {
        PriorityQueue<Job> jobs = new PriorityQueue<>();
        int lastTime = 0;
        for (int i = 0; i < startTime.length; i++) {
            jobs.offer(new Job(startTime[i], endTime[i], profit[i]));
            lastTime = Math.max(lastTime, endTime[i]);
        }

        int[] dp = new int[lastTime + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1];
            while (!jobs.isEmpty() && jobs.peek().end == i) {
                Job curr = jobs.poll();
                dp[i] = Math.max(dp[i], dp[curr.start] + curr.profit);
            }
        }
        return dp[lastTime];
    }

    public static int jobSchedulingTreeSet(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < jobs.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs);

        TreeSet<Job> treeSet = new TreeSet<>();
        treeSet.add(new Job(0, 0, 0));
        int maxProfit = 0;
        for (int i = 0; i < jobs.length; i++) {
            Job job = jobs[i];
            int prevProfit = treeSet.floor(new Job(Integer.MIN_VALUE, job.start, Integer.MAX_VALUE)).profit;
            job.profit += prevProfit;
            maxProfit = Math.max(maxProfit, job.profit);
            job.profit = maxProfit;
            treeSet.add(job);
        }
        return maxProfit;
    }

    private static class Job implements Comparable<Job> {
        int start;
        int end;
        int profit;

        private Job(int newStart, int newEnd, int newProfit) {
            start = newStart;
            end = newEnd;
            profit = newProfit;
        }

        @Override
        public int compareTo(Job other) {
            if (this.end != other.end) {
                return Integer.compare(this.end, other.end);
            }
            if (this.profit != other.profit) {
                return Integer.compare(this.profit, other.profit);
            }
            return Integer.compare(this.start, other.start);
        }
    }

    public static void main(String[] args) {
        int[] startTime = {1,2,3,4,6};
        int[] endTime = {3,5,10,6,9};
        int[] profit = {20,20,100,70,60};
        System.out.println(jobSchedulingArray(startTime, endTime, profit));
        System.out.println(jobSchedulingTreeSet(startTime, endTime, profit));
        // output: 150
    }

}
