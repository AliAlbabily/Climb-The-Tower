package model;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Level {

    private ImageIcon image;
    private HashMap<String, Integer> map = new HashMap<>();
    private ArrayList<String> mathProblems;

    public int generateMathQuestion() throws FileNotFoundException {
        mathProblems = MathProblems.getMathProblems("files/MathProblems");

        for (int i = 0; i < mathProblems.size(); i++) {
         String question = mathProblems.get(i).substring(0, mathProblems.get(i).indexOf('='));
         int answer = Integer.parseInt(mathProblems.get(i).substring(mathProblems.get(i).indexOf('=') + 2));
         map.put(question, answer);
        }

        String question = generateRandomQuestion();
        System.out.println(question + "= ?");
        return map.get(question);
    }
    public String generateRandomQuestion() {
        Random random = new Random();
        int index = random.nextInt(mathProblems.size());
        return mathProblems.get(index).substring(0, mathProblems.get(index).indexOf('='));
    }
}
