package adventofcode2020;

import helperclasses.FetchData;
import java.util.ArrayList;

public class Day4 {
    int year = 2020, day = 4;

    public Day4() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        ArrayList<String> passports = fetchPassports();
        String[] req = { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };

        int valid = 0;
        for (String passport : passports) {
            boolean errorFound = false;
            for (String string : req) {
                if (!passport.contains(string)) {
                    errorFound = true;
                    break;
                }
            }
            if (!errorFound) {
                valid++;
            }
        }
        System.out.println(valid);
    }

    private void task2() {
        ArrayList<String> passports = fetchPassports();
        String[] req = { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };

        int valid = 0;
        for (String passport : passports) {
            boolean errorFound = false;
            for (String string : req) {
                if (!passport.contains(string)) {
                    errorFound = true;
                    break;
                }
            }
            if (!errorFound) {
                String[] passportElements = passport.substring(1).split(" ");
                for (String element : passportElements) {
                    if (element.startsWith("byr")) {
                        int birthYear = Integer.parseInt(element.split(":")[1]);
                        if (birthYear < 1920 || birthYear > 2002 || element.split(":")[1].length() != 4) {
                            errorFound = true;
                        }
                    } else if (element.startsWith("iyr")) {
                        int issueYear = Integer.parseInt(element.split(":")[1]);
                        if (issueYear < 2010 || issueYear > 2020 || element.split(":")[1].length() != 4) {
                            errorFound = true;
                        }
                    } else if (element.startsWith("eyr")) {
                        int expirationYear = Integer.parseInt(element.split(":")[1]);
                        if (expirationYear < 2020 || expirationYear > 2030 || element.split(":")[1].length() != 4) {
                            errorFound = true;
                        }
                    } else if (element.startsWith("hgt")) {
                        if (element.endsWith("cm") || element.endsWith("in")) {
                            int height = Integer
                                    .parseInt(element.split(":")[1].substring(0, element.split(":")[1].length() - 2));
                            if (element.endsWith("cm")) {
                                if (height < 150 || height > 193) {
                                    errorFound = true;
                                }
                            } else if (element.endsWith("in")) {
                                if (height < 59 || height > 76) {
                                    errorFound = true;
                                }
                            }
                        } else {
                            errorFound = true;
                        }
                    } else if (element.startsWith("hcl")) {
                        String hairColor = element.split(":")[1];
                        if (!hairColor.startsWith("#") || hairColor.length() != 7) {
                            errorFound = true;
                        }
                    } else if (element.startsWith("ecl")) {
                        String eyeColor = element.split(":")[1];
                        if (!eyeColor.equals("amb") && !eyeColor.equals("blu") && !eyeColor.equals("brn")
                                && !eyeColor.equals("gry") && !eyeColor.equals("grn") && !eyeColor.equals("hzl")
                                && !eyeColor.equals("oth")) {
                            errorFound = true;
                        }
                    } else if (element.startsWith("pid")) {
                        String passportID = element.split(":")[1];
                        try {
                            Integer.parseInt(passportID);
                        } catch (Exception e) {
                            errorFound = true;
                        }
                        if (passportID.length() != 9) {
                            errorFound = true;
                        }
                    }
                }
                if (!errorFound) {
                    valid++;
                }
            }
        }
        System.out.println(valid);
    }

    private ArrayList<String> fetchPassports() {
        ArrayList<String> list = FetchData.getDataAsArrayList(year, day);
        ArrayList<String> passports = new ArrayList<>();
        String passport = "";
        for (String listElement : list) {
            if (!listElement.equals("")) {
                passport += " " + listElement;
            } else {
                passports.add(new String(passport));
                passport = "";
            }
        }
        passports.add(new String(passport));
        return passports;
    }
}