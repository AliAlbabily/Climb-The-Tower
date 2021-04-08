package model.levels;

import model.Level;
import model.Monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


/**
 * @version 1.0
 * @author Ardian Glamniki
 */
public class Level8 extends Level {
    private ArrayList<String> mathProblems = new ArrayList<>();
    private HashMap<String, String> mathSolutions = new HashMap<>();
    private final static String file = "files/MathProblemLevel8.txt";

    public Level8(String lvlName) {
        super(lvlName);
        setMonster(new Monster("Nosferatu Zodd", 200));
    }

    private ArrayList<String> getMathProblems() throws FileNotFoundException {
        ArrayList<String> mathQuestions = new ArrayList<>();
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNextLine()) {
            mathQuestions.add(scanner.nextLine());
        }
        return mathQuestions;
    }

    @Override
    public double generateMathQuestion() throws FileNotFoundException {
        mathProblems = getMathProblems();
        HashMap<String, Double> map = new HashMap<>();

        for (int i = 0; i < mathProblems.size(); i++) {
            int offset = mathProblems.get(i).indexOf(',');
            String question = mathProblems.get(i).substring(0, offset);
            String strAnswer = mathProblems.get(i).substring(offset + 1);
            int offse = strAnswer.indexOf('=');
            double answer = Double.parseDouble(strAnswer.substring(offse + 2));
            map.put(question, answer);
        }
        String question = generateRandomMathQuestion();
        System.out.println(question + " vad är x?");
        return map.get(question);
    }
    private String generateRandomMathQuestion() {
        Random random = new Random();
        int index = random.nextInt(mathProblems.size());
        int offset = mathProblems.get(index).indexOf(',');
        return mathProblems.get(index).substring(0, offset);
    }

}
