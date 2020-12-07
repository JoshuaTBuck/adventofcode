package adventofcode2015;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day2 {

    int year = 2015, day = 2;

    public Day2() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        ArrayList<String> packages = FetchData.getDataAsArrayList(year, day);
        int totalArea = 0;

        for (String packageDim : packages) {
            String[] side = packageDim.split("x");
            int w = Integer.parseInt(side[0]);
            int h = Integer.parseInt(side[1]);
            int l = Integer.parseInt(side[2]);
            int sideA = l * w;
            int sideB = h * w;
            int sideC = h * l;
            int smallest = Integer.min(Integer.min(sideA, sideB), sideC);
            totalArea += 2 * sideA + 2 * sideB + 2 * sideC + smallest;
        }
        System.out.println(totalArea);
    }

    private void task2() {
        ArrayList<String> packages = FetchData.getDataAsArrayList(year, day);
        int totalArea = 0;

        for (String packageDim : packages) {
            String[] side = packageDim.split("x");
            int w = Integer.parseInt(side[0]);
            int h = Integer.parseInt(side[1]);
            int l = Integer.parseInt(side[2]);
            int smallest = Integer.min(Integer.min(w, h), l);
            int smallest2;
            if (smallest == w) {
                smallest2 = Integer.min(h, l);
            } else if (smallest == h) {
                smallest2 = Integer.min(w, l);
            } else {
                smallest2 = Integer.min(w, h);
            }
            totalArea += 2 * smallest + 2 * smallest2 + w * h * l;

        }
        System.out.println(totalArea);
    }
}