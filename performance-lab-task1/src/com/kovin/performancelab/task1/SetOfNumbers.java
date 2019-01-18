package com.kovin.performancelab.task1;

import java.util.ArrayList;
import java.util.Arrays;


public class SetOfNumbers {

    public ArrayList<Integer> myFileNumbers = new ArrayList<Integer>();
    private int sum = 0, median, percentile90, max, min;
    private float average;

    public void doCalc(){
        Integer[] myNumbers = myFileNumbers.toArray(new Integer[myFileNumbers.size()]);
        Arrays.sort(myNumbers);

        min = myNumbers[0]; // get min as first element in sorted array
        max = myNumbers[myNumbers.length-1]; // get max as last element
        percentile90 = myNumbers[myNumbers.length * 9 / 10]; // calculate 90 percentile
        median = myNumbers[myNumbers.length * 5 / 10]; // calculate median

        for (int x = 0; x < myNumbers.length; x++) { sum += myNumbers[x]; } // calculate sum
        average = (float) sum / (float) myNumbers.length; // calculate average
    }

    public int getMin() {
        return min;
    }

    public int getMedian() {
        return median;
    }

    public int getPercentile90() {
        return this.percentile90;
    }

    public int getMax() {
        return max;
    }

    public float getAverage() {
        return average;
    }

    public void showResults() {
        System.out.println("90 percentile: " + getPercentile90());
        System.out.println("median: " + getMedian());
        System.out.println("average: " + getAverage());
        System.out.println("max: " + getMax());
        System.out.println("min: " + getMin());
    }
}
