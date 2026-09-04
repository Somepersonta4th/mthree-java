package com.mthree.academy.c458.team3.SeanMeaney.JavaBasics.FactorialCalculator;

import java.util.Scanner;

public class FactorialCalculator {
    public static void main(String[] args) {
        var s = new Scanner(System.in);
        int num = getInput(s,"Provide a positive integer between 1 and 10: ");
        System.out.println("Factorial: " + calcFactorial(num));
    }

    //return x!
    private static int calcFactorial(int x) {
        int total = 1;
        for (int i = 1; i < x + 1; i++) {
            total = total * i;
        }
        return total;
    }

    //retrieve and validate input
    private static int getInput(Scanner s, String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                int num = Integer.parseInt(s.nextLine());

                if (num >= 1 && num <= 10) {
                    return num;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid integer, try again");
            }
        }
    }

}