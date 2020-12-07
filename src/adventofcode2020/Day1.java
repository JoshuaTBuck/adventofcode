package adventofcode2020;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day1 {
    int year = 2020, day = 1;

    public Day1() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        int[] input = convertIntegers(FetchData.getDataAsArrayList(year, day));
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] + input[j] == 2020) {
                    System.out.println(input[i] * input[j]);
                }
            }
        }
    }

    private void task2() {
        int[] input = convertIntegers(FetchData.getDataAsArrayList(year, day));
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    if (input[i] + input[j] + input[k] == 2020) {
                        System.out.println(input[i] * input[j] * input[k]);
                    }
                }
            }
        }
    }

    private int[] convertIntegers(ArrayList<String> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Integer.parseInt(integers.get(i));
        }
        return ret;
    }
}