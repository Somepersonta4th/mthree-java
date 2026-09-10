package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.ClassRoster.ui;

import java.util.Scanner;

//console-specific implementation of UserIO
public class UserIOConsoleImpl implements UserIO {

    private final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return SCANNER.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        while (true) {
            try {
                int in = Integer.parseInt(SCANNER.nextLine());
                if (in>min && in<max) {
                    return in;
                }
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Double.parseDouble(SCANNER.nextLine());
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        while (true) {
            try {
                double in = Double.parseDouble(SCANNER.nextLine());
                if (in>min && in<max) {
                    return in;
                }
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Float.parseFloat(SCANNER.nextLine());
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        while (true) {
            try {
                float in = Float.parseFloat(SCANNER.nextLine());
                if (in>min && in<max) {
                    return in;
                }
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Long.parseLong(SCANNER.nextLine());
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        while (true) {
            try {
                long in = Long.parseLong(SCANNER.nextLine());
                if (in>min && in<max) {
                    return in;
                }
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

}
