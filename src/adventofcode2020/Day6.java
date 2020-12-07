package adventofcode2020;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day6 {
    int year = 2020, day = 6;

    public Day6() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        ArrayList<String> answers = FetchData.getDataAsArrayList(year, day);
        String[] letter = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z" };
        boolean[] b = new boolean[26];

        int total = 0;
        for (String answer : answers) {
            if (answer.equals("")) {
                total += getTotal(b);
                b = new boolean[26];
            } else {
                while (answer.length() > 0) {
                    for (int i = 0; i < letter.length; i++) {
                        if (answer.startsWith(letter[i])) {
                            b[i] = true;
                        }
                    }
                    answer = answer.substring(1);
                }
            }
        }
        total += getTotal(b);
        System.out.println(total);
    }

    private void task2() {
        ArrayList<String> answers = FetchData.getDataAsArrayList(year, day);
        String[] letter = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z" };
        boolean[] group = new boolean[26];
        for (int i = 0; i < group.length; i++) {
            group[i] = true;
        }

        int total = 0;
        for (String answer : answers) {
            boolean[] element = new boolean[26];
            if (answer.equals("")) {
                total += getTotal(group);
                for (int i = 0; i < group.length; i++) {
                    group[i] = true;
                }
            } else {
                while (answer.length() > 0) {
                    for (int i = 0; i < letter.length; i++) {
                        if (answer.startsWith(letter[i])) {
                            element[i] = true;
                        }
                    }
                    answer = answer.substring(1);
                }
                for (int i = 0; i < element.length; i++) {
                    if (!element[i])
                        group[i] = false;
                }
            }
        }
        total += getTotal(group);
        System.out.println(total);
    }

    private int getTotal(boolean[] b) {
        int total = 0;
        for (boolean c : b) {
            if (c) {
                total++;
            }
        }
        return total;
    }
}