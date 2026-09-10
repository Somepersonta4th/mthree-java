package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.SimpleCalculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (true) {

            System.out.println("""
                    Select operation:
                    1. addition
                    2. subtraction
                    3. multiplication
                    4. division
                    5. exit
                    """);

            String out = "Result = ";

            switch (Integer.parseInt(s.nextLine())) {
                case 1:
                    out += SimpleCalculator.add(get2Inputs(s));
                    break;
                case 2:
                    out += SimpleCalculator.subtract(get2Inputs(s));
                    break;
                case 3:
                    out += SimpleCalculator.multiply(get2Inputs(s));
                    break;
                case 4:
                    out += SimpleCalculator.divide(get2Inputs(s));
                    break;
                case 5:
                    System.out.println("Thank you for using this calculator");
                    return;
            }

            System.out.println(out);
        }
    }

    static double[] get2Inputs (Scanner s) {
        double[] out = new double[2];
        System.out.println("""
                Provide 2 numbers:
                """);
        out[0] = Double.parseDouble(s.nextLine());
        out[1] = Double.parseDouble(s.nextLine());
        return out;
    }
}
