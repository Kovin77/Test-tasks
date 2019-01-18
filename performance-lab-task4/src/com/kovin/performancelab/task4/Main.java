package com.kovin.performancelab.task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // array for time interval; consists native strings from file == in and out times
        ArrayList<String> timesFromFile = new ArrayList<>();
        // array consists time stamps for every IN or OUT movements (no matter in or out)
        ArrayList<String> timeIntervals = new ArrayList<>();

        Scanner in = new Scanner(System.in); // set in-stream to console
        System.out.print("Enter a file name: ");
        File file = new File(in.next());

        // read data from file
        try {
            Scanner scanner = new Scanner(file);                  // set in-steam to file
            while (scanner.hasNextLine()) {                       // reading line by line from file
                String line = scanner.nextLine();
                timesFromFile.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

        // filling array timeIntervals with times from timesFromFile,
        // each time stamp is 1 element no matter people got In or Out
        for (int i = 0; i < timesFromFile.size(); i++) {
            timeIntervals.add(timeIn(timesFromFile.get(i)));
            timeIntervals.add(timeOut(timesFromFile.get(i)));
        }

        // Sort records of time to create time intervals without overlapping
        Collections.sort(timeIntervals);

/*        for (int i = 0; i < timeIntervals.size(); i++) System.out.println("|" + timeIntervals.get(i) + "|");
        System.out.println(timeIntervals.size());
        System.out.println();
*/
        // remove duplicates
        boolean noDublicates = false;
        int c = 0;

        while (!noDublicates) {
            for (int j = c + 1; j < timeIntervals.size(); j++)
                if (timeIntervals.get(j).equals(timeIntervals.get(c))) {
                    timeIntervals.remove(j);
                    c = -1;
                    break;
                }
            c++;
            if (c == timeIntervals.size()) {noDublicates = true; }
        }

        // set array which represents number of people for each time interval
        // (*1*) peopleN[x] = number of people for interval [timeIntervals(x) ; timeIntervals(x+1))
        int[] peopleN = new int[timeIntervals.size()];
        for (int i = 0; i < peopleN.length; i++) peopleN[i] = 0;

        // fill array peopleN
        for (int i = 0; i < timesFromFile.size(); i++) {
            int indexIn, indexOut; // indexes of elements in timeIntervals
            indexIn = timeIntervals.indexOf(timeIn(timesFromFile.get(i)));
            indexOut = timeIntervals.indexOf(timeOut(timesFromFile.get(i)));
            // here, with logic mentioned in (*1*), we set +1 to number of people
            // for corresponding time interval
            for (int k = indexIn; k < indexOut; k++) peopleN[k]++;
        }

        // find maximum in peopleN
        int maxPeople = 0;
        for (int i = 0; i < peopleN.length; i++)
            if (peopleN[i] > maxPeople) maxPeople = peopleN[i];

        // show results
        System.out.println("Maximum number of people = "+ maxPeople);
        for (int i = 0; i < peopleN.length; i++)
            if (peopleN[i] == maxPeople)
                System.out.println("form " + timeIntervals.get(i) + " till " + timeIntervals.get(i + 1));

    } // main

    private static String timeIn(String s) { // Take first part from string like "09:12:18 09:27:11"
        String[] lineParts = s.split(" ");
        return lineParts[0];
    }

    private static String timeOut(String s) { // Take second part from string like "09:12:18 09:27:11"
        String[] lineParts = s.split(" ");
        return lineParts[1];
    }
}
