package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) throws IOException {

        //////
        if (args.length != 1) {
            System.out.println(" java .\\Task4.java  test4.txt\n");
            System.exit(1);
        }
        String fileName = args[0];
        int[] nums = readArrayFromFile(fileName);

        int median = findMedian(nums);
        int steps = calculateSteps(nums, median);

        System.out.println("min koll step " + steps);
    }


    private static int[] readArrayFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int count = 0;
            while (reader.readLine() != null) {
                count++;
            }
            reader.close();

            int[] nums = new int[count];
            try (BufferedReader reader2 = new BufferedReader(new FileReader(fileName))) {
                for (int i = 0; i < count; i++) {
                    nums[i] = Integer.parseInt(reader2.readLine());
                }
            }
            return nums;
        }
    }

    private static int findMedian(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n % 2 == 0) {
            return (nums[n / 2 - 1] + nums[n / 2]) / 2;
        } else {
            return nums[n / 2];
        }
    }

    private static int calculateSteps(int[] nums, int target) {
        int steps = 0;
        for (int num : nums) {
            steps += Math.abs(num - target);

        }
        return steps;
    }
}