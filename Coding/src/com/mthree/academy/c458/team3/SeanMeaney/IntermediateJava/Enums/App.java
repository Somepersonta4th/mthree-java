package com.mthree.academy.c458.team3.SeanMeaney.IntermediateJava.Enums;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("""
                1. Monday
                2. Tuesday
                etc...
                Enter the day:
                """);
        int in = Integer.parseInt(s.nextLine());

        Day currentDay;

        switch (in) {
            case 1:
                currentDay = Day.MONDAY;
                break;
            case 2:
                currentDay = Day.TUESDAY;
                break;
            case 3:
                currentDay = Day.WEDNESDAY;
                break;
            case 4:
                currentDay = Day.THRUSDAY;
                break;
            case 5:
                currentDay = Day.FRIDAY;
                break;
            case 6:
                currentDay = Day.SATURDAY;
                break;
            case 7:
                currentDay = Day.SUNDAY;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        System.out.println("Days until Friday:");
        switch (currentDay) {
            case MONDAY:
                System.out.println("4");
                break;
            case TUESDAY:
                System.out.println("3");
                break;
            case WEDNESDAY:
                System.out.println("2");
                break;
            case THRUSDAY:
                System.out.println("1");
                break;
            case FRIDAY:
                System.out.println("0");
                break;
            case SATURDAY:
                System.out.println("6");
                break;
            case SUNDAY:
                System.out.println("5");
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
