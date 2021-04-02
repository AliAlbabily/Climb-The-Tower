package model.levels;

import model.Level;
import model.Monster;

/**
 * @author Hanis Saley
 * version 1.0
 */
public class Level6 extends Level {


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

        System.out.println(a + " * " + b + "/" + c);
        System.out.println(a*b/c);
        System.out.println("round down the answer for the time being");
        return a*b/c; //läsa från fil eller fixa så att man ska skriva in double?
    }
}
