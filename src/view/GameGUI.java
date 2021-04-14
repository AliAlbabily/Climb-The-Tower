package view;


import control.Controller;

import javax.swing.*;
import java.awt.*;

public class GameGUI
{
    private JFrame frame;
    private JPanel pnlNorth;
    private JPanel pnlSouth;
    private JLabel lblQuestion;
    private JTextField answerTextField;

    private JLabel timeLeft;
    private JLabel timer;
    private JLabel lblenemy;
    private JLabel lblplayer;
    private JLabel enemyHealth;
    private JLabel playerHealth;
    private JLabel lblLevel;
    private JLabel lblEnemyName;
    private JLabel enemyName;



    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 24);



    public GameGUI(Controller controller)
    {
        InitializePanels();
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

        lblQuestion = new JLabel("35 + 11 = ?");
        lblQuestion.setForeground(Color.white);
        lblQuestion.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 30));
        lblQuestion.setBounds(100, 125, 200, 50);

        answerTextField = new JTextField();
        answerTextField.setBounds(75,175,200, 25);

        pnlNorth.add(lblQuestion);
        pnlNorth.add(answerTextField);

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
        //TODO - timern bytar färg enligt vad tiden ligger på
        //TODO - till exempel ifall tiden ligger mellan 6-10 så är det grönt, 4-5 orange och 0-3 rött.
        timeLeft = new JLabel("Time left:");
        timeLeft.setForeground(Color.white);
        timeLeft.setFont(font);
        timeLeft.setBounds(25, 50, 100, 50);

        timer = new JLabel("10");
        timer.setForeground(Color.green);
        timer.setFont(font);
        timer.setBounds(130, 50, 50, 50);

        lblenemy = new JLabel("Enemy:");
        lblenemy.setForeground(Color.white);
        lblenemy.setFont(font);
        lblenemy.setBounds(750,50, 100, 50);

        lblplayer = new JLabel("You:");
        lblplayer.setForeground(Color.white);
        lblplayer.setFont(font);
        lblplayer.setBounds(750,90, 100, 50);

        enemyHealth = new JLabel("100/100");
        enemyHealth.setForeground(Color.white);
        enemyHealth.setFont(font);
        enemyHealth.setBounds(850,50, 100, 50);

        playerHealth = new JLabel("100/100");
        playerHealth.setForeground(Color.white);
        playerHealth.setFont(font);
        playerHealth.setBounds(850,90, 100, 50);

        lblLevel = new JLabel("Level 1");
        lblLevel.setForeground(Color.white);
        lblLevel.setFont(font);
        lblLevel.setBounds(433,85, 100, 50);

        lblEnemyName = new JLabel("Enemy:");
        lblEnemyName.setForeground(Color.white);
        lblEnemyName.setFont(font);
        lblEnemyName.setBounds(380,120, 100, 50);

        enemyName = new JLabel("Chimera");
        enemyName.setForeground(Color.white);
        enemyName.setFont(font);
        enemyName.setBounds(470,120, 100, 50);





        pnlSouth.add(timeLeft);
        pnlSouth.add(timer);
        pnlSouth.add(lblenemy);
        pnlSouth.add(lblplayer);
        pnlSouth.add(enemyHealth);
        pnlSouth.add(playerHealth);
        pnlSouth.add(lblLevel);
        pnlSouth.add(enemyName);
        pnlSouth.add(lblEnemyName);

        frame.add(pnlSouth, BorderLayout.SOUTH);
        frame.pack();
    }

//    public static void main(String[] args)
//    {
//        GameGUI mainFrameGUI = new GameGUI(this);
//    }
}

