package adventofcode2015;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day3 {
    int year = 2015, day = 3;

    public Day3() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        String instructions = FetchData.getDataAsArrayList(year, day).get(0);
        ArrayList<House> visitedHouses = new ArrayList<>();
        int x1 = 0, y1 = 0;
        while (instructions.length() > 0) {

            if (instructions.startsWith("^")) {
                y1--;
            } else if (instructions.startsWith("v")) {
                y1++;
            } else if (instructions.startsWith("<")) {
                x1--;
            } else if (instructions.startsWith(">")) {
                x1++;
            }
            if (!visitedHouse(x1, y1, visitedHouses)) {
                visitedHouses.add(new House(x1, y1));
            }

            instructions = instructions.substring(1);
        }
        System.out.println(visitedHouses.size());
    }

    private void task2() {
        String instructions = FetchData.getDataAsArrayList(year, day).get(0);
        ArrayList<House> visitedHouses = new ArrayList<>();
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        boolean realSantasTurn = false;
        while (instructions.length() > 0) {
            if (instructions.startsWith("^")) {
                if (realSantasTurn) {
                    y1--;
                } else {
                    y2--;
                }
            } else if (instructions.startsWith("v")) {
                if (realSantasTurn) {
                    y1++;
                } else {
                    y2++;
                }
            } else if (instructions.startsWith("<")) {
                if (realSantasTurn) {
                    x1--;
                } else {
                    x2--;
                }
            } else if (instructions.startsWith(">")) {
                if (realSantasTurn) {
                    x1++;
                } else {
                    x2++;
                }
            }
            if (realSantasTurn) {
                if (!visitedHouse(x1, y1, visitedHouses)) {
                    visitedHouses.add(new House(x1, y1));
                }
            } else {
                if (!visitedHouse(x2, y2, visitedHouses)) {
                    visitedHouses.add(new House(x2, y2));
                }
            }
            instructions = instructions.substring(1);
            realSantasTurn = !realSantasTurn;
        }
        System.out.println(visitedHouses.size());
    }

    public boolean visitedHouse(int x, int y, ArrayList<House> houses) {
        for (House house : houses) {
            if (house.x == x && house.y == y) {
                return true;
            }
        }
        return false;
    }

    class House {
        public int x, y;

        public House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}