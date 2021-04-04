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
 * @author Hanis Saley
 * version 1.0
 */
public class Level6 extends Level {

    private HashMap<String, Integer> map = new HashMap<>();
    private ArrayList<String> mathProblemsLevel6;

    public Level6(String lvlName) {
        super(lvlName);
        setMonster(new Monster("Vorkath", 160));
    }

    @Override
    public double generateMathQuestion() {
        int a, b, c;
        a = (int)(Math.random() * 10 +1);
        b = (int)(Math.random() * 10 +1);
        c = (int)(Math.random() * 10 +1);

        if(a * b > c){ // fixa så att man skriver in double?? eller läsa från fil?
            System.out.println(a + " * " + b + "/" + c);
            System.out.println(a*b/c);
            System.out.println("round down the answer första if satsen");
            return a*b/c;
        }
        else if(a * b < c){
            System.out.println(c + " * " + b + "/" +a);
            System.out.println(c*b/a);
            System.out.println("round down the answer if else satsen");
            return c*b/a;
        }
        else{
            System.out.println(c + " * " + a + "/" +b);
            System.out.println(c*a/b);
            System.out.println("round down the answer else satsen");
            return c*a/b;
        }
    }
}
