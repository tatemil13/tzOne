package task1;

import java.util.Scanner;

//12 23 34 45 51
//12345 56123 34561
public class Task1 {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter n: ");
//        int n = scanner.nextInt();
//        System.out.print("Enter m: ");
//        int m = scanner.nextInt();

        /////
        if (args.length != 2) {
            System.out.println("java .\\Task1.java <n> <m> ");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        /////

        char[] circularArray = new char[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = (char) ('1' + i);
        }

        StringBuilder path = new StringBuilder();
        int startIndex = 0;
        while (true) {
            path.append(circularArray[startIndex]);
            startIndex = (startIndex + m - 1) % n;
            if (startIndex == 0) {
                break;
            }
        }

        System.out.println("answer: " + path);
    }

}

