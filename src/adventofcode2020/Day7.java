package adventofcode2020;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day7 {
    int year = 2020, day = 7;

    public Day7() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        ArrayList<Bag> bags = new ArrayList<>();

        // Add bags
        for (String inst : instructions) {
            String[] instPart = inst.split(" ");
            String bagName = instPart[0] + " " + instPart[1];
            if (getBagIdx(bags, bagName) == -1) {
                bags.add(new Bag(bagName));
            }
        }

        // Init Values
        for (String inst : instructions) {
            if (!inst.endsWith("contain no other bags.")) {
                String[] instParts = inst.substring(0, inst.length() - 2).split(", ");
                String bagName = instParts[0].split(" ")[0] + " " + instParts[0].split(" ")[1];
                for (String string : instParts) {
                    String[] bagEParts = string.split(" ");
                    String bagEName = bagEParts[bagEParts.length - 3] + " " + bagEParts[bagEParts.length - 2];
                    int bagEAmount = Integer.parseInt(bagEParts[bagEParts.length - 4]);
                    getBag(bags, bagName).containingBags.add(new BagElement(getBag(bags, bagEName), bagEAmount));
                }
            }
        }
        for (int i = 0; i < bags.size() - 1; i++) {
            for (Bag bag : bags) {
                bag.canContainGoldenBag();
            }
        }

        // Print Answer
        int total = 0;
        for (Bag bag : bags) {
            if (bag.containShinyGoldBag) {
                total++;
            }
        }
        System.out.println(total);
    }

    private void task2() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        ArrayList<Bag> bags = new ArrayList<>();

        // Add bags
        for (String inst : instructions) {
            String[] instPart = inst.split(" ");
            String bagName = instPart[0] + " " + instPart[1];
            if (getBagIdx(bags, bagName) == -1) {
                bags.add(new Bag(bagName));
            }
        }

        // Init Values
        for (String inst : instructions) {
            if (!inst.endsWith("contain no other bags.")) {
                String[] instParts = inst.substring(0, inst.length() - 2).split(", ");
                String bagName = instParts[0].split(" ")[0] + " " + instParts[0].split(" ")[1];
                for (String string : instParts) {
                    String[] bagEParts = string.split(" ");
                    String bagEName = bagEParts[bagEParts.length - 3] + " " + bagEParts[bagEParts.length - 2];
                    int bagEAmount = Integer.parseInt(bagEParts[bagEParts.length - 4]);
                    getBag(bags, bagName).containingBags.add(new BagElement(getBag(bags, bagEName), bagEAmount));
                }
            }
        }

        // Print Answer
        System.out.println(getBag(bags, "shiny gold").getBagsWithin());
    }

    private class BagElement {
        Bag bag;
        int amount;

        public BagElement(Bag bag, int amount) {
            this.bag = bag;
            this.amount = amount;
        }
    }

    private class Bag {
        String name;
        ArrayList<BagElement> containingBags = new ArrayList<>();
        boolean containShinyGoldBag = false;

        public Bag(String name) {
            this.name = name;
        }

        public int getBagsWithin() {
            int total = 0;
            for (BagElement bagE : containingBags) {
                total += (bagE.bag.getBagsWithin() + 1) * bagE.amount;
            }
            return total;
        }

        public void canContainGoldenBag() {
            for (BagElement bagE : containingBags) {
                if (bagE.bag.containShinyGoldBag || bagE.bag.name.equals("shiny gold")) {
                    containShinyGoldBag = true;
                }
            }
        }
    }

    private static Bag getBag(ArrayList<Bag> bags, String name) {
        int bagIdx = getBagIdx(bags, name);
        if (bagIdx > -1) {
            return bags.get(bagIdx);
        } else {
            return null;
        }
    }

    private static int getBagIdx(ArrayList<Bag> bags, String name) {
        for (int i = 0; i < bags.size(); i++) {
            if (bags.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }
}