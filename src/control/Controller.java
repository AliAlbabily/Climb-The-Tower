package control;

import model.*;
import view.ButtonType;
import view.EndGameGUI;
import view.GameGUI;
import view.HighscoreGUI;
import view.StartMenuGUI;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * @author Ali, Hanis, Ardian and Mads
 * @version 1.5
 */
public class Controller implements TimerCallback {

    private GameManager model;
    private StartMenuGUI startMenuGUI;
    private GameGUI gameGUI;
    private GameTimer timer;
    private HighscoreGUI highscoreGUI;
    private EndGameGUI endGameGui;

    private final PlayersList playersList = new PlayersList();

    private double currentCorrectAnswer = 0;
    int streak = 0;
    private boolean gameHasEnded = false;

    public Controller() {
        startNewGame();
    }

    private void startNewGame() {
        startMenuGUI = new StartMenuGUI(this);
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
                if (startMenuGUI.getPlayerName().equals(""))
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

    //Updates highscore taking the values from PlayersList class in model package and
    //transfering it to HighscoreGUi in view
    public void updateHighscoreListGUI(Player [] highscoreList){
        HighscoreGUI highscoreGUI = new HighscoreGUI(this);
        String[] list = playersList.convertObjListToStringList(highscoreList);
        playersList.printStringList(list);
        highscoreGUI.updateHighscoreGUI(list);
    }

    public Player[] checkIfPointsQualified(Player[] listOfPlayers, int points, int worstResult){

        if(points > worstResult){
            for(int i = 0; i < listOfPlayers.length; i++){
                int playerPoints = listOfPlayers[i].getPoints();

                if(points > playerPoints){
                    moveElementsToRight(i,listOfPlayers);

                    String playerName = startMenuGUI.getPlayerName();
                    listOfPlayers[i] = new Player(playerName, 100, points);
                    System.out.println();
                    break;
                }
            }
        }
        return listOfPlayers;
    }
    private void endGame() {
        System.out.println("\nGame Over!");
        gameGUI.closeGameGUI();
        setupEndGameWindow();
    }

    private void displayHighscores(){
        Player[] tempList = playersList.getHighScoreList();
        int points = model.getPoints();
        int worstResult = tempList[9].getPoints();
        tempList = checkIfPointsQualified(tempList, points, worstResult);
        playersList.setHighScoreList(tempList);
        updateHighscoreListGUI(tempList);
        endGameGui.getBtnHighscore().setEnabled(false);

    }
    private void moveElementsToRight(int index, Player[] listOfObjects){
        for(int i = listOfObjects.length-2; i >=index; i--){
            listOfObjects[i+1] = listOfObjects[i];
        }
    }

    public void setupEndGameWindow(){
        endGameGui = new EndGameGUI(this);
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