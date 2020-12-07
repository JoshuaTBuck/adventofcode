package adventofcode2015;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {

    String input = "iwrupvqb";
    MessageDigest md;

    public Day4() {
        System.out.println("Task 1:");
        task1();
        System.out.println("\nTask 2:");
        task2();
    }

    private void task1() {
        int sol = 0;
        while (!getMd5(input + sol).startsWith("00000")) {
            sol++;
        }
        System.out.println(sol);
    }

    private void task2() {
        int sol = 0;
        while (!getMd5(input + sol).startsWith("000000")) {
            sol++;
        }
        System.out.println(sol);
    }

    public String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}