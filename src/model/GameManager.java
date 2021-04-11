package model;

import model.levels.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class GameManager {

    private Player hero = new Player("Levi", 100);
    private LinkedList<Level> lvls = new LinkedList<>(); // ska fungera enligt (First In First Out)
    private Level currentLevel = null;
    private Monster currentMonster = null;

    public GameManager() {
//        lvls.addFirst(new Level1("Level 1"));
//        lvls.addFirst(new Level2("Level 2"));
//        lvls.addFirst(new Level3("Level 3"));
//        lvls.addFirst(new Level4("Level 4"));
//        lvls.addFirst(new Level5("Level 5"));
//          lvls.addFirst(new Level6("Level 6"));
//        lvls.addFirst(new Level7("Level 7"));
//        lvls.addFirst(new Level8("Level 8"));
//        lvls.addFirst(new Level9("Level 9"));
        lvls.addFirst(new Level10("Level 10"));

    }

    public void startLevels() {
        double answer = 0;
        int damage = 0;
        boolean ifCharacterIsDead = false;

        nextLevel();

        while (lvls.isEmpty() != true) { // spelet avslutas när det inte finns flera nivåer (i listan) att ta sig genom
            System.out.println("\n" + hero.getName() + "'s hp: " + hero.getHitPoints());
            System.out.println(currentMonster.getName() + "'s hp: " + currentMonster.getHitPoints() +"\n");

            try {
                answer = currentLevel.generateMathQuestion();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("\nAttack the enemy by entering the right number: ");
            int yourAnswer = getInteger(-1000, 1000);

            if(yourAnswer == answer) {
                damage = 30;
                currentMonster.takeDamage(damage);
                System.out.println("You dealt " + damage +" damage to the monster!");
                ifCharacterIsDead = currentMonster.checkIfAlive();
                if (ifCharacterIsDead) {
                    System.out.println("You defeated: " + currentMonster.getName());
                    lvls.removeLast();
                    nextLevel();
                }
            } else {
                damage = 10;
                hero.takeDamage(damage);
                System.out.println("Oh no, the monster dealt " + damage +" damage to you!");
                ifCharacterIsDead = hero.checkIfAlive();
                if (ifCharacterIsDead) {
                    System.out.println("You Died!");
                    break;
                }
            }
        }
        System.out.println("\nGame Over!");
    }

    private void nextLevel() {
        if(!lvls.isEmpty()) { // hämta nästa nivå så länge listan inte är tom
            currentLevel = lvls.getLast();
            currentMonster = currentLevel.getMonster();
            String lvlName = currentLevel.getLvlName();
            String monsterName = currentMonster.getName();
            currentLevel.newLvlMessage(lvlName, monsterName);
        }
    }

    private int getInteger(int lowLimit, int upperLimit) {
        int number = 0;
        boolean goodNumber = false;
        Scanner reader = new Scanner(System.in);
        do {
            number = reader.nextInt();
            goodNumber = (number >= lowLimit) && (number <= upperLimit);
            if(!goodNumber)
                System.out.println("Invalid Number! Try again.");
        } while(!goodNumber);
        return number;
    }
}