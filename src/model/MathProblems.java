package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MathProblems {
    public static ArrayList<String> getMathProblems(String file) throws FileNotFoundException {
        Scanner in = new Scanner(new File(file));
//Test
        ArrayList<String> mathProblems = new ArrayList<>();
        while(in.hasNextLine()) {
            mathProblems.add(in.nextLine());
        }
        in.close();
        return mathProblems;
    }
}
