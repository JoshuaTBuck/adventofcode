package adventofcode2020;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day5 {
    int year = 2020, day = 5;

    public Day5() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        ArrayList<String> passports = FetchData.getDataAsArrayList(year, day);
        int seatID = Integer.MIN_VALUE;

        for (String passport : passports) {
            int rowL = 0, rowH = 128, colL = 0, colH = 8;
            while (passport.length() > 0) {
                if (passport.startsWith("F")) {
                    rowH -= (rowH - rowL) / 2;
                } else if (passport.startsWith("B")) {
                    rowL += (rowH - rowL) / 2;
                } else if (passport.startsWith("L")) {
                    colH -= (colH - colL) / 2;
                } else if (passport.startsWith("R")) {
                    colL += (colH - colL) / 2;
                }
                passport = passport.substring(1);
            }
            if(rowL * 8 + colL > seatID){
                seatID = rowL * 8 + colL;
            }
        }

        System.out.println(seatID);
    }

    private void task2() {
        ArrayList<String> passports = FetchData.getDataAsArrayList(year, day);
        Point[] point = new Point[1024];
        int seatID = Integer.MIN_VALUE;

        for (String passport : passports) {
            int rowL = 0, rowH = 128, colL = 0, colH = 8;
            while (passport.length() > 0) {
                if (passport.startsWith("F")) {
                    rowH -= (rowH - rowL) / 2;
                } else if (passport.startsWith("B")) {
                    rowL += (rowH - rowL) / 2;
                } else if (passport.startsWith("L")) {
                    colH -= (colH - colL) / 2;
                } else if (passport.startsWith("R")) {
                    colL += (colH - colL) / 2;
                }
                passport = passport.substring(1);
            }
            seatID = rowL * 8 + colL;
            point[seatID] = new Point(colL, rowL);
        }

        for (int y = 0; y < point.length; y++) {
            if (point[y] == null) {
                if (y > 0 && y < point.length - 1) {
                    if (point[y - 1] != null && point[y + 1] != null) {
                        System.out.println(y);
                    }
                }
            }
        }
    }

    private class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}