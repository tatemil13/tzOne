package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Task2 {


    public static void main(String[] args) {


        if (args.length != 2) {
            System.out.println(" java .\\task2.java circle.txt points.txt\n");
            return;
        }

        try (BufferedReader circleReader = new BufferedReader(new FileReader(args[0]));
             BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]))) {

            String[] circleLine = circleReader.readLine().split(" ");
            double centerX = Double.parseDouble(circleLine[0]);
            double centerY = Double.parseDouble(circleLine[1]);
            double radius = Double.parseDouble(circleReader.readLine());


            String line;
            while ((line = pointsReader.readLine()) != null) {
                String[] pointCoords = line.split(" ");
                double pointX = Double.parseDouble(pointCoords[0]);
                double pointY = Double.parseDouble(pointCoords[1]);

                double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));


                if (distance == radius) {
                    System.out.println("0");
                } else if (distance < radius) {
                    System.out.println("1");
                } else {
                    System.out.println("2");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}



