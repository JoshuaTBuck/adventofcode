package adventofcode2015;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day6 {
    int year = 2015, day = 6;

    public Day6() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    public void task1() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        boolean[][] lights = new boolean[1000][1000];

        for (String instruction : instructions) {
            String[] s1 = instruction.split(",");
            int x1 = Integer.parseInt(s1[0].split(" ")[s1[0].split(" ").length - 1]);
            int y1 = Integer.parseInt(s1[1].split(" ")[0]);
            int x2 = Integer.parseInt(s1[1].split(" ")[s1[1].split(" ").length - 1]);
            int y2 = Integer.parseInt(s1[2].split(" ")[0]);

            if (instruction.startsWith("turn on")) {
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        lights[x][y] = true;
                    }
                }
            } else if (instruction.startsWith("turn off")) {
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        lights[x][y] = false;
                    }
                }
            } else if (instruction.startsWith("toggle")) {
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        lights[x][y] = !lights[x][y];
                    }
                }
            }
        }

        int totalLights = 0;
        for (int y = 0; y < lights[0].length; y++) {
            for (int x = 0; x < lights.length; x++) {
                if (lights[x][y]) {
                    totalLights++;
                }
            }
        }
        System.out.println(totalLights);
    }

    public void task2() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        short[][] lights = new short[1000][1000];

        for (String instruction : instructions) {
            String[] s1 = instruction.split(",");
            int x1 = Integer.parseInt(s1[0].split(" ")[s1[0].split(" ").length - 1]);
            int y1 = Integer.parseInt(s1[1].split(" ")[0]);
            int x2 = Integer.parseInt(s1[1].split(" ")[s1[1].split(" ").length - 1]);
            int y2 = Integer.parseInt(s1[2].split(" ")[0]);

            if (instruction.startsWith("turn on")) {
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        lights[x][y]++;
                    }
                }
            } else if (instruction.startsWith("turn off")) {
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        lights[x][y]--;
                        if (lights[x][y] < 0) {
                            lights[x][y] = 0;
                        }
                    }
                }
            } else if (instruction.startsWith("toggle")) {
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        lights[x][y] += 2;
                    }
                }
            }
        }

        int totalLights = 0;
        for (int y = 0; y < lights[0].length; y++) {
            for (int x = 0; x < lights.length; x++) {
                totalLights += lights[x][y];
            }
        }
        System.out.println(totalLights);
    }
}