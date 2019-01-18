package com.kovin.performancelab.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        float[][] table = new float[6][16];
        // table [x][y] = N
        //  x - cashbox ID, x from 0 to 4 is for cashboxes, x=5 is sum of 5 cashboxes
        //  y - 'time', 1 correspond to time interval from 0:00 till 0:30 (30 min) counting from
        //       shop opening; 2 to interval from 0:30 till 1:00 and so on; 16 to interval from
        //       7:30 till 8:00
        //  N - average number of people standing at cashbox x recorded for time interval y

        File[] files = new File[5];   // array for files

        // ask for 5 files names and read values from files;
        // each file consists data from each cashbox
        Scanner in = new Scanner(System.in);
        String line;
        for (int x = 0; x < 5; x++) {
            System.out.print("Enter a name of file with data for cashbox " + (x+1) + ": ");
            files[x] = new File(in.next());

            //reading values from file to array
            try {
                Scanner scanner = new Scanner(files[x]);
                for (int y = 0; y < 16; y++) {              // getting data from 16 lines
                    line = scanner.nextLine();
                    table[x][y] = Float.parseFloat(line);
                }
                scanner.close();
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
                System.exit(-1);
            }
        }

        // calculate total average number of people for each interval
        // use table[5] for this
        for (int y = 0; y < 16; y++) {
            table[5][y] = 0;
            for (int x = 0; x < 5; x++) table[5][y] += table[x][y];
        }

        // lets find time interval with biggest number of people
        int maxN = 0;
        for (int y = 0; y < 16; y++) {
            if (table[5][y] > table[5][maxN]) { maxN = y; }
        }

        System.out.println("The largest number of visitors was from " +     // show time interval
                (int) Math.floor(maxN * 0.5d) + ":" + (maxN % 2) * 3 + "0 till " +   // from ?:?0
                (int) Math.floor((maxN+1) * 0.5d) + ":" + ((maxN+1) % 2) * 3 + "0"); // til ?:?0

    } // method main
}
