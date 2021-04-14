package model;

import model.levels.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * @author Ali-A
 * @version 1.2
 */
public class GameManager implements TimerCallback{

    private Player player;
    private LinkedList<Level> lvls = new LinkedList<>(); // ska fungera enligt (First In First Out)
    private Level currentLevel = null;
    private Monster currentMonster = null;
    private String currentMathQuestion;
    private boolean gameHasEnded = false;

    public GameManager(Player player) {
        this.player = player;

        // fill levels-list with new levels
        lvls.addFirst(new Level1("Level 1", this));
        lvls.addFirst(new Level2("Level 2", this));
        //       lvls.addFirst(new Level3("Level 3", this));
        lvls.addFirst(new Level4("Level 4", this));
        lvls.addFirst(new Level5("Level 5", this));
        lvls.addFirst(new Level6("Level 6", this));
//        lvls.addFirst(new Level7("Level 7", this));
//        lvls.addFirst(new Level8("Level 8", this));
//        lvls.addFirst(new Level9("Level 9", this));
//        lvls.addFirst(new Level10("Level 10", this));
    }

    private boolean checkUserAnswer(double correctAnswer, double userAnswer) {
        if(userAnswer == correctAnswer) {
            return true;
        }
        return false;
    }

    public void startAtFirstLevel() {
        nextLevel();
    }

    public void nextLevel() {
        if(!lvls.isEmpty()) { // hämta nästa nivå så länge listan inte är tom
            currentLevel = lvls.getLast();
            currentMonster = currentLevel.getMonster();
            String lvlName = currentLevel.getLvlName();
            String monsterName = currentMonster.getName();
            currentLevel.newLvlMessage(lvlName, monsterName);
        }
    }

    public double getNewMathQuestion() throws FileNotFoundException {
        return currentLevel.generateMathQuestion();
    }


    public boolean handleUserAnswer(double userAnswer, double currentCorrectAnswer) {
        int damage = 0;
        boolean ifCharacterIsDead = false;
        //  boolean gameHasEnded = false;

        boolean userAnswerIsCorrect = checkUserAnswer(currentCorrectAnswer, userAnswer);

        if(userAnswerIsCorrect) {
            damage = 30;
            currentMonster.takeDamage(damage);
            System.out.println("You dealt " + damage +" damage to the monster!");
            ifCharacterIsDead = currentMonster.checkIfAlive();
            if (ifCharacterIsDead) {
                System.out.println("You defeated: " + currentMonster.getName());

                lvls.removeLast();
                nextLevel();

                // when there are no more levels available in the lvls-list
                if(lvls.isEmpty()) {
                    System.out.println("\nYou won!");
                    gameHasEnded = true;
                    return gameHasEnded;
                }
            }
        } else {
            damage = 10;
            player.takeDamage(damage);
            System.out.println("Oh no, the monster dealt " + damage +" damage to you!");
            ifCharacterIsDead = player.checkIfAlive();
            if (ifCharacterIsDead) {
                System.out.println("You Died!");
                gameHasEnded = true;
                return gameHasEnded;
            }
        }

        return gameHasEnded;
    }

    public boolean isGameHasEnded(){
        return gameHasEnded;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public String getCurrentMathQuestion() {
        return currentMathQuestion;
    }

    public void setCurrentMathQuestion(String currentMathQuestion) {
        this.currentMathQuestion = currentMathQuestion;
    }

    @Override
    public void timesUp() {
        player.takeDamage(10);
        System.out.println("Times up! You need to be faster than that!");
        try {
            getNewMathQuestion();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}