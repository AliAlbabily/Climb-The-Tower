package control;

import model.GameManager;
import view.ButtonType;
import view.GameGUI;
import view.MainFrame;
import view.StartMenuGUI;

import javax.swing.*;

public class Controller {

    private MainFrame view;
    private GameManager model;
    private StartMenuGUI startMenuGUI;
    private GameGUI gameGUI;

    public Controller() {
        startMenuGUI = new StartMenuGUI(this);
        init(); // kan bytas mot startNewGame()
    }

    private void init() {
        view = new MainFrame();
        model = new GameManager();

        startGame();
    }

    private void startGame() {
        int choice = view.showMenu();

        switch(choice) {
            case 1:
                model.startLevels();
                break;
            case 2:
                System.out.println("Don't be a noob!");
                break;
            case 3:
                System.out.println("Exiting the game");
                System.exit(0);
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    public void buttonPressed(ButtonType button)
    {
        switch (button)
        {
            case Play:
                gameGUI = new GameGUI(this);
                startMenuGUI.closeClientConnectionWindow();
                break;

            default:
                JOptionPane.showMessageDialog(null,"Error");
        }
    }
}