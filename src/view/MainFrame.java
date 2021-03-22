package view;

public class MainFrame {

    public int showMenu() {
        int choice = 0;

        System.out.println(); // ny rad
        Utilities.padChars('*', 40);
        System.out.println("What do you want to do? \n");

        System.out.println("1. Play");
        System.out.println("2. Tutorial");
        Utilities.padChars('*', 40);
        System.out.println(); // ny rad

        choice = Utilities.getInteger(1, 2);
        return choice;
    }
}
