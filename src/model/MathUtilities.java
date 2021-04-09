package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MathUtilities {

    public static String generateRandomQuestion(ArrayList<String> mathProblems) {
        Random random = new Random();
        int index = random.nextInt(mathProblems.size());
        int offset = mathProblems.get(index).indexOf(',');
        return mathProblems.get(index).substring(0, offset);
    }
    public static ArrayList<String> getMathProblems(String filename) throws FileNotFoundException {
        ArrayList<String> mathQuestions = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            mathQuestions.add(scanner.nextLine());
        }
        scanner.close();
        return mathQuestions;
    }
}
