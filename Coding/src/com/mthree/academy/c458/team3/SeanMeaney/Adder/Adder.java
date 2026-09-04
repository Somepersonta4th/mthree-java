package com.mthree.academy.c458.team3.SeanMeaney.Adder;

import java.util.Scanner;
public class Adder {
    public static void main(String[] args) {
        int sum;
        int num1 = 0;
        int num2 = 0;
        String in1 = "";
        String in2 = "";
        var aScanner = new Scanner(System.in);

        boolean validIn = false;
        while (!validIn) {
            try {
                System.out.println("First number to be added:");
                in1 = aScanner.nextLine();
                num1 = Integer.parseInt(in1);
                validIn = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number");
            }
        }

        validIn = false;
        while (!validIn) {
            try {
                System.out.println("Second number to be added:");
                in2 = aScanner.nextLine();
                num2 = Integer.parseInt(in2);
                validIn = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number");
            }
        }

        sum = num1 + num2;

        System.out.println("Sum is: " + sum);
    }
}