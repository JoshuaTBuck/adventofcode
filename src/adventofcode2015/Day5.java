package adventofcode2015;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day5 {
    int year = 2015, day = 5;

    public Day5() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        ArrayList<String> list = FetchData.getDataAsArrayList(year, day);
        String[] letter = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z" };
        String[] vovel = { "a", "e", "i", "o", "u", };

        int validStrings = 0;

        for (String element : list) {
            boolean enoughVovels = false;
            boolean doubleLetter = false;
            boolean noBlacklistElements = false;

            int numOfVovels = 0;
            for (String string : vovel) {
                numOfVovels += element.length() - element.replace(string, "").length();
                if (numOfVovels >= 3) {
                    enoughVovels = true;
                    break;
                }
            }

            for (String string : letter) {
                if (element.contains(string + string)) {
                    doubleLetter = true;
                    break;
                }
            }

            if (!element.contains("ab") && !element.contains("cd") && !element.contains("pq")
                    && !element.contains("xy")) {
                noBlacklistElements = true;
            }

            if (doubleLetter && enoughVovels && noBlacklistElements) {
                validStrings++;
            }
        }
        System.out.println(validStrings);
    }

    private void task2() {
        ArrayList<String> list = FetchData.getDataAsArrayList(year, day);
        String[] letter = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z" };

        int validStrings = 0;

        for (String element : list) {
            boolean doubleLetters = false;
            boolean whitelistElements = false;

            for (String letterA : letter) {
                for (String letterB : letter) {
                    if (element.contains(letterA + letterB)) {
                        if (element.length() - element.replace(letterA + letterB, "").length() >= 4) {
                            doubleLetters = true;
                        }
                    }

                }
            }

            for (String letterA : letter) {
                for (String letterB : letter) {
                    if (element.contains(letterA + letterB + letterA)) {
                        whitelistElements = true;
                    }
                }
            }

            if (doubleLetters && whitelistElements) {
                validStrings++;
            }
        }
        System.out.println(validStrings);
    }
}