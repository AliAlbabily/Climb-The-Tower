package view;

import control.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jagtej Sidhu, Hanis Saley
 * @version 1.3
 */
public class StartMenuGUI extends JPanel
{
    //object of controller class
    private Controller controller;

    //Java swing variables
    private JTextField userTextField = new JTextField("Enter username here...");
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton playBtn = new JButton();
    private JButton infoBtn = new JButton();
    private String  playerName;


    //Image variables
    private ImageIcon towerPic = new ImageIcon("files/towers.png");
    private JLabel myLabel;

    /**
     * Constructor
     * @param controller Controller class
     */
    public StartMenuGUI(Controller controller)
    {
        this.controller = controller;
        createMenuBox();
        addListeners(); // add action-listeners to components
    }

    public void createMenuBox()
    {
        /**
         * Inizialize of frame
         */
        frame = new JFrame("Climb The Tower");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
        centreWindow(frame);

        Border blackline = BorderFactory.createLineBorder(Color.black);

        /**
         * Initializing of jpanel being used
         */
        JPanel mainPnl = new JPanel();
        mainPnl.setPreferredSize(new Dimension(300, 400));
        mainPnl.setLayout(null);
        panel.add(mainPnl);

        /**
         * Text field where you type in your name
         */
    //    userTextField = new JTextField("Enter username here...");
        userTextField.setPreferredSize(new Dimension(200,30));
        userTextField.setBounds(50,200, 200, 30);
        userTextField.setBorder(blackline);
        userTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (userTextField.getText().equals("Enter username here..."))
                {
                    userTextField.setText("");
                }
            }
        });
        mainPnl.add(userTextField);

        /**
         * Play button on screen
         */
        playBtn = new JButton("PLAY");
        playBtn.setBounds(75, 245, 150, 40);
        mainPnl.add(playBtn);

        /**
         * Info button on screen
         */
        infoBtn = new JButton("Tutorial");
//        infoBtn.setBounds(15, 8, 40,40);
        infoBtn.setBounds(75, 295, 150, 25);
        infoBtn.setFont(new Font("", Font.BOLD, 10));
        infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"You versus the monsters!\n You play the game by typing the the right mathematical answers for each question. \n"+
                                "An amount of correct answers are required to defeat the monsters. \n"+
                                "The further you progress the harder the questions become. \n" +
                                "You can also get bonus hits and points if you answer quickly before the timer" +
                                " runs out.\n" + "Good luck warrior!");
            }
        });
        mainPnl.add(infoBtn);

        /**
         * Background picture for the frame
         */
        myLabel = new JLabel(towerPic);
        myLabel.setSize(300,400);
        mainPnl.add(myLabel);
    }

    private void addListeners()
    {
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == playBtn)
                {
                    controller.buttonPressed(ButtonType.Play);
                }
            }
        });
    }

    public void closeStartMenuGUIWindow() {
        frame.setVisible(false); // hide window
        frame.dispose(); // Destroy the JFrame object, (close window)
    }

    // get player name from user input (userTextField)
    public String getPlayerName() {
        playerName = userTextField.getText();
        return playerName;
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
}

