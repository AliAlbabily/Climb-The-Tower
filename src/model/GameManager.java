package model;

import model.levels.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Ali-A, Ardian Glamniki and Mads Nörklit Johansen
 * @version 1.7
 */
public class GameManager implements TimerCallback{

    private Player player;
    private LinkedList<Level> lvls = new LinkedList<>(); // ska fungera enligt (First In First Out)
    private Level currentLevel = null;
    private Monster currentMonster = null;
    private String currentMathQuestion;
    private boolean gameHasEnded = false;
    private int streak = 0;
    private int damage = 0;
    private boolean ifCharacterIsDead = false;
    private boolean playerIsAlive = true;
    private final String correctAnswerSound = "files/minecraft_hit.wav";
    private final String wrongAnswerSound = "files/runescape-hit.wav";
    private Difficulty difficulty;

    public GameManager(Player player, Difficulty difficulty) {
        this.player = player;
        this.difficulty = difficulty;

        initializeLevels(difficulty);
    }

    // Creates the level objects, and adds them to the list containing the levels.
    private void initializeLevels(Difficulty difficulty) {
        Level level1 = new Level1("Level 1", this);
        level1.setMonster(new Monster("Chimera", difficulty.getMonsterHP()));
        lvls.addFirst(level1);

        Level level2 = new Level2("Level 2", this);
        level2.setMonster(new Monster("Graardor", difficulty.getMonsterHP()));
        lvls.addFirst(level2);

        Level level3 = new Level3("Level 3", this);
        level3.setMonster(new Monster("Gargamel", difficulty.getMonsterHP()));
        lvls.addFirst(level3);

        Level level4 = new Level4("Level 4", this);
        level4.setMonster(new Monster("Wyvern", difficulty.getMonsterHP()));
        lvls.addFirst(level4);

        Level level5 = new Level5("Level 5", this);
        level5.setMonster(new Monster("Valyei", (int) (difficulty.getMonsterHP() * 1.5)));
        lvls.addFirst(level5);

        Level level6 = new Level6("Level 6", this);
        level6.setMonster(new Monster("Vorkath", (int) (difficulty.getMonsterHP() * 1.5)));
        lvls.addFirst(level6);

        Level level7 = new Level7("Level 7", this);
        level7.setMonster(new Monster("Berry The Cherry", (int) (difficulty.getMonsterHP() * 1.5)));
        lvls.addFirst(level7);

        Level level8 = new Level8("Level 8", this);
        level8.setMonster(new Monster("Nosferatu Zodd", difficulty.getMonsterHP() * 2));
        lvls.addFirst(level8);

        Level level9 = new Level9("Level 9", this);
        level9.setMonster(new Monster("Chrollo", difficulty.getMonsterHP() * 2));
        lvls.addFirst(level9);

        Level level10 = new Level10("Level 10", this);
        level10.setMonster(new Monster("Dahaka", difficulty.getMonsterHP() * 2));
        lvls.addFirst(level10);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getMonsterStartingHP() {
        return currentMonster.getBaseHitPoints();
    }

    // Compares the users submitted answer with the correct answer
    private boolean checkUserAnswer(double correctAnswer, double userAnswer) {
        if(userAnswer == correctAnswer) {
            return true;
        }
        return false;
    }

    /*
     * This method is called when the user input is correct.
     * It increases the streak count and deals damage to the monster by decreasing its hitpoints.
     * If the monster is dead, the game advances to the next level by calling the method progressGame()
     */
    private void handleCorrectAnswer() {
        damage = 10;
        streak++;
        player.increasePoints();

        System.out.println("points"+player.getPoints());
        playSound(correctAnswerSound);
        streakManager(streak);

        currentMonster.takeDamage(damage);
        System.out.println("You dealt " + damage +" damage to the monster!");
        ifCharacterIsDead = currentMonster.checkIfAlive();
        if (ifCharacterIsDead) {
            JOptionPane.showMessageDialog(null, "Well fought, click ok to proceed to next level.");
            progressGame(); // advances to the next level
        }
    }

    /*
     * This method is called when the user input is wrong.
     * By calling this method,the streak counter is set to 0, the players hitpoints decreases
     * and the game will eventually end, when the player hitpoints is 0 or below.
     */
    private void handleWrongAnswer() {
        damage = 10;
        streak = 0; //Streak reset
        player.takeDamage(damage);
        playSound(wrongAnswerSound);
        System.out.println("Oh no, the monster dealt " + damage +" damage to you!");
        ifCharacterIsDead = player.checkIfAlive();
        if (ifCharacterIsDead) {
            endGamePlayerDead();
        }
    }

    // Sets the flag that ends the game when the player is dead
    private void endGamePlayerDead() {
        System.out.println("You Died!");
        gameHasEnded = true;
        playerIsAlive = false;
    }

    // For every correct answer by the user, the streak increases
    private void streakManager(int streak) {
        System.out.println("Amount of points: "+player.getPoints());
        if (streak==5) //Hade kunnat ändra till streak > 2 eller något för att alltid ge bonus poäng.
        {
            player.increasePoints();
            System.out.println("You're on a 5x streak! Keep going! \n10 bonus points added!");

        } else if (streak == 10)
        {
            player.increasePoints(20);
            System.out.println("You're on a 10x streak! \n20 bonus points added!");
        }
        else if (streak ==15)
        {
            player.increasePoints(30);
            System.out.println("Are you cheating? On a 15x stream! \n30 bonus points awarded!");
        }
        System.out.println("Current points: "+player.getPoints());
        System.out.println("Amount of correct in a row: " + streak);
    }

    // Advances to the next level by calling nextLevel() contributing to the game progress.
    private void progressGame() {
        System.out.println("You defeated: " + currentMonster.getName());

        System.out.println("20 bonus points for completing " + currentLevel.getLvlName()); //20 bonus poäng för att klara en level.
        lvls.removeLast();
        nextLevel();

        // When there are no more levels available in the lvls-list
        if(lvls.isEmpty()) {
            System.out.println("\nYou won!");
            System.out.println("A final reward of 100 bonus points for finishing the game. \nWell played warrior.");
            player.increasePoints(100); //100 bonus poäng för att klara spelet.
            gameHasEnded = true;
        }
    }
    public void startAtFirstLevel() {
        nextLevel();
    }

    // Advances to the next level by fetching it from arraylist which contains the levels.
    public void nextLevel() {
        if(!lvls.isEmpty()) { // hämta nästa nivå så länge listan inte är tom
            currentLevel = lvls.getLast();
            currentMonster = currentLevel.getMonster();
            String lvlName = currentLevel.getLvlName();
            String monsterName = currentMonster.getName();
            currentLevel.newLvlMessage(lvlName, monsterName);
        }
    }

    // Fetches a math question from the current level at hand.
    public double getNewMathQuestion() throws FileNotFoundException {
        return currentLevel.generateMathQuestion();
    }

    // Compares the user input with the generated correct answer to determine if the answer is correct or not.
    public boolean handleUserAnswer(double userAnswer, double currentCorrectAnswer) {
        boolean userAnswerIsCorrect = checkUserAnswer(currentCorrectAnswer, userAnswer);

        if(userAnswerIsCorrect) {
            handleCorrectAnswer();
        } else {
            handleWrongAnswer();
        }
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

    public int getPoints(){
        return player.getPoints();
    }

    public boolean getGameHasEnded() {
        return gameHasEnded;
    }

    public boolean playerIsAliveStatus() {
        return playerIsAlive;
    }

    /*
     * This callback function is invoked when the countdown timer is finished,
     * which punishes the player by reducing hitpoints.
     */
    @Override
    public void timesUp() {
        player.takeDamage(10);
        playSound(wrongAnswerSound);
        streak = 0;
        System.out.println("Times up! You need to be faster than that!");
        ifCharacterIsDead = player.checkIfAlive();
        if (ifCharacterIsDead) {
            endGamePlayerDead();
        }
    }

    @Override
    public void onTick(int seconds) {
        // Empty for now
    }

    // Plays audio from a given file.
    public static void playSound(String fileName) {
        try {
            File url = new File(fileName);
            Clip clip = AudioSystem.getClip();

            AudioInputStream ais = AudioSystem.
                    getAudioInputStream( url );
            clip.open(ais);
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}