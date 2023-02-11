
/**
 * Program Name: Addition
 * Author: Pup Abdulgapul
 * Student ID: 100362791
 * Date: Nov 19, 2022
 * Course: CPSC1150-03
 * Professor: Hengameh Hamavand
 */

import java.io.IOException;
import java.util.Scanner;

public class Addition { // program to read two inputted numbers as strings and display their sum
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String in1, in2;
        int longer = 0, shorter = 0;
        boolean run = true;

        System.out.println("This program accepts two numbers up to 40 digits long. Please enter in the first number.");
        in1 = input.nextLine();
        run = lengthCheck(in1);
        if (run) {
            run = digits(in1);
        }
        if (!run)
            return;
        System.out.println("Please enter in the second number.");
        in2 = input.nextLine();
        run = lengthCheck(in2);
        if (run) {
            run = digits(in2);
        }
        if (!run) {
            return;
        } else {
            System.out.println("");
            if (in1.length() == in2.length()) {
                longer = commaLength(in1);
                shorter = commaLength(in1);
                spacing(longer, shorter);
                comma(in1);
                System.out.println("+");
                comma(in2);
            }
            if (in2.length() > in1.length()) { // if the 2nd number is longer than the first
                longer = commaLength(in2); // get the length of the longer number
                shorter = commaLength(in1);
                spacing(longer, shorter);
                comma(in1);
                System.out.println("+");
                comma(in2);
            } else {
                longer = commaLength(in1); // get the length of the longer number
                shorter = commaLength(in2);
                comma(in1);
                System.out.println("+");
                spacing(longer, shorter);
                comma(in2);
            }

            System.out.println("");
            for (int i = 0; i <= longer; i++) {
                System.out.print("-"); // print the line thingy
            }

            if (in1.length() == in2.length()) {
                resultEqualLength(in1, in2);
            }
            if (in2.length() > in1.length()) {
                result(in2, in1);
            } else {
                result(in1, in2);
            }
        }

    }

    /**
     * Method to print the spacing for alignment
     *
     * @param l  is the length (with commas) of the longer number
     * @param sh is the length (with commas) of the shorter number
     */
    public static void spacing(int l, int sh) {
        for (int i = 0; i < (l - sh); i++) {
            System.out.print(" ");
        }
    }

    /**
     * Method to validate the given string of numbers contains up to 40 digits.
     *
     * @param s is the string of numbers
     * @return false if more than 40 digits, otherwise true
     */
    public static boolean lengthCheck(String s) {
        if (s.length() > 40 || (s.length() < 1)) {
            System.out.println("Error: number must be 1-40 digits long.");
            return false;
        } else
            return true;
    }

    /**
     * Method to validate if given string contains only digits
     *
     * @param s is the string
     * @return false if contains characters other than numerals, otherwise true
     */
    public static boolean digits(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                System.out.println("Error: number can only contain numeral digits.");
                return false;
            }
        }
        return true;
    }

    /**
     * Method to calculate length of string with the commas added
     *
     * @param s is the string
     * @return an int value
     */
    public static int commaLength(String s) {
        int l = s.length(), count = 0;
        if (l < 4) // if the string is less than 4 digits, there's no commas
            return l;
        if (l % 3 == 0) {
            for (int i = 0; i < s.length(); i += 3) {
                if (((i + 3) != l - 1) && ((i + 4) < l)) { // puts a comma after every 3 digits except after the last 3
                    count++;
                }
            }
        } else {
            int i = l % 3;
            count++;
            for (; i < s.length(); i += 3) {
                if (((i + 3) != l - 1) && ((i + 4) < l)) { // puts a comma after every 3 digits except after the last 3
                    count++;
                }
            }
        }
        return (s.length() + count);
    }

    /**
     * Method to print out the string of numbers but with commas to separate every
     * 1000
     *
     * @param s is the string
     */
    public static void comma(String s) {
        int l = s.length();
        if (s.length() < 4) {
            System.out.print(s);
            return;
        }

        if (l % 3 == 0) {
            for (int i = 0; i < s.length(); i += 3) {
                System.out.print(s.substring(i, i + 3));
                if (((i + 3) != l - 1) && ((i + 4) < l)) { // puts a comma after every 3 digits except after the last 3
                    System.out.print(",");
                }
            }
        } else {
            int i = l % 3;
            System.out.print(s.substring(0, i) + ",");
            for (; i < s.length(); i += 3) {
                System.out.print(s.substring(i, i + 3));
                if (((i + 3) != l - 1) && ((i + 4) < l)) { // puts a comma after every 3 digits except after the last 3
                    System.out.print(",");
                }
            }
        }
    }

    public static void resultEqualLength(String firstNumber, String secondNumber) {
        char[] a = new char[firstNumber.length() + 1];
        int[] l = new int[firstNumber.length() + 1];
        int[] s = new int[firstNumber.length()];
        int sum = 0, leadingZeros = s.length;

        // populate the arrays
        for (int i = 0; i < firstNumber.length(); i++) {
            l[i] = firstNumber.charAt(i) - '0';
        }
        for (int i = 0; i < secondNumber.length(); i++, leadingZeros--) {
            s[i] = secondNumber.charAt(i) - '0';
        }
        for (int i = leadingZeros; i >= 0; i--) { //fill the rest of the array with 0
            s[i] = 0;
        }


        int count = firstNumber.length() - 1; // tracks where we stopped filling in the char array

        /*
         * Starting from the last digit and working backwards, i is the index of the
         * shorter string, j is the index of the char array currently being filled and k
         * is the index of the longer string
         */
        if (firstNumber.length() != secondNumber.length()) {
            for (int i = secondNumber.length() - 1, j = firstNumber.length(), k = firstNumber.length() - 1; i >= 0; i--, j--, k--) {
                count--;
                sum = l[k] + s[i];
                if (sum > 9) { // if the sum is 10 or greater, need to carry over the 1
                    a[j] = (char) ((sum % 10) + '0');
                    l[k - 1]++;
                } else {
                    a[j] = (char) (sum + '0');
                }
            }


            if (l[0] > 9) {
                a[1] = (char) ((sum % 10) + '0');
                a[0] = '1';
            } else {
                a[1] = (char) ((l[0]) + '0');
                a[0] = '0';
            }

            for (int i = count; i > 0; i--) { // fill in the rest but stop at l[1] (dont fill in l[0])
                a[i + 1] = (char) (l[i] + '0');
            }

            // filling in a[1] and a[0]
            if (l[0] > 9) {
                a[1] = (char) ((l[0] % 10) + '0');
                a[0] = '1';
            } else {
                a[1] = (char) ((l[0]) + '0');
                a[0] = '0';
            }
        } else { // if two string lengths were the same
            for (int i = firstNumber.length() - 1, j = firstNumber.length(); i >= 0; i--, j--) {
                sum = l[i] + s[i];
                if (sum > 9) { // if the sum is 10 or greater, need to carry over the 1
                    a[j] = (char) ((sum % 10) + '0');
                    l[i - 1]++;
                } else {
                    a[j] = (char) (sum + '0');
                }
            }

            // filling in a[1] and a[0]
            if (l[0] > 9) {
                a[1] = (char) ((sum % 10) + '0');
                a[0] = '1';
            } else
                a[0] = '0';
        }

        String r = "";
        if (a[0] != '0') { // if the first character in a is 1 then use the whole array
            for (char ch : a) { // turn it back into a string
                r = r + ch;
            }
        } else {
            for (int i = 1; i < a.length; i++) {
                char ch = a[i];
                r = r + ch;
            }
        }

        System.out.println("");
        // pass it to comma() to print it out
        comma(r);
    }

    /**
     * Method to calculate and print the sum of 2 strings of numbers
     *
     * @param longer  is the longer string of numbers
     * @param shorter is the shorter string of numbers
     */
    public static void result(String longer, String shorter) {
        char[] a = new char[longer.length() + 1];
        int[] l = new int[longer.length()];
        int[] s = new int[longer.length()];
        int sum = 0, leadingZeros = shorter.length();

        // populate the arrays
        for (int i = 0; i < longer.length(); i++) {
            l[i] = longer.charAt(i) - '0';
        }
        for (int i = (shorter.length()); i > 0; i--, leadingZeros--) {
            s[i] = shorter.charAt(i - 1) - '0';
        }
        for (int i = leadingZeros; i >= 0; i--) { //fill the rest of the array with 0
            s[i] = 0;
        }


        int count = longer.length() - 1; // tracks where we stopped filling in the char array

        /*
         * Starting from the last digit and working backwards, i is the index of the
         * shorter string, j is the index of the char array currently being filled and k
         * is the index of the longer string
         */

            for (int i = shorter.length(), j = longer.length(), k = longer.length() - 1; i >= 0; i--, j--, k--) {
                count--;
                sum = l[k] + s[i];
                if (sum > 9) { // if the sum is 10 or greater, need to carry over the 1
                    a[j] = (char) ((sum % 10) + '0');
                    l[k - 1]++;
                } else {
                    a[j] = (char) (sum + '0');
                }
            }

            if (l[0] > 9) {
                a[1] = (char) ((sum % 10) + '0');
                a[0] = '1';
            } else {
                a[1] = (char) ((l[0]) + '0');
                a[0] = '0';
            }

            for (int i = count; i > 0; i--) { // fill in the rest but stop at l[1] (dont fill in l[0])
                a[i + 1] = (char) (l[i] + '0');
            }

            // filling in a[1] and a[0]
            if (l[0] > 9) {
                a[1] = (char) ((l[0] % 10) + '0');
                a[0] = '1';
            } else {
                a[1] = (char) ((l[0]) + '0');
                a[0] = '0';
            }

        String r = "";
        if (a[0] != '0') { // if the first character in a is 1 then use the whole array
            for (char ch : a) { // turn it back into a string
                r = r + ch;
            }
        } else {
            for (int i = 1; i < a.length; i++) {
                char ch = a[i];
                r = r + ch;
            }
        }

        System.out.println("");
        // pass it to comma() to print it out
        comma(r);
    }
}