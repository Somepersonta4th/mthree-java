package com.mthree.academy.c458.team3.SeanMeaney.JavaBasics.LuckySevens;

import java.util.Random;
import java.util.Scanner;

public class LuckySevens {
    public static void main(String[] args) {
        var s = new Scanner(System.in);
        var RNG  = new Random();
        int rolls = 0;
        int maxRolls = 0;

        System.out.println("How many dollars do you bet?");
        int dollars = Integer.parseInt(s.nextLine());

        int maxDollars = dollars;

        while (dollars > 0) {
            rolls++;
            int sum = RNG.nextInt(1,7) + RNG.nextInt(1,7);

            if (sum == 7) { //win
                dollars += 4;
                if (dollars >= maxDollars) { //track max
                    maxDollars = dollars;
                    maxRolls = rolls;
                }
            } else { //lose
                dollars--;
            }
        }

        System.out.println("You are broke after " + rolls + " rolls.");
        System.out.println("You should have quit after "+maxRolls+" rolls when you had $"+maxDollars+".");

    }
}
