
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
                spacing(longer, shorter); //although they are the same length, we still need to print an extra space before each operand
                comma(in1);
                System.out.println("+");
                spacing(longer, shorter);
                comma(in2);
            } else if (in2.length() > in1.length()) { // if the 2nd number is longer than the first
                longer = commaLength(in2); // get the length of the longer number
                shorter = commaLength(in1);
                spacing(longer, shorter);
                comma(in1);
                System.out.println("+");
                comma(in2);
            } else {
                longer = commaLength(in1); // get the length of the longer number after adding commas
                shorter = commaLength(in2); //get the length of the shorter number after adding commas
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
            } else if (in2.length() > in1.length()) {
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
        if (l == sh) { // need an extra space just in case there is a carry later during addition
            System.out.print(" ");
            return;
        }

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

    /**
     * Handles the calculations if both numbers are the same length
     *
     * @param firstNumber  is the first number entered
     * @param secondNumber is the second number entered
     */

    public static void resultEqualLength(String firstNumber, String secondNumber) {
        char[] a = new char[firstNumber.length() + 1];
        int[] l = new int[firstNumber.length() + 1];
        int[] s = new int[firstNumber.length()];
        int sum = 0;

        // populate the arrays
        for (int i = 0; i < firstNumber.length(); i++) {
            l[i + 1] = firstNumber.charAt(i) - '0';
            s[i] = secondNumber.charAt(i) - '0';
        }
        l[0] = 0;


        for (int i = firstNumber.length() - 1, j = firstNumber.length(); i >= 0; i--, j--) {
            sum = l[i + 1] + s[i];
            if (sum > 9) { // if the sum is 10 or greater, need to carry over the 1
                a[j] = (char) ((sum % 10) + '0');
                l[i]++;
            } else {
                a[j] = (char) (sum + '0');
            }
        }

        // filling in a[1] and a[0]
        if (sum >= 10) {
            a[1] = (char) ((sum % 10) + '0');
            a[0] = '1';
        } else
            a[0] = '0';


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
        int[] l = new int[longer.length() + 1];
        int[] s = new int[longer.length()];
        int sum = 0, leadingZeros = (longer.length() - shorter.length());


        for (int i = leadingZeros; i > 0; i--) { //put leading zeroes to make the shorter string the same length
            shorter = "0" + shorter;
        }
        // populate the arrays
        for (int i = 0; i < longer.length(); i++) {
            l[i + 1] = longer.charAt(i) - '0';
            s[i] = shorter.charAt(i) - '0';
        }
        l[0] = 0;


        for (int i = longer.length() - 1, j = longer.length(); i >= 0; i--, j--) {
            sum = l[i + 1] + s[i];
            if (sum > 9) { // if the sum is 10 or greater, need to carry over the 1
                a[j] = (char) ((sum % 10) + '0');
                l[i]++;
            } else {
                a[j] = (char) (sum + '0');
            }
        }

        // filling in a[1] and a[0]
        if (sum >= 10) {
            a[1] = (char) ((sum % 10) + '0');
            a[0] = '1';
        } else
            a[0] = '0';


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