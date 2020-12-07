package adventofcode2015;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day9 {
    int year = 2015, day = 9;

    public Day9() {
        System.out.println("Task 1:");
        task1();

        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        ArrayList<Location> loc = new ArrayList<>();

        for (String inst : instructions) {
            String[] instPart = inst.split(" ");
            if (getLocIdx(loc, instPart[0]) == -1) {
                loc.add(new Location(instPart[0]));
            }
            if (getLocIdx(loc, instPart[2]) == -1) {
                loc.add(new Location(instPart[2]));
            }
        }

        for (String inst : instructions) {
            String[] instPart = inst.split(" ");
            loc.get(getLocIdx(loc, instPart[0])).addLoc(loc.get(getLocIdx(loc, instPart[2])),
                    Integer.parseInt(instPart[4]));
            loc.get(getLocIdx(loc, instPart[2])).addLoc(loc.get(getLocIdx(loc, instPart[0])),
                    Integer.parseInt(instPart[4]));
        }

        ArrayList<String> locNames = new ArrayList<>();
        for (Location location : loc) {
            locNames.add(location.name);
        }

        int minDist = Integer.MAX_VALUE;
        for (Location location : loc) {
            int guess = location.pathFinding(locNames, 0);
            if (guess < minDist) {
                minDist = guess;
            }
        }
        System.out.println("md: " + minDist);
    }

    private void task2() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        ArrayList<Location> loc = new ArrayList<>();

        for (String inst : instructions) {
            String[] instPart = inst.split(" ");
            if (getLocIdx(loc, instPart[0]) == -1) {
                loc.add(new Location2(instPart[0]));
            }
            if (getLocIdx(loc, instPart[2]) == -1) {
                loc.add(new Location2(instPart[2]));
            }
        }

        for (String instruction : instructions) {
            String[] instPart = instruction.split(" ");
            loc.get(getLocIdx(loc, instPart[0])).addLoc(loc.get(getLocIdx(loc, instPart[2])),
                    Integer.parseInt(instPart[4]));
            loc.get(getLocIdx(loc, instPart[2])).addLoc(loc.get(getLocIdx(loc, instPart[0])),
                    Integer.parseInt(instPart[4]));
        }

        ArrayList<String> locNames = new ArrayList<>();
        for (Location location : loc) {
            locNames.add(location.name);
        }
        int minDist = Integer.MIN_VALUE;
        for (Location location : loc) {
            int guess = location.pathFinding(locNames, 0);
            if (guess > minDist) {
                minDist = guess;
            }
        }
        System.out.println("md: " + minDist);
    }

    private class Location {
        String name;
        ArrayList<Location> conLocs = new ArrayList<>();
        ArrayList<Integer> conLocsDists = new ArrayList<>();

        public Location(String name) {
            this.name = name;
        }

        public void addLoc(Location conLoc, int conLocDist) {
            conLocs.add(conLoc);
            conLocsDists.add(conLocDist);
        }

        public int pathFinding(ArrayList<String> locsToVisit, int dist) {
            ArrayList<String> newLocsToVisit = new ArrayList<>(locsToVisit);
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < newLocsToVisit.size(); i++) {
                if (newLocsToVisit.get(i).equals(name)) {
                    newLocsToVisit.remove(i);
                    break;
                }
            }

            for (int cLIdx = 0; cLIdx < conLocs.size(); cLIdx++) {
                for (int listIdx = 0; listIdx < newLocsToVisit.size(); listIdx++) {
                    if (newLocsToVisit.get(listIdx).equals(conLocs.get(cLIdx).name)) {
                        int guess = conLocs.get(cLIdx).pathFinding(newLocsToVisit, conLocsDists.get(cLIdx));
                        if (guess < minDist) {
                            minDist = guess;
                        }
                        break;
                    }
                }
            }
            if (minDist == Integer.MAX_VALUE) {
                return dist;
            }
            return minDist + dist;
        }

    }

    private class Location2 extends Location {
        public Location2(String name) {
            super(name);
        }

        @Override
        public int pathFinding(ArrayList<String> locsToVisit, int dist) {
            ArrayList<String> newLocsToVisit = new ArrayList<>(locsToVisit);
            int minDist = Integer.MIN_VALUE;
            for (int i = 0; i < newLocsToVisit.size(); i++) {
                if (newLocsToVisit.get(i).equals(name)) {
                    newLocsToVisit.remove(i);
                    break;
                }
            }

            for (int cLIdx = 0; cLIdx < conLocs.size(); cLIdx++) {
                for (int listIdx = 0; listIdx < newLocsToVisit.size(); listIdx++) {
                    if (newLocsToVisit.get(listIdx).equals(conLocs.get(cLIdx).name)) {
                        int guess = conLocs.get(cLIdx).pathFinding(newLocsToVisit, conLocsDists.get(cLIdx));
                        if (guess > minDist) {
                            minDist = guess;
                        }
                        break;
                    }
                }
            }
            if (minDist == Integer.MIN_VALUE) {
                return dist;
            }
            return minDist + dist;
        }
    }

    private int getLocIdx(ArrayList<Location> locations, String name) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }
}