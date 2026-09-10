package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.UserIO;

import java.util.Scanner;

public class UserIOImpl implements UserIO{

    Scanner aScanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return aScanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Integer.parseInt(aScanner.nextLine());
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        while (true) {
            try {
                int in = Integer.parseInt(aScanner.nextLine());
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
                return Double.parseDouble(aScanner.nextLine());
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        while (true) {
            try {
                double in = Double.parseDouble(aScanner.nextLine());
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
                return Float.parseFloat(aScanner.nextLine());
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        while (true) {
            try {
                float in = Float.parseFloat(aScanner.nextLine());
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
                return Long.parseLong(aScanner.nextLine());
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        while (true) {
            try {
                long in = Long.parseLong(aScanner.nextLine());
                if (in>min && in<max) {
                    return in;
                }
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }
}

