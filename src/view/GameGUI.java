package view;


import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jagtej Sidhu
 * @version 1.3
 */
public class GameGUI
{
    // java swing variables
    private JFrame frame;
    private JPanel pnlNorth;
    private JPanel pnlSouth;
    private JPanel playerHealthBarPanel;
    private JPanel enemyHealthBarPanel;
    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 24);

    private JTextField answerTextField;
    private JButton submitButton;

    private static JLabel timer;
    private JLabel enemyHealth;
    private JLabel playerHealth;
    private JLabel lblQuestion;
    private JLabel lblLevel;
    private JLabel enemyName;
    private JLabel lblplayer;
    private static JLabel streak;

    private JProgressBar playerHealthBar;
    private JProgressBar enemyHealthBar;

    private ImageIcon level1 = new ImageIcon("files/levels/level1.png");
    private ImageIcon level2 = new ImageIcon("files/levels/level2.png");
    private ImageIcon level3 = new ImageIcon("files/levels/level3NEW");
    private ImageIcon level4 = new ImageIcon("files/levels/level4.png");
    private ImageIcon level5 = new ImageIcon("files/levels/level5.png");
    private ImageIcon level6 = new ImageIcon("files/levels/level6.png");
    private ImageIcon level7 = new ImageIcon("files/levels/level7.png");
    private ImageIcon level8 = new ImageIcon("files/levels/level8.png");
    private ImageIcon level9 = new ImageIcon("files/levels/level9.png");
    private ImageIcon level10 = new ImageIcon("files/levels/level10.png");

    private JLabel lblLevel1 = new JLabel(level1);
    private JLabel lblLevel2 = new JLabel(level2);
    private JLabel lblLevel3 = new JLabel(level3);
    private JLabel lblLevel4 = new JLabel(level4);
    private JLabel lblLevel5 = new JLabel(level5);
    private JLabel lblLevel6 = new JLabel(level6);
    private JLabel lblLevel7 = new JLabel(level7);
    private JLabel lblLevel8 = new JLabel(level8);
    private JLabel lblLevel9 = new JLabel(level9);
    private JLabel lblLevel10 = new JLabel(level10);

    //Object of controller class
    private Controller controller;

    /**
     * Constructor
     * @param controller
     */
    public GameGUI(Controller controller)
    {
        InitializePanels();
        this.controller = controller;
        // add action listener to submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buttonPressed(ButtonType.SubmitAnswer);
            }
        });
    }

    /**
     * Method that initializes all the other frames and jpanels
     */
    public void InitializePanels()
    {
        createMainFrame();
        NorthPanel();
        SouthPanel();
    }

    /**
     * Creation of the frame window
     */
    public void createMainFrame()
    {
        frame = new JFrame("Climb The Tower");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        centreWindow(frame);
    }

    /**
     * The north panel in the frame where the math questions are located
     */
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
        lblQuestion.setBounds(90, 125, 450, 50);

        answerTextField = new JTextField();
        answerTextField.setBounds(75,175,200, 25);

        submitButton = new JButton("Submit Answer");
        submitButton.setBounds(75,225,130, 25);

        pnlNorth.add(lblQuestion);
        pnlNorth.add(answerTextField);
        pnlNorth.add(submitButton);

        frame.add(pnlNorth, BorderLayout.NORTH);
        frame.pack();
    }

    /**
     * The bottom part of the window where health, level, and timer is being showned.
     */
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
        pnlSouth.add(timeLeft);

        timer = new JLabel("---");
        timer.setForeground(Color.green);
        timer.setFont(font);
        timer.setBounds(130, 50, 50, 50);
        pnlSouth.add(timer);

//        playerHealthBarPanel = new JPanel();
//        playerHealthBarPanel.setBounds(860,100, 100, 30);
//        playerHealthBarPanel.setBackground(Color.black);
//        pnlSouth.add(playerHealthBarPanel);
//
//        playerHealthBar = new JProgressBar(0,100);
//        playerHealthBar.setPreferredSize(new Dimension(100, 30));
////        playerHealthBar.setForeground(Color.green);
//        playerHealthBarPanel.add(playerHealthBar);

//        enemyHealthBarPanel = new JPanel();
//        enemyHealthBarPanel.setBounds(860,60, 100, 25);
//        pnlSouth.add(enemyHealthBar);
//
//        enemyHealthBar = new JProgressBar(0,80);
//        enemyHealthBar.setPreferredSize(new Dimension(100, 30));
//        enemyHealthBarPanel.add(enemyHealthBar);

        JLabel lblenemy = new JLabel("Enemy:");
        lblenemy.setForeground(Color.white);
        lblenemy.setFont(font);
        lblenemy.setBounds(760,50, 100, 50);
        pnlSouth.add(lblenemy);


        lblplayer = new JLabel("You:");
        lblplayer.setForeground(Color.white);
        lblplayer.setFont(font);
        lblplayer.setBounds(792,90, 100, 50);
        pnlSouth.add(lblplayer);

        enemyHealth = new JLabel();
        enemyHealth.setFont(font);
        enemyHealth.setBounds(860,50, 100, 50);
        pnlSouth.add(enemyHealth);

        playerHealth = new JLabel();
        playerHealth.setFont(font);
        playerHealth.setBounds(860,90, 100, 50);
        pnlSouth.add(playerHealth);

        lblLevel = new JLabel();
        lblLevel.setForeground(Color.white);
        lblLevel.setFont(font);
        lblLevel.setBounds(433,85, 100, 50);
        pnlSouth.add(lblLevel);

        JLabel lblStreak = new JLabel("Streak:");
        lblStreak.setForeground(Color.white);
        lblStreak.setFont(font);
        lblStreak.setBounds(25,140, 100, 50);
        pnlSouth.add(lblStreak);

        //TODO koppla denna jlabeln till streak nummer så att man kan se vilken streak man ligger på i GUI:t
        streak = new JLabel("0");
        streak.setForeground(Color.white);
        streak.setFont(font);
        streak.setBounds(105, 140, 50, 50);
        pnlSouth.add(streak);

        enemyName = new JLabel();
        enemyName.setForeground(Color.white);
        enemyName.setFont(font);
        enemyName.setBounds(425,120, 250, 50);
        pnlSouth.add(enemyName);

        frame.add(pnlSouth, BorderLayout.SOUTH);
        frame.pack();
    }

    // parse value from string to double and return it
    public String getUserAnswer() {
        String answerStr = answerTextField.getText();
        answerTextField.setText("");
        return answerStr;
    }

    /**
     * Updates the streak label
     * @param count
     */
    public void updateStreak(int count)
    {
        streak.setText("" + count + "");
    }


    public void updateCharactersHPGUI(int playerHP, int enemyHP) {
        String playerHPStr = Integer.toString(playerHP);
        String enemyHPStr = Integer.toString(enemyHP);
        playerHealth.setText(playerHPStr);
        enemyHealth.setText(enemyHPStr);

//        playerHealthBar.setValue(playerHP);
//        playerHealthBar.setBackground(Color.black);
//        playerHealthBar.setStringPainted(true);

//        enemyHealthBar.setValue(enemyHP);
//        enemyHealthBar.setStringPainted(true);

        if (playerHP <= 30)
        {
            playerHealth.setForeground(Color.red);
        }

        if (playerHP <= 50)
        {
            playerHealth.setForeground(Color.orange);
        }

        if (playerHP > 50)
        {
            playerHealth.setForeground(Color.green);
        }

        if (enemyHP <= 30)
        {
            enemyHealth.setForeground(Color.red);
        }

        if (enemyHP <= 50)
        {
            enemyHealth.setForeground(Color.orange);
        }

        if (enemyHP > 50)
        {
            enemyHealth.setForeground(Color.green);
        }
    }

    public void updateMathQuestionGUI(String mathQuestionStr) {
        lblQuestion.setText(mathQuestionStr);
    }

    public void updateLevelNameGUI(String levelName) {
        lblLevel.setText(levelName);
    }

    public void updateLayoutGUI(String levelname)
    {
        if(levelname == "Level 1") {
            frame.repaint();
            lblLevel1.setSize(1000, 400);
            pnlNorth.add(lblLevel1);
        }

        if(levelname == "Level 2") {
            lblLevel1.setVisible(false);
            frame.repaint();
            lblLevel2.setSize(1000, 400);
            pnlNorth.add(lblLevel2);
        }
        if (levelname == "Level 3") {
            lblLevel2.setVisible(false);
            frame.repaint();
            lblLevel3.setSize(1000, 400);
            pnlNorth.add(lblLevel3);
        }

        if (levelname == "Level 4") {
            lblLevel3.setVisible(false);
            frame.repaint();
            lblLevel4.setSize(1000, 400);
            pnlNorth.add(lblLevel4);
        }

        if(levelname == "Level 5"){
            lblLevel4.setVisible(false);
            frame.repaint();
            lblLevel5.setSize(1000,400);
            pnlNorth.add(lblLevel5);
        }

        if (levelname == "Level 6")
        {
            lblLevel6.setSize(1000,400);
            lblLevel5.setVisible(false);
            frame.repaint();
            pnlNorth.add(lblLevel6);
        }

        if (levelname == "Level 7")
        {
            lblLevel7.setSize(1000,400);
            lblLevel6.setVisible(false);
            frame.repaint();
            pnlNorth.add(lblLevel7);
        }

        if (levelname == "Level 8")
        {
            lblLevel8.setSize(1000,400);
            lblLevel7.setVisible(false);
            frame.repaint();
            pnlNorth.add(lblLevel8);
        }

        if (levelname == "Level 9")
        {
            lblLevel9.setSize(1000,400);
            lblLevel8.setVisible(false);
            frame.repaint();
            pnlNorth.add(lblLevel9);
        }

        if (levelname == "Level 10")
        {
            lblLevel10.setSize(1000,400);
            lblLevel9.setVisible(false);
            frame.repaint();
            pnlNorth.add(lblLevel10);
        }
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

    /**
     * Closes the frame on command
     */
    public void closeGameGUI(){
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * Centers the frame according to the user's window size
     * @param frame the JFrame that needs to be centered
     */
    private void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public JLabel getTimer()
    {
        return timer;
    }

}

