package com.kovin.performancelab.task1;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in); // set in-stream to console
        System.out.print("Enter a file name: ");
        File file = new File(in.next());
        SetOfNumbers setOfNumbers = new SetOfNumbers();

        // read data from file
        try {
            Scanner scanner = new Scanner(file);                  // set in-steam to file

            while (scanner.hasNextLine()) {                       // reading line by line from file
                String line = scanner.nextLine();
                    setOfNumbers.myFileNumbers.add(Integer.parseInt(line));
                }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

        // do calculations and show results
        setOfNumbers.doCalc();
        setOfNumbers.showResults();

    } // main
}
