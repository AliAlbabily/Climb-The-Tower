package model.levels;

import model.Level;
import model.Monster;

/**
 * @author Mads Nörklit Johansen
 * @version 1.0
 */

public class Level2 extends Level {


    public Level2(String lvlName) {
        super(lvlName);
        setMonster(new Monster("Oog The Forest Wizard",90)); // instansiera ett monster i denna nivå
    }

    @Override
    public int generateMathQuestion() {
        int a, b;
        a = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        b = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        System.out.println(a + " - " + b + " = ?"); // skriv ut frågan
        return a - b;
    }
}
