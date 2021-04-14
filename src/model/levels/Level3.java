package model.levels;

import model.GameManager;
import model.Level;
import model.Monster;

/**
 * @author Mads Nörklit Johansen
 * @version 1.0
 */

public class Level3 extends Level {
    public Level3(String lvlName, GameManager gameManager) {
        super(lvlName, gameManager);
        setMonster(new Monster("Gargamel", 110));
    }

    @Override
    public double generateMathQuestion() {
        int a, b, c, firstOperator, secondOperator;
        char char_firstOperator, char_secondOperator;
        a = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        b = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10
        c = (int)(Math.random() * 10 + 1); // generera ett tal från 1 - 10

        firstOperator = (int)(Math.random() * 2 + 1); //Genererar ett tal från 1-2 för att slumpmässigt bestämma tecken
        secondOperator = (int)(Math.random() * 2 + 1); //Genererar ett tal från 1-2 för att slumpmässigt bestämma tecken

        if (firstOperator == 1) //Bestämmer vilket tecken som ska sättas i mattefrågan utifrån vår slumpmässiga siffra.
        {
            char_firstOperator = '+';
        }
        else
        {
            char_firstOperator = '-';
        }

        if (secondOperator == 1) //Bestämmer vilket tecken som ska sättas i mattefrågan utifrån vår slumpmässiga siffra.
        {
            char_secondOperator = '+';
        }
        else
        {
            char_secondOperator = '-';
        }

        System.out.println(a + " " + char_firstOperator + " "+ b + " " + char_secondOperator + " " + c + " " + " = ?"); // skriv ut frågan

        if (firstOperator == 1 && secondOperator == 1) //Retunerar rätt svar på uträkningen baserad på vilka operatorer som har valts ut.
        {
            return a + b + c;
        } else if (firstOperator == 1 && secondOperator == 2)
        {
            return a + b - c;
        }
       else if (firstOperator == 2 && secondOperator == 1)
        {
            return a - b + c;

        } else
        {
            return a - b - c;
        }
    }
}
