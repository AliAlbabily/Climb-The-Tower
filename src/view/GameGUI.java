package view;


import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jagtej Sidhu
 * @version 1.2
 */
public class GameGUI
{
    private JFrame frame;
    private JPanel pnlNorth;
    private JPanel pnlSouth;
    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 24);

    private JTextField answerTextField;
    private JButton submitButton;
    private Controller controller;

    private static JLabel timer;
    private JLabel enemyHealth;
    private JLabel playerHealth;
    private JLabel lblQuestion;
    private JLabel lblLevel;
    private JLabel enemyName;
    private JLabel lblplayer;
    private static JLabel streak;

    public GameGUI(Controller controller)
    {
        this.controller = controller;
        InitializePanels();

        // add action listener to submit button
//        submitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.buttonPressed(ButtonType.SubmitAnswer);
//            }
//        });
    }

    public void InitializePanels()
    {
        createMainFrame();
        NorthPanel();
        SouthPanel();
    }

    public void createMainFrame()
    {
        frame = new JFrame("Climb The Tower");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    public void NorthPanel()
    {
        //TODO fixa bild till jpanel
        pnlNorth = new JPanel();
        pnlNorth.setPreferredSize(new Dimension(1000,400));
        pnlNorth.setBackground(Color.gray);
        pnlNorth.setVisible(true);
        pnlNorth.setLayout(null);

        lblQuestion = new JLabel();
        lblQuestion.setForeground(Color.white);
        lblQuestion.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 30));
        lblQuestion.setBounds(100, 125, 300, 50);

        answerTextField = new JTextField();
        answerTextField.setBounds(75,175,200, 25);

        submitButton = new JButton("Submit Answer");
        submitButton.setBounds(75,225,130, 25);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buttonPressed(ButtonType.SubmitAnswer);
            }
        });

        pnlNorth.add(lblQuestion);
        pnlNorth.add(answerTextField);
        pnlNorth.add(submitButton);

        frame.add(pnlNorth, BorderLayout.NORTH);
        frame.pack();
    }

    public void SouthPanel()
    {
        pnlSouth = new JPanel();
        pnlSouth.setPreferredSize(new Dimension(1000,200));
        pnlSouth.setBackground(Color.BLACK);
        pnlSouth.setVisible(true);
        pnlSouth.setLayout(null);

        //Labels för tiden som tickar neråt
        JLabel timeLeft = new JLabel("Time left:");
        timeLeft.setForeground(Color.white);
        timeLeft.setFont(font);
        timeLeft.setBounds(25, 50, 100, 50);

        timer = new JLabel("---");
        timer.setForeground(Color.green);
        timer.setFont(font);
        timer.setBounds(130, 50, 50, 50);

        JLabel lblenemy = new JLabel("Enemy:");
        lblenemy.setForeground(Color.white);
        lblenemy.setFont(font);
        lblenemy.setBounds(750,50, 100, 50);

        lblplayer = new JLabel("You:");
        lblplayer.setForeground(Color.white);
        lblplayer.setFont(font);
        lblplayer.setBounds(782,90, 100, 50);

        enemyHealth = new JLabel();
        enemyHealth.setForeground(Color.white);
        enemyHealth.setFont(font);
        enemyHealth.setBounds(860,50, 100, 50);

        playerHealth = new JLabel();
        playerHealth.setForeground(Color.white);
        playerHealth.setFont(font);
        playerHealth.setBounds(860,90, 100, 50);

        lblLevel = new JLabel();
        lblLevel.setForeground(Color.white);
        lblLevel.setFont(font);
        lblLevel.setBounds(433,85, 100, 50);

        JLabel lblStreak = new JLabel("Streak:");
        lblStreak.setForeground(Color.white);
        lblStreak.setFont(font);
        lblStreak.setBounds(25,140, 100, 50);

        //TODO koppla denna jlabeln till streak nummer så att man kan se vilken streak man ligger på i GUI:t
        streak = new JLabel("0");
        streak.setForeground(Color.white);
        streak.setFont(font);
        streak.setBounds(105, 140, 50, 50);

        enemyName = new JLabel();
        enemyName.setForeground(Color.white);
        enemyName.setFont(font);
        enemyName.setBounds(425,120, 250, 50);

        pnlSouth.add(timeLeft);
        pnlSouth.add(timer);
        pnlSouth.add(lblenemy);
        pnlSouth.add(lblplayer);
        pnlSouth.add(enemyHealth);
        pnlSouth.add(playerHealth);
        pnlSouth.add(lblLevel);
        pnlSouth.add(enemyName);
        pnlSouth.add(lblStreak);
        pnlSouth.add(streak);

        frame.add(pnlSouth, BorderLayout.SOUTH);
        frame.pack();
    }

    // parse value from string to double and return it
    public double getUserAnswer() {
        String answerStr = answerTextField.getText();
        answerTextField.setText("");
        return Double.parseDouble(answerStr);
    }

    public void updateCharactersHPGUI(int playerHP, int enemyHP) {
        String playerHPStr = Integer.toString(playerHP);
        String enemyHPStr = Integer.toString(enemyHP);
        playerHealth.setText(playerHPStr);
        enemyHealth.setText(enemyHPStr);
    }

    public void updateMathQuestionGUI(String mathQuestionStr) {
        lblQuestion.setText(mathQuestionStr);
    }

    public void updateLevelNameGUI(String levelName) {
        lblLevel.setText(levelName);
    }

    public void updateMonsterNameGUI(String currentMonsterName)
    {

        enemyName.setText(currentMonsterName);

        if (currentMonsterName == "Gargamel")
        {
            enemyName.setBounds(418,120, 250, 50);
        }
        if (currentMonsterName == "Wyvern" || currentMonsterName == "Vorkath")
        {
            enemyName.setBounds(430,120, 250, 50);
        }
        if (currentMonsterName == "Valyei")
        {
            enemyName.setBounds(440,120, 250, 50);
        }
        if (currentMonsterName == "Berry The Cherry")
        {
            enemyName.setBounds(380,120, 250, 50);
        }
        if (currentMonsterName == "Nosferatu Zodd")
        {
            enemyName.setBounds(390,120, 250, 50);
        }
        if (currentMonsterName == "Chrollo")
        {
            enemyName.setBounds(433,120, 250, 50);
        }
        if (currentMonsterName == "Dahaka")
        {
            enemyName.setBounds(436,120, 250, 50);
        }

    }

    public JLabel getTimer()
    {
        return timer;
    }

}

