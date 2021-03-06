package model.levels;

import model.GameManager;
import model.Level;
import model.MathUtilities;
import model.Monster;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * @author Ardian Glamniki
 * @version 1.1
 *
 * Denna klass genererar slumpmässiga frågor från level 1-8 med tre termer per fråga.
 */
public class Level10 extends Level {

    public Level10(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
    }

    @Override

    /*
     * Denna metod genererar en mattefråga som kan antingen vara addition, subtraktion, division, ekvation
     * eller multiplikation. En av dessa väljs slumpmässigt ut och är jämt fördelad med 20% chans för varje.
     */
    public double generateMathQuestion() throws FileNotFoundException {
        Random random = new Random();

        double answer = 0;

        int rand = random.nextInt(100);

        if (rand < 20) {
            answer = generateAdditionQuestion();
        } else if (rand < 40) {
            answer = generateSubtractionQuestion();
        } else if (rand < 60) {
            answer = generateMultiplicationQuestion();
        } else if (rand < 80) {
            answer = generateDivisionQuestion();
        } else {
            answer = generateEquationQuestion();
        }

        return answer;
    }

    // Genererar division fråga med tre termer
    private double generateDivisionQuestion() {
        int number1 = (int)(Math.random() * 10 + 1);
        int placeholder = (int)(Math.random() * 10 + 1);
        int number2 = number1*placeholder;

        int answer = number2/number1;
        int placeholder1 = (int)(Math.random() * 10 + 1);

        int number3 = answer*placeholder1;


        getGameManager().setCurrentMathQuestion(number3 + " / " + "(" + number2 + " / " + number1 + ")" + " = ?");
        return number3/(number2/number1);
    }
    // Genererar additionsfråga med tre termer
    private double generateAdditionQuestion() {
        Random random = new Random();
        int a, b, c = 0;
        a = (int)(Math.random() * 100 + 1);
        int low = 10;
        int high = 100;
        b = random.nextInt(high-low) + low;
        c = random.nextInt(high-low) + low;
        getGameManager().setCurrentMathQuestion(a + " + " + b + " + " + c + " = ?");
        return a + b + c;
    }
    // Genererar subtraktionsfråga med tre temer
    private double generateSubtractionQuestion() {
        int a, b, c;
        a = (int)(Math.random() * 100 + 1);
        b = (int)(Math.random() * 100 + 1);
        c = (int)(Math.random() * 100 + 1);
        getGameManager().setCurrentMathQuestion(a + " - " + b + " - " + c + " = ?");
        return a - b - c;
    }

    // Genererar multiplikationsfråga med tre termer
    private double generateMultiplicationQuestion() {
        Random random = new Random();
        int a, b, c;
        a = (int)(Math.random() * 10 + 1);
        b = (int)(Math.random() * 10 + 1);
        c = (int)(Math.random() * 10 + 1);

        getGameManager().setCurrentMathQuestion(c + " " + "(" + a + " * " + b + ")" + " = ?");
        return (a * b) * c;
    }

    // Denna metod genererar ekvationsfrågor från en fil och är en svårare variant än frågorna som förekommer i level 8.
    private double generateEquationQuestion() throws FileNotFoundException {
        ArrayList<String> questions = MathUtilities.getMathProblems("files/MathProblemLevel10.txt");
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
        getGameManager().setCurrentMathQuestion(question);
        return questionToAnswer.get(question);
    }

}
