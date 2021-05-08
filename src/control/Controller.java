package control;

import model.*;
import view.ButtonType;
import view.EndGameGUI;
import view.GameGUI;
import view.HighscoreGUI;
import view.StartMenuGUI;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author Ali, Hanis, Ardian and Mads
 * @version 1.7
 */
public class Controller implements TimerCallback {

    private GameManager model;
    private StartMenuGUI startMenuGUI;
    private GameGUI gameGUI;
    private GameTimer timer;
    private HighscoreGUI highscoreGUI;
    private EndGameGUI endGameGui;
    private HighscoreList highscoreList;

    private final PlayersList playersList = new PlayersList();

    private double currentCorrectAnswer = 0;
    int streak = 0;
    private boolean gameHasEnded = false;

    public Controller() {
        startNewGame();
    }

    private void startNewGame() {
        startMenuGUI = new StartMenuGUI(this);
        highscoreList = new HighscoreList();
    }

    // update gameplay info on both GUI & console
    public void updateGamePlayInformation() {
        String currentLevelName;
        String currentMonsterName;
        String currentPlayerName;

        int playerHP = model.getPlayer().getHitPoints();
        int monsterHP = model.getCurrentMonster().getHitPoints();

        // update level name on GUI
        currentLevelName = model.getCurrentLevel().getLvlName();
        gameGUI.updateLevelNameGUI(currentLevelName);
        gameGUI.updateLayoutGUI(currentLevelName);

        // update monster name on GUI
        currentMonsterName = model.getCurrentMonster().getName();
        gameGUI.updateMonsterNameGUI(currentMonsterName);

        //update player name on GUI
        currentPlayerName = model.getPlayer().getName();
//        gameGUI.setName(currentPlayerName);

        updateCharacterHP(playerHP, monsterHP);

        generateMathQuestion();

        // update math question on console
        updateMathQuestion();
        // update math question on GUI

        System.out.println("\nAttack the enemy by entering the right number: ");
    }

    // Updates the level, monster and player name on the GUI

    // Displays the current health of the characters on the GUI and prints it on the console.
    private void updateCharacterHP(int playerHP, int monsterHP) {
        gameGUI.updateCharactersHPGUI(playerHP, monsterHP);
        System.out.println("\n" + model.getPlayer().getName() + "'s hp: " + playerHP);
        System.out.println(model.getCurrentMonster().getName() + "'s hp: " + monsterHP +"\n");
    }
    // Displays the current math question on the GUI and prints it on the console.
    private void updateMathQuestion() {
        String mathQuestionStr = model.getCurrentMathQuestion();
        gameGUI.updateMathQuestionGUI(mathQuestionStr);
        System.out.println(mathQuestionStr);
    }
    // Fetches a math question and starts the timer for the question.
    private void generateMathQuestion() {
        try {
            currentCorrectAnswer = model.getNewMathQuestion();

            timer = new GameTimer(model);
            timer.addListener(model);
            timer.addListener(this);

            setTimer(model.getCurrentLevel().getLvlName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // Initializes the player and timer object then starts starts the gameplay with a GUI.
    private void startGamePlay() {
        String playerName = startMenuGUI.getPlayerName();
        Player player = new Player(playerName, 100, 0);
        model = new GameManager(player);

        timer = new GameTimer(model);
        timer.addListener(model);
        timer.addListener(this);

        model.startAtFirstLevel();
        gameGUI = new GameGUI(this);
        updateGamePlayInformation();
        startMenuGUI.closeStartMenuGUIWindow();
    }

    public void buttonPressed(ButtonType button) {

        switch (button) {
            case Play:
                if (startMenuGUI.getPlayerName().equals("") || startMenuGUI.getPlayerName().equals("Enter username here..."))
                {
                    JOptionPane.showMessageDialog(null, "Please type your chosen name");
                }
                else {
                    startGamePlay();
                }
                break;
            case SubmitAnswer:
                try {
                    double userAnswer = Double.parseDouble(gameGUI.getUserAnswer());
                    if (userAnswer == currentCorrectAnswer)
                    {
                        streak++;
                        gameGUI.updateStreak(streak);
                    }
                    if (userAnswer != currentCorrectAnswer)
                    {
                        streak = 0;
                        gameGUI.updateStreak(0);
                    }

                    gameHasEnded = model.handleUserAnswer(userAnswer, currentCorrectAnswer);

                    if(gameHasEnded)
                    {
                        endGame();
                    }
                    else {
                        timer.stopTimer();
                        updateGamePlayInformation();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input, try again with a number!");
                }
                break;
            case PlayAgain:
                startNewGame();
                streak = 0; // reset amount of streak
                endGameGui.closeEndGameGUI(); // closes endGame window
                break;
            case Highscore:
                displayHighscores();
                break;
            case Back:
                endGameGui.getBtnHighscore().setEnabled(true);
                break;
            case Quit:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null,"Error");
        }
    }

    // Initializes the timer by setting the contents and starting the countdown thread.
    private void setTimer(String lvl) {
        timer.setTimeLeftLbl(gameGUI.getTimer());
        switch (lvl) {
            case "Level 1":
            case "Level 2":
            case "Level 3":
            case "Level 4":
                timer.setSeconds(11);
                break;
            case "Level 5":
            case "Level 6":
            case "Level 7":
                timer.setSeconds(21);
                break;
            default:
                timer.setSeconds(61);
        }
        timer.start();
    }

    private String[] convertToStringArray(LinkedList<Highscore> highscores) {
        String[] scores = new String[highscores.size()];

        for (int i = 0; i < highscores.size(); i++) {
            scores[i] = highscores.get(i).toString();
        }

        return scores;
    }

    private void endGame() {
        highscoreList.saveNewHighscore(new Highscore(model.getPlayer().getName(), model.getPlayer().getPoints()));
        System.out.println("\nGame Over!");
        gameGUI.closeGameGUI();

        boolean playerIsAlive = model.playerIsAliveStatus();

        if(playerIsAlive) { // if player is alive
            setupEndGameWindow(true);
        } else { // otherwise (player is dead)
            setupEndGameWindow(false);
        }
    }

    private void displayHighscores(){
        highscoreGUI = new HighscoreGUI(this);
        LinkedList<Highscore> hsList = highscoreList.topTenHighscores();
        String[] scores = convertToStringArray(hsList);
        playersList.printStringList(scores);
        highscoreGUI.updateHighscoreGUI(scores);
    }

    public void setupEndGameWindow(boolean playerIsAliveStatus){
        endGameGui = new EndGameGUI(this, playerIsAliveStatus);
    }

    // Callback function that is invoked when the countdown timer is finished.
    @Override
    public void timesUp() {
        if (!model.getGameHasEnded()) {
            updateGamePlayInformation();
        } else {
            endGame();
        }
    }
}