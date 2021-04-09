package model.levels;

import model.Level;
import model.Monster;
import model.MathUtilities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Ardian Glamniki
 * @version 1.0
 *
 * Denna klass väljer slumpmässigt ut frågor från levels 1-8, men i en svårare variant.
 */
public class Level9 extends Level {

    public Level9(String lvlName) {
        super(lvlName);
        setMonster(new Monster("Chrollo", 140));
    }

    /*
     * Genererar en slumpmässig fråga som kan antingen vara addition, subtraktion, division, multiplikation, kvadratrot
     * eller ekvation. En av dessa väljs slumpmässigt ut med 16% chans för varje.
     */
    @Override
    public double generateMathQuestion() throws FileNotFoundException {
        Random random = new Random();

        double answer = 0;

        int placeholder = random.nextInt(100);

        if (placeholder < 16) {
            answer = generateAdditionQuestion();
        } else if (placeholder < 32) {
            answer = generateSubtractionQuestion();
        } else if (placeholder < 48) {
            answer = generateMultiplicationQuestion();
        } else if (placeholder < 64) {
            answer = generateDivisionQuestion();
        } else if (placeholder < 80) {
            answer = generateSqrQuestion();
        } else {
            answer = generateEquationQuestion();
        }

        return answer;
    }


    // Rekursiv metod som genererar division frågor där svaret måste vara ett heltal.
    private double generateDivisionQuestion() {
        int number1 = (int)(Math.random() * 100 + 1);
        int number2 = (int)(Math.random() * 100 + 1);

        double answer = 0;

        if(number1 % number2 == 0) {
            System.out.println(number1 + " / " + number2 + " = ?");
            answer = number1 / number2;
        }
        else {
            answer = generateDivisionQuestion();
        }
        return answer;
    }

    // Genererar slumpmässiga ekvationsfrågor från en fil.
    private double generateEquationQuestion() throws FileNotFoundException {
        ArrayList<String> questions = MathUtilities.getMathProblems("files/MathProblemLevel8.txt");
        HashMap<String, Double> questionToAnswer = new HashMap<>();
        for (int i = 0; i < questions.size(); i++) {
            int offset = questions.get(i).indexOf(',');

            // Frågan skiljs från svaret via ett kommatecken i filen.
            String question = questions.get(i).substring(0, offset);
            String strAnswer = questions.get(i).substring(offset + 1);
            int offse = strAnswer.indexOf('=');
            double answer = Double.parseDouble(strAnswer.substring(offse + 2));
            questionToAnswer.put(question, answer);
        }
        String question = MathUtilities.generateRandomQuestion(questions);
        System.out.println(question + " vad är x?");
        return questionToAnswer.get(question);
    }


    // Genererar additionsfrågor där både talen är mellan 1 - 100.
    private double generateAdditionQuestion() {
        int a, b = 0;
        a = (int)(Math.random() * 100 + 1);
        b = (int)(Math.random() * 100 + 1);
        System.out.println(a + " + " + b + " = ?");
        return a + b;
    }

    // Genererar subtraktionsfrågår där både talen är mellan 1 - 100.
    private double generateSubtractionQuestion() {
        int a, b;
        a = (int)(Math.random() * 100 + 1);
        b = (int)(Math.random() * 100 + 1);
        System.out.println(a + " - " + b + " = ?");
        return a - b;
    }
    // Genererar multiplikationsfrågor där både talen är mellan 5 - 14.
    private double generateMultiplicationQuestion() {
        int a, b;
        a = (int)(Math.random() * 10 + 5);
        b = (int)(Math.random() * 10 + 5);
        System.out.println(a + " * " + b + " = ?");
        return a * b;
    }

    // Genererar kvadratrotsfrågor mellan där både talen är mellan 1 - 15.
    private double generateSqrQuestion() {
        int a;
        a = (int)(Math.random() * 15 + 1);
        int squareroot = a*a;
        System.out.println("What's the square root of "+ squareroot + " ?");
        return a;
    }
}
