import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var s = new Scanner(System.in);
        float height = getFloatFromUser(s,"Window height:");
        float width = getFloatFromUser(s,"Window width:");

        //calculate outputs
        float area = height*width;
        float perimeter = 2*height + 2*width;
        float cost = 3.5f*area + 2.25f*perimeter;

        //provide outputs
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
        System.out.println("Cost: " + cost);
    }

    //get user to provide a float
    private static float getFloatFromUser(Scanner s, String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                String in = s.nextLine();
                return Float.parseFloat(in);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input, try again");
            }
        }
    }
}