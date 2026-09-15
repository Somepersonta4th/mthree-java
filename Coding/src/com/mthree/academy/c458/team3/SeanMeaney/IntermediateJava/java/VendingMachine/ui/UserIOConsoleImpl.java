package ui;

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
}
