package org.WileyEdgeCorp.FlooringMastery.ui;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{

    private final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
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
    public int readInt(String prompt,int min) {
        System.out.println(prompt);
        while (true) {
            try {
                int newInt = Integer.parseInt(SCANNER.nextLine());
                if (newInt>min) {
                    return newInt;
                }
                System.out.println("Int must be positive");
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public int readInt(String prompt, int min, boolean canBeEmpty) {
        System.out.println(prompt);
        while (true) {
            try {
                String raw = SCANNER.nextLine();
                if (canBeEmpty && raw.isEmpty()) {
                    return -1;
                }
                int newInt = Integer.parseInt(raw);
                if (newInt>min) {
                    return newInt;
                }
                System.out.println("Int must be positive");
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
                if (in>=min && in<=max) {
                    return in;
                }
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public int readInt(String prompt, int min, int max, boolean canBeEmpty) {
        System.out.println(prompt);
        while (true) {
            try {
                String raw = SCANNER.nextLine();
                if (canBeEmpty && raw.isEmpty()) {
                    return -1;
                }
                int in = Integer.parseInt(raw);
                if (in>=min && in<=max) {
                    return in;
                }
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid int");
        }
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return SCANNER.nextLine();
    }

    //gets Date type
    //requires format yyyy/MM/dd
    //return null if blank
    @Override
    public Date readDate(String s) {
        while (true) {
            try {
                String newDateString = readString("Enter date. Use yyyy/MM/dd format.");
                if (newDateString.equals("")) {
                    return null;
                }
                return new SimpleDateFormat("yyyy/MM/dd").parse(newDateString);
            } catch (ParseException ex) {
                print("Invalid date format. Please try again.");
            }
        }
    }

    @Override
    public BigDecimal readBigDecimal(String s) {
        while (true) {
            try {
                print(s);
                return new BigDecimal(SCANNER.nextLine());
            } catch (NumberFormatException ex) {
                print("Invalid number. Please try again.");
            }
        }
    }

    @Override
    public BigDecimal readBigDecimal(String s, boolean canBeEmpty) {
        while (true) {
            try {
                print(s);
                String raw = SCANNER.nextLine();
                if (canBeEmpty && raw.equals("")){
                    return null;
                }
                return new BigDecimal(raw);
            } catch (NumberFormatException ex) {
                print("Invalid number. Please try again.");
            }
        }
    }
}
