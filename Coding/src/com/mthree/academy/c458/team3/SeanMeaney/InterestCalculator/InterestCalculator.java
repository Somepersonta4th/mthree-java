package com.mthree.academy.c458.team3.SeanMeaney.InterestCalculator;

import java.util.Scanner;

public class InterestCalculator {
    public static void main(String[] args) {

        double annualInterestRate;
        double currentBalance;
        int years;
        var s = new Scanner(System.in);

        //get inputs
        System.out.println("annual interest rate %:");
        annualInterestRate = Double.parseDouble(s.nextLine());
        System.out.println("initial amount of principal:");
        currentBalance = Double.parseDouble(s.nextLine());
        System.out.println("number of years the money is to stay in the fund:");
        years = Integer.parseInt(s.nextLine());

        //iterate for each year and print output
        for (int i = 0; i < years; i++) {
            System.out.println("Year "+i+":\n" +
                "Began with $" + currentBalance);
            double change = currentBalance * (annualInterestRate / 400);
            currentBalance+= change;
            System.out.println("Earned $"+ change +"\n" +
                    "Ended with $"+currentBalance);
        }
    }
}
