package model.levels;

import model.Level;
import model.Monster;

/**
 * @author Hanis Saley
 * version 1.0
 */
public class Level5 extends Level {

    public Level5(String lvlName) {
        super(lvlName);
        setMonster(new Monster("Valyei",140));
    }

    @Override
    public double generateMathQuestion() {
        int a, b;
        a = (int)(Math.random() * 20 + 1);
        b = (int)(Math.random() * 20 + 1);
        System.out.println(a + " / " + b + " = ?");
        System.out.println(a/b);
        System.out.println("round down the answer for the time being");
        return a/b;  //läsa från fil eller fixa så att man ska skriva in double?
    }
}
