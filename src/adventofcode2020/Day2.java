package adventofcode2020;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day2 {
    int year = 2020, day = 2;

    public Day2() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    public void task1() {
        ArrayList<String> list = FetchData.getDataAsArrayList(year, day);

        int total = 0;
        for (String string : list) {
            int min = Integer.parseInt(string.split(" ")[0].split("-")[0]);
            int max = Integer.parseInt(string.split(" ")[0].split("-")[1]);
            String c = string.split(" ")[1].split(":")[0];
            String p = string.split(" ")[2];

            int diff = p.length() - p.replace(c, "").length();
            if (diff >= min && diff <= max) {
                total++;
            }
        }
        System.out.println(total);
    }

    public void task2() {
        ArrayList<String> list = FetchData.getDataAsArrayList(year, day);
        int total = 0;
        for (String string : list) {
            int posA = Integer.parseInt(string.split(" ")[0].split("-")[0]);
            int posB = Integer.parseInt(string.split(" ")[0].split("-")[1]);
            String c = string.split(" ")[1].split(":")[0];
            String p = string.split(" ")[2];

            if (p.substring(posA - 1, posA).equals(c)) {
                if (!p.substring(posB - 1, posB).equals(c)) {
                    total++;
                }
            } else {
                if (p.substring(posB - 1, posB).equals(c)) {
                    total++;
                }
            }
        }
        System.out.println(total);
    }
}