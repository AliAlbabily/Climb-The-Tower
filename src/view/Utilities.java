
package view;

import java.util.Scanner;

public class Utilities {
    public static void padChars(char chr, int count)
    {
        for (int i= 0; i < count; i++)
            System.out.print(chr);
        System.out.println();
    }

    public static int getInteger(int lowLimit, int upperLimit) {
        String str = String.format("Enter an Integer between %d and %d", lowLimit, upperLimit);
        System.out.println(str);

        int number = 0;
        boolean goodNumber = false;

        Scanner reader = new Scanner(System.in);
        do {
            number = reader.nextInt();

            goodNumber = (number >= lowLimit) && (number <= upperLimit);
            if(!goodNumber)
                System.out.println("Invalid Number! Try again.");

        } while(!goodNumber);

        return number;
    }

    public static int getInteger() {
        int number = 0;

        Scanner reader = new Scanner(System.in);
        number = reader.nextInt();

        return number;
    }

    public static String getString() {
        Scanner reader = new Scanner(System.in);
        boolean done = true;
        String str = "";

        do {
            done = reader.hasNextLine();

            if(done) {
                str = reader.nextLine();
                //delete spaces from the ends of the string
                str = str.trim();

                done = !(str.isEmpty() || str.isBlank());
            }
            else
                System.out.println("Give a valid String!");

        } while (!done);
        return str;
    }

    public static double getDouble() {
        Scanner reader = new Scanner(System.in);
        double number = 0;

        number = reader.nextDouble();

        return number;
    }
}
