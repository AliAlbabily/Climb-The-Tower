package model.levels;

import model.GameManager;
import model.Level;
import model.Monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Hanis Saley
 * @version 1.2
 */
public class Level6 extends Level {


    public Level6(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
        setMonster(new Monster("Vorkath", 160));
    }

    @Override
    public double generateMathQuestion() {
        int a, b, c, d;
        a = (int) (Math.random() * 5 + 1);
        c = (int) (Math.random() * 9 + 1);
        d = (int) (Math.random() * 10 + 1);
        b = a * c * d;


            String mathQuestionStr = a + " * " + b + "/" + c+ "= ?";
            getGameManager().setCurrentMathQuestion(mathQuestionStr); // save math question in GameManager
            return a * b / c;

    }
}
