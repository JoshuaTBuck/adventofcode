package adventofcode2015;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day8 {
    int year = 2015, day = 8;

    public Day8() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    public void task1() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        int totalDif = 0;
        String[] hexadezimal = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
        for (String string : instructions) {
            int originalLength = string.length();

            string = string.substring(1, originalLength - 1);
            string = string.replace("\\\"", "\"");
            string = string.replace("\\\\", "\\");

            if (string.contains("\\x")) {
                for (String sign : hexadezimal) {
                    if (string.contains("\\x" + sign)) {
                        string = string.replace("\\x" + sign, "");
                    }
                }
            }
            totalDif += (originalLength - string.length());
        }
        System.out.println(totalDif);

    }

    public void task2() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        int totalDif = 0;
        for (String string : instructions) {
            int increase = 2;
            increase += string.replace("\"", "\\\"").length() - string.length();
            increase += string.replace("\\", "\\\\").length() - string.length();

            totalDif += increase;
        }
        System.out.println(totalDif);
    }
}