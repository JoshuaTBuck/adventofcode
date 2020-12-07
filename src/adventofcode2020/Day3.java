package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3 {
    int year = 2020, day = 3;

    public Day3() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        boolean[][] treeOnField = getTreeMap();

        int trees = 0;
        int xModifier = 3, yModifier = 1;

        int x = 0;
        for (int y = 0; y < treeOnField[0].length; y += yModifier) {
            if (treeOnField[x % treeOnField.length][y]) {
                trees++;
            }
            x += xModifier;
        }

        System.out.println(trees);
    }

    private void task2() {
        boolean[][] treeOnField = getTreeMap();

        Modi[] modi = { new Modi(1, 1), new Modi(3, 1), new Modi(5, 1), new Modi(7, 1), new Modi(1, 2) };
        int trees[] = new int[modi.length];

        for (int i = 0; i < trees.length; i++) {
            int x = 0;
            for (int y = 0; y < treeOnField[0].length; y += modi[i].y) {
                if (treeOnField[x % treeOnField.length][y]) {
                    trees[i]++;
                }
                x += modi[i].x;
            }
        }

        System.out.println(trees[0] * trees[1] * trees[2] * trees[3] * trees[4]);
    }

    private class Modi {
        int x, y;

        public Modi(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean[][] getTreeMap() {
        boolean[][] treeOnField = null;
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("./src/adventofcode" + year + "/input/inputDay" + day + ".txt"));
            int height = 0;
            while (reader.readLine() != null) {
                height++;
            }
            reader.close();
            reader = new BufferedReader(new FileReader("./src/adventofcode" + year + "/input/inputDay" + day + ".txt"));
            String line = reader.readLine();
            treeOnField = new boolean[line.length()][height];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < line.length(); x++) {
                    treeOnField[x][y] = line.substring(x, x + 1).equals("#");
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return treeOnField;
    }

}