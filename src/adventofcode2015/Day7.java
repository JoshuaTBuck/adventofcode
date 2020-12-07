package adventofcode2015;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day7 {
    int year = 2015, day = 7;

    public Day7() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        ArrayList<Wire> wires = new ArrayList<>();

        // Add wires
        for (String inst : instructions) {
            String[] instPart = inst.split(" ");
            String[] letter = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                    "r", "s", "t", "u", "v", "w", "x", "y", "z" };
            for (String singlePart : instPart) {
                boolean var = false;
                for (String singleLetter : letter) {
                    if (singlePart.contains(singleLetter)) {
                        var = true;
                        break;
                    }
                }
                if (var) {
                    if (getWireIdx(wires, singlePart) == -1) {
                        wires.add(new Wire(singlePart));
                    }
                }
            }
        }

        // Init Values
        for (int i = 0; i < instructions.size(); i++) {
            for (String inst : instructions) {
                String[] instParts = inst.split(" ");
                int targetWireIdx = getWireIdx(wires, instParts[instParts.length - 1]);

                if (instParts.length == 3) {
                    int startWireIdx = getWireIdx(wires, instParts[0]);
                    if (startWireIdx == -1) {
                        wires.get(targetWireIdx).value = Integer.parseInt(instParts[0]);
                    } else {
                        wires.get(targetWireIdx).value = wires.get(startWireIdx).value;
                    }
                }
                if (instParts[0].equals("NOT")) {
                    int startWireIdx = getWireIdx(wires, instParts[1]);
                    if (startWireIdx == -1) {
                        wires.get(targetWireIdx).value = (65535 - Integer.parseInt(instParts[0]));
                    } else {
                        wires.get(targetWireIdx).value = (65535 - wires.get(startWireIdx).value);
                    }
                }
                if (instParts[1].equals("AND")) {
                    int aWireIdx = getWireIdx(wires, instParts[0]);
                    int bWireIdx = getWireIdx(wires, instParts[2]);
                    if (aWireIdx > -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value & wires.get(bWireIdx).value);
                    } else if (aWireIdx == -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) & wires.get(bWireIdx).value);
                    } else if (aWireIdx > -1 && bWireIdx == -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value & Integer.parseInt(instParts[2]));
                    } else {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0])
                                & Integer.parseInt(instParts[2]));
                    }
                }
                if (instParts[1].equals("OR")) {
                    int aWireIdx = getWireIdx(wires, instParts[0]);
                    int bWireIdx = getWireIdx(wires, instParts[2]);
                    if (aWireIdx > -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value | wires.get(bWireIdx).value);
                    } else if (aWireIdx == -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) | wires.get(bWireIdx).value);
                    } else if (aWireIdx > -1 && bWireIdx == -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value | Integer.parseInt(instParts[2]));
                    } else {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0])
                                | Integer.parseInt(instParts[2]));
                    }
                }
                if (instParts[1].equals("LSHIFT")) {
                    int aWireIdx = getWireIdx(wires, instParts[0]);
                    int bWireIdx = getWireIdx(wires, instParts[2]);
                    if (aWireIdx > -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value << wires.get(bWireIdx).value);
                    } else if (aWireIdx == -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) << wires.get(bWireIdx).value);
                    } else if (aWireIdx > -1 && bWireIdx == -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value << Integer.parseInt(instParts[2]));
                    } else {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) << Integer
                                .parseInt(instParts[2]));
                    }
                }
                if (instParts[1].equals("RSHIFT")) {
                    int aWireIdx = getWireIdx(wires, instParts[0]);
                    int bWireIdx = getWireIdx(wires, instParts[2]);
                    if (aWireIdx > -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value >> wires.get(bWireIdx).value);
                    } else if (aWireIdx == -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) >> wires.get(bWireIdx).value);
                    } else if (aWireIdx > -1 && bWireIdx == -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value >> Integer.parseInt(instParts[2]));
                    } else {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) >> Integer
                                .parseInt(instParts[2]));
                    }
                }
            }
        }

        // Print Answer
        for (Wire wire : wires) {
            if (wire.name.equals("a")) {
                System.out.println(wire.value);
            }
        }
    }

    private void task2() {
        ArrayList<String> instructions = FetchData.getDataAsArrayList(year, day);
        ArrayList<Wire> wires = new ArrayList<>();

        // Add wires
        for (String inst : instructions) {
            if (inst.endsWith(" -> b")) {
                inst = "956 -> b";
            }
            String[] instPart = inst.split(" ");
            String[] letter = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                    "r", "s", "t", "u", "v", "w", "x", "y", "z" };
            for (String singlePart : instPart) {
                boolean var = false;
                for (String singleLetter : letter) {
                    if (singlePart.contains(singleLetter)) {
                        var = true;
                        break;
                    }
                }
                if (var) {
                    if (getWireIdx(wires, singlePart) == -1) {
                        wires.add(new Wire(singlePart));
                    }
                }
            }
        }

        // Init Values
        for (int i = 0; i < instructions.size(); i++) {
            for (String inst : instructions) {
                if (inst.endsWith(" -> b")) {
                    inst = "956 -> b";
                }
                String[] instParts = inst.split(" ");
                int targetWireIdx = getWireIdx(wires, instParts[instParts.length - 1]);

                if (instParts.length == 3) {
                    int startWireIdx = getWireIdx(wires, instParts[0]);
                    if (startWireIdx == -1) {
                        wires.get(targetWireIdx).value = Integer.parseInt(instParts[0]);
                    } else {
                        wires.get(targetWireIdx).value = wires.get(startWireIdx).value;
                    }
                }
                if (instParts[0].equals("NOT")) {
                    int startWireIdx = getWireIdx(wires, instParts[1]);
                    if (startWireIdx == -1) {
                        wires.get(targetWireIdx).value = (65535 - Integer.parseInt(instParts[0]));
                    } else {
                        wires.get(targetWireIdx).value = (65535 - wires.get(startWireIdx).value);
                    }
                }
                if (instParts[1].equals("AND")) {
                    int aWireIdx = getWireIdx(wires, instParts[0]);
                    int bWireIdx = getWireIdx(wires, instParts[2]);
                    if (aWireIdx > -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value & wires.get(bWireIdx).value);
                    } else if (aWireIdx == -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) & wires.get(bWireIdx).value);
                    } else if (aWireIdx > -1 && bWireIdx == -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value & Integer.parseInt(instParts[2]));
                    } else {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0])
                                & Integer.parseInt(instParts[2]));
                    }
                }
                if (instParts[1].equals("OR")) {
                    int aWireIdx = getWireIdx(wires, instParts[0]);
                    int bWireIdx = getWireIdx(wires, instParts[2]);
                    if (aWireIdx > -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value | wires.get(bWireIdx).value);
                    } else if (aWireIdx == -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) | wires.get(bWireIdx).value);
                    } else if (aWireIdx > -1 && bWireIdx == -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value | Integer.parseInt(instParts[2]));
                    } else {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0])
                                | Integer.parseInt(instParts[2]));
                    }
                }
                if (instParts[1].equals("LSHIFT")) {
                    int aWireIdx = getWireIdx(wires, instParts[0]);
                    int bWireIdx = getWireIdx(wires, instParts[2]);
                    if (aWireIdx > -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value << wires.get(bWireIdx).value);
                    } else if (aWireIdx == -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) << wires.get(bWireIdx).value);
                    } else if (aWireIdx > -1 && bWireIdx == -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value << Integer.parseInt(instParts[2]));
                    } else {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) << Integer
                                .parseInt(instParts[2]));
                    }
                }
                if (instParts[1].equals("RSHIFT")) {
                    int aWireIdx = getWireIdx(wires, instParts[0]);
                    int bWireIdx = getWireIdx(wires, instParts[2]);
                    if (aWireIdx > -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value >> wires.get(bWireIdx).value);
                    } else if (aWireIdx == -1 && bWireIdx > -1) {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) >> wires.get(bWireIdx).value);
                    } else if (aWireIdx > -1 && bWireIdx == -1) {
                        wires.get(targetWireIdx).value = (wires.get(aWireIdx).value >> Integer.parseInt(instParts[2]));
                    } else {
                        wires.get(targetWireIdx).value = (Integer.parseInt(instParts[0]) >> Integer
                                .parseInt(instParts[2]));
                    }
                }
            }
        }

        // Print Answer
        for (Wire wire : wires) {
            if (wire.name.equals("a")) {
                System.out.println(wire.value);
            }
        }
    }

    private class Wire {
        String name;
        int value;

        public Wire(String name) {
            this.name = name;
        }
    }

    private static int getWireIdx(ArrayList<Wire> wires, String name) {
        for (int i = 0; i < wires.size(); i++) {
            if (wires.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

}