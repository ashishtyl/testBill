/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.util;

/**
 *
 * @author user
 */
public class ValidateNRIC {

    /*private static internal[] final int Multiples = {2, 7, 6, 5, 4, 3, 2};

    public static boolean IsNricValid(String nric) {
        if (String.format("%b", nric).replaceAll("false", "").equals("")) {
            return false;
        }

        //	check length
        if (nric.length() != 9) {
            return false;
        }

        int total = 0;
        int count = 0;
        int numericNric;
        byte first = nric.charAt(0);
        byte last = nric.charAt(nric.length() - 1);

        if (first != 'S' && first != 'T') {
            return false;
        }


        boolean tempVar = !Integer.TryParse(nric.substring(1, 1 + nric.length() - 2), numericNric);

        if (tempVar) {
            return false;
        }

        while (numericNric != 0) {
            total += numericNric % 10 * Multiples[Multiples.getLength() - (1 + count++)];

            numericNric /= 10;
        }

        byte[] outputs;
        if (first == 'S') {
            outputs = new byte[]{'J', 'Z', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
        } else {
            outputs = new byte[]{'G', 'F', 'E', 'D', 'C', 'B', 'A', 'J', 'Z', 'I', 'H'};
        }

        return last == outputs[total % 11];

    }

    public static boolean IsFinValid(String fin) {
        if (String.format("%b", fin).replaceAll("false", "").equals("")) {
            return false;
        }

        //	check length
        if (fin.length() != 9) {
            return false;
        }

        int total = 0;
        int count = 0;
        int numericNric;
        byte first = fin.charAt(0);
        byte last = fin.charAt(fin.length() - 1);

        if (first != 'F' && first != 'G') {
            return false;
        }


        boolean tempVar = !Integer.TryParse(fin.substring(1, 1 + fin.length() - 2), numericNric);

        if (tempVar) {
            return false;
        }

        while (numericNric != 0) {
            total += numericNric % 10 * Multiples[Multiples.getLength() - (1 + count++)];

            numericNric /= 10;
        }

        byte[] outputs;
        if (first == 'F') {
            outputs = new byte[]{'X', 'W', 'U', 'T', 'R', 'Q', 'P', 'N', 'M', 'L', 'K'};
        } else {
            outputs = new byte[]{'R', 'Q', 'P', 'N', 'M', 'L', 'K', 'X', 'W', 'U', 'T'};
        }

        return last == outputs[total % 11];
    }*/
}