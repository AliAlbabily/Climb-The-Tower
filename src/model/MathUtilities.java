package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Ardian Glamniki
 * @version 1.0
 *
 * This class is intended to provide useful methods to handle math questions.
 */
public class MathUtilities {

    /*
     * Takes a list of math questions as parameter
     * Returns a random question from the list of math questions, not including the answer
     */
    public static String generateRandomQuestion(ArrayList<String> mathProblems) {
        Random random = new Random();
        int index = random.nextInt(mathProblems.size());
        int offset = mathProblems.get(index).indexOf(',');
        return mathProblems.get(index).substring(0, offset);
    }

    /*
     * Takes a filename as parameter
     * Returns a list containing the questions and answers in the file
     */
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
