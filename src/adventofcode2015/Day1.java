package adventofcode2015;

import helperclasses.FetchData;

public class Day1 {
    int year = 2015, day = 1;

    public Day1() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        String instructions = FetchData.getDataAsArrayList(year, day).get(0);
        int floor = 0;
        while (instructions.length() > 0) {
            if (instructions.startsWith("(")) {
                floor++;
            } else if (instructions.startsWith(")")) {
                floor--;
            }
            instructions = instructions.substring(1);
        }
        System.out.println(floor);
    }

    private void task2() {
        String instructions = FetchData.getDataAsArrayList(year, day).get(0);
        int floor = 0, steps = 0;
        while (instructions.length() > 0) {
            if (instructions.startsWith("(")) {
                floor++;
            } else if (instructions.startsWith(")")) {
                floor--;
            }
            instructions = instructions.substring(1);
            steps++;
            if (floor == -1) {
                System.out.println(steps);
                break;
            }
        }
    }
}