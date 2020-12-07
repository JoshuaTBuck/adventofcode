package adventofcode2015;

public class Day10 {
    int year = 2015, day = 10;

    public Day10() {
        String input = "1113122113";

        System.out.println("Task 1:");
        System.out.println(lookAndSay(input, 40).length());
        System.out.println("\nTask 2:");
        System.out.println(lookAndSay(input, 50).length());
    }

    private String lookAndSay(String current, int runs) {
        StringBuilder next = new StringBuilder();
        char lastPart = 0;
        int count = 0;
        for (char part : current.toCharArray()) {
            if (lastPart != 0 && part != lastPart) {
                next.append(count).append(lastPart);
                lastPart = part;
                count = 1;
            } else {
                count++;
                lastPart = part;
            }
        }
        next.append(count).append(lastPart);
        if (runs == 1) {
            return next.toString();
        }
        return lookAndSay(next.toString(), runs - 1);
    }
}