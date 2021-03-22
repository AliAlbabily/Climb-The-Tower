package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MathProblems {
    /*
    * This method fetches math problems from a file and puts them in a list
    * Returntype: Arraylist
    */
    public static ArrayList<String> getMathProblems(String file) throws FileNotFoundException {
        Scanner in = new Scanner(new File(file));

        ArrayList<String> mathProblems = new ArrayList<>();
        while(in.hasNextLine()) {
            mathProblems.add(in.nextLine());
        }
        in.close();
        return mathProblems;
    }
}
