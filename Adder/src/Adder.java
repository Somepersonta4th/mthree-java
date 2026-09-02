import java.util.Scanner;
public class Adder {
    public static void main(String[] args) {
        int sum;
        int num1;
        int num2;
        String in1 = "";
        String in2 = "";
        var aScanner = new Scanner(System.in);

        System.out.println("First number to be added:");
        in1 = aScanner.nextLine();

        System.out.println("First number to be added:");
        in2 = aScanner.nextLine();

        sum = Integer.parseInt(in1) + Integer.parseInt(in2);

        System.out.println("Sum is: " + sum);
    }
}