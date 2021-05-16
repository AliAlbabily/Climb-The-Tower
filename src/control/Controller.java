package control;

import model.*;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * @author Ali, Hanis, Ardian Glamniki and Mads
 * @version 1.9
 */
public class Controller implements TimerCallback, ActionListener {

    private GameManager model;
    private StartMenuGUI startMenuGUI;
    private GameGUI gameGUI;
    private GameTimer timer;
    private HighscoreGUI highscoreGUI;
    private EndGameGUI endGameGui;
    private HighscoreList highscoreList;
    private DifficultyGUI difficultyGUI;

    private final PlayersList playersList = new PlayersList();

    private double currentCorrectAnswer = 0;
    private int streak = 0;
    private boolean gameHasEnded = false;
    private String chosenCharacterName = "Soldier"; // set to the default character

    public Controller() {
        startNewGame();
    }

    private void startNewGame() {
        startMenuGUI = new StartMenuGUI(this);
        highscoreList = new HighscoreList();
        chosenCharacterName = "Soldier";
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
        gameGUI.updateProgressBar(model.getMonsterStartingHP());

        // update monster name on GUI
        currentMonsterName = model.getCurrentMonster().getName();
        gameGUI.updateMonsterNameGUI(currentMonsterName);


        //update player name on GUI
        currentPlayerName = model.getPlayer().getName();
//        gameGUI.setName(currentPlayerName);

        updateCharacterHP(playerHP, monsterHP, model.getMonsterStartingHP());

        generateMathQuestion();

        // update math question on console
        updateMathQuestion();
        // update math question on GUI

        System.out.println("\nAttack the enemy by entering the right number: ");
    }

    // Updates the level, monster and player name on the GUI

    // Displays the current health of the characters on the GUI and prints it on the console.
    private void updateCharacterHP(int playerHP, int monsterHP, int monsterFullHP) {
        gameGUI.updateCharactersHPGUI(playerHP, monsterHP, monsterFullHP);
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
    public void startGamePlay(DifficultyLevel difficultyLevel) {
        String playerName = startMenuGUI.getPlayerName();
        Player player = new Player(playerName, 100, 0);

        Difficulty difficulty = checkChosenDifficulty(difficultyLevel);
        model = new GameManager(player, difficulty);

        timer = new GameTimer(model);
        timer.addListener(model);
        timer.addListener(this);

        model.startAtFirstLevel();
        gameGUI = new GameGUI(this);
        // TODO : call a method in gameGUI that changes background upon choice
        gameGUI.changeBackgroundOnCharacter(chosenCharacterName);

        updateGamePlayInformation();
        startMenuGUI.closeStartMenuGUIWindow();
        difficultyGUI.closeFrame();
    }

    private Difficulty checkChosenDifficulty(DifficultyLevel difficultyLevel) {
        Difficulty difficulty = Difficulty.EASY;
        switch (difficultyLevel) {
            case Easy:
                difficulty = Difficulty.EASY;
                break;
            case Medium:
                difficulty = Difficulty.MEDIUM;
                break;
            case Hard:
                difficulty = Difficulty.HARD;
        }
        return difficulty;
    }

    public void buttonPressed(ButtonType button) {

        switch (button) {
            case Play:
                if (startMenuGUI.getPlayerName().equals("") || startMenuGUI.getPlayerName().equals("Enter username here..."))
                {
                    JOptionPane.showMessageDialog(null, "Please type your chosen name");
                }
                else {
                    difficultyGUI = new DifficultyGUI(this);
                }
                break;
            case SubmitAnswer:
                submitUserAnswer();
                break;
            case PlayAgain:
                startNewGame();
                streak = 0; // reset amount of streak
                endGameGui.closeEndGameGUI(); // closes endGame window
                endGameGui = null; 

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
        Difficulty difficulty = model.getDifficulty();
        switch (lvl) {
            case "Level 1":
            case "Level 4":
            case "Level 2":
            case "Level 3":
                timer.setSeconds(difficulty.getCountDown());;
                break;
            case "Level 5":
            case "Level 6":
            case "Level 7":
                timer.setSeconds((int) (difficulty.getCountDown() * 2));
                break;
            default:
                if (difficulty == Difficulty.HARD) {
                    timer.setSeconds(difficulty.getCountDown() * 4);
                } else {
                    timer.setSeconds(difficulty.getCountDown() * 3);
                }
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
        if(endGameGui == null){
            setupEndGameWindow();
        }


        boolean playerIsAlive = model.playerIsAliveStatus();
        if (playerIsAlive) { // if player is alive
            endGameGui.displayYouWinMessage();
        } else { // otherwise (player is dead)
            endGameGui.displayYouLostMessage();
        }
    }

    // Displays the top ten highscores from the saved highscores file in the highscore GUI.
    private void displayHighscores(){
        highscoreGUI = new HighscoreGUI(this);
        LinkedList<Highscore> hsList = highscoreList.topTenHighscores();
        String[] scores = convertToStringArray(hsList);
        playersList.printStringList(scores);
        highscoreGUI.updateHighscoreGUI(scores);
    }

    public void setupEndGameWindow(){
        endGameGui = new EndGameGUI(this);
    }

    // Callback function that is invoked when the countdown timer is finished.
    @Override
    public void timesUp() {
        if (!model.getGameHasEnded()) {
            updateGamePlayInformation();
            streak = 0;
            gameGUI.updateStreak(streak);
        } else {
            endGame();
        }
    }

    public DifficultyLevel [] getDifficulty()
    {
        return DifficultyLevel.values();
    }

    public Characters [] getCharacter()
    {
        return Characters.values();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        var jComboBox = (JComboBox) e.getSource();
        if (jComboBox == null)
        {
            System.out.println("JComboBox is null!");
            return;
        }

//        var difficulty = (DifficultyLevel) jComboBox.getSelectedItem();
//        if(difficulty == null)
//        {
//            System.out.println("Choice is null");
//        }

        var character = (Characters) jComboBox.getSelectedItem();
        //System.out.println(character); // FIXME : delete this line later
        chosenCharacterName = character.name(); // saves the returned enum value into an instance-variable

        if (character == null)
        {
            JOptionPane.showMessageDialog(null, "Choice is null");
        }
    }

    public void submitUserAnswer() {
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
    }
}