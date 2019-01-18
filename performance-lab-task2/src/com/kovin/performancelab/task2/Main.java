package com.kovin.performancelab.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String line = "";
        String[] lineParts;
        MyPoint po1 = new MyPoint();
        MyPoint po2 = new MyPoint();
        MyPoint po3 = new MyPoint();
        MyPoint po4 = new MyPoint();
        MyPoint poo = new MyPoint();

        // getting coordinates for checking point
        Scanner in = new Scanner(System.in); // set in-stream to console
        System.out.println("Enter a X coordinate fot the Point: ");
        poo.x = in.nextFloat();
        System.out.println("Enter a Y coordinate fot the Point: ");
        poo.y = in.nextFloat();

        // getting from file coordinates for quadrilateral corners
        System.out.print("Enter a file name: ");
        File file = new File(in.next());

        Quadrilateral quadr = new Quadrilateral(po1,po2,po3,po4);

        try {
            Scanner scanner = new Scanner(file);

            // point 1
            line = scanner.nextLine();
            line = line.replaceAll("<",""); line = line.replaceAll(">","");
            lineParts = line.split(" ");
            quadr.point1.x = Float.parseFloat(lineParts[0]);
            quadr.point1.y = Float.parseFloat(lineParts[1]);
            System.out.println("Point 1: (" + quadr.point1.x+") ("+ quadr.point1.y + ")");

            // point 2
            // Выглядит ужасно. По хорошему надо было делать точки четырёхугольника как
            // массив из объектов MyPoint, НО я решил не рефакторить код, т.к.
            // 1. Точек всего 4
            // 2. После введения массива прочтение кода было бы сложнее
            // и да, я поленился :P А вы коменты читаете? :)
            // Бонус: У программиста спросили: Какой код по вашему самый плохо? Ответ: без комментариев﻿
            line = scanner.nextLine();
            line = line.replaceAll("<",""); line = line.replaceAll(">","");
            lineParts = line.split(" ");
            quadr.point2.x = Float.parseFloat(lineParts[0]);
            quadr.point2.y = Float.parseFloat(lineParts[1]);
            System.out.println("Point 2: (" + quadr.point2.x+") ("+ quadr.point2.y + ")");

            // point 3
            line = scanner.nextLine();
            line = line.replaceAll("<",""); line = line.replaceAll(">","");
            lineParts = line.split(" ");
            quadr.point3.x = Float.parseFloat(lineParts[0]);
            quadr.point3.y = Float.parseFloat(lineParts[1]);
            System.out.println("Point 3: (" + quadr.point3.x+") ("+ quadr.point3.y + ")");

            // point 4
            line = scanner.nextLine();
            line = line.replaceAll("<",""); line = line.replaceAll(">","");
            lineParts = line.split(" ");
            quadr.point4.x = Float.parseFloat(lineParts[0]);
            quadr.point4.y = Float.parseFloat(lineParts[1]);
            System.out.println("Point 4: (" + quadr.point4.x+") ("+ quadr.point4.y + ")");

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        // checking and printing out results
        if (quadr.onCorner(poo)) {
            System.out.println("точка - вершина четырехугольника");
        } else {
            if (quadr.onSide(poo)) {
                System.out.println("точка лежит на стороне четырехугольника");
            } else {
                if (quadr.isPointInside(poo)) {
                    System.out.println("точка внутри четырехугольника");
                } else System.out.println("точка снаружи четырехугольника");
            }
        }

    } //  main
}
