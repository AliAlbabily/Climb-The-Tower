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
 * @version 1.5
 */
public class StartMenuGUI extends JPanel {
    //object of controller class
    private Controller controller;

    //Java swing variables
    private JTextField userTextField = new JTextField("Enter username here...");
    private JComboBox cmbCharacter = new JComboBox();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JPanel mainPnl;
    private JButton playBtn = new JButton();
    private JButton infoBtn = new JButton();
    private String playerName;
    private JLabel chooseChar;
    private final String music = "files/great_battle.wav";
    private BackgroundMusic backgroundMusic;
  //  private JComboBox cmbDifficulty;



    //Image variables
    private ImageIcon towerPic = new ImageIcon("files/towers.png");
    private JLabel myLabel;

    /**
     * Constructor
     *
     * @param controller Controller class
     */
    public StartMenuGUI(Controller controller) {
        this.controller = controller;
        initializeGUI();
        addListeners(); // add action-listeners to components
    }

    public void initializeGUI() {
        createFrame();
        createMenuBox();

        backgroundMusic = new BackgroundMusic();
        backgroundMusic.playMusic(music);
    }

    public void createFrame() {
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
    }

    public void createMenuBox() {

        Border blackline = BorderFactory.createLineBorder(Color.black);

        /**
         * Initializing of jpanel being used
         */
        mainPnl = new JPanel();
        mainPnl.setPreferredSize(new Dimension(300, 400));
        mainPnl.setLayout(null);
        panel.add(mainPnl);

        chooseChar = new JLabel("Choose Character");
        chooseChar.setForeground(Color.orange);
        chooseChar.setFont(new Font(Font.SERIF, Font.PLAIN,15));

        chooseChar.setBounds(95, 30, 200, 190);
        mainPnl.add(chooseChar);

        cmbCharacter = new JComboBox(controller.getCharacter());
        cmbCharacter.addActionListener(controller);
        cmbCharacter.setBounds(75, 140, 150, 25);
        mainPnl.add(cmbCharacter);

        /**
         * Text field where you type in your name
         */
        //    userTextField = new JTextField("Enter username here...");
        userTextField.setPreferredSize(new Dimension(200, 30));
        userTextField.setBounds(50, 200, 200, 30);
        userTextField.setBorder(blackline);
        userTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (userTextField.getText().equals("Enter username here...")) {
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
        infoBtn.setBounds(75, 295, 150, 25);
        infoBtn.setFont(new Font("", Font.BOLD, 10));
        infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "You versus the monsters!\nYou play the game by typing the the right mathematical answers for each question. \n" +
                        "An amount of correct answers are required to defeat the monsters. \n" +
                        "The further you progress the harder the questions become. \n" +
                        "You can also get bonus hits and points if you answer quickly before the timer" +
                        " runs out.\n" + "\nTip: You can turn the volume on or off by clicking the volume icon while playing the game\n" + "\nGood luck warrior!");
            }
        });
        mainPnl.add(infoBtn);

        /**
         * Background picture for the frame
         */
        myLabel = new JLabel(towerPic);
        myLabel.setSize(300, 400);
        mainPnl.add(myLabel);

        //Svårighetsgrad
//        cmbDifficulty = new JComboBox(controller.getDifficulty());
//        cmbDifficulty.addActionListener(controller);
//        cmbDifficulty.setBounds(75, 100, 150, 25);
//        mainPnl.add(cmbDifficulty);

        //Karaktärsval
       //


    }

        private void addListeners ()
        {
            playBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == playBtn) {
                        controller.buttonPressed(ButtonType.Play);
                    }
                }
            });
        }

        public void closeStartMenuGUIWindow() {
            frame.setVisible(false); // hide window
            frame.dispose(); // Destroy the JFrame object, (close window)
            backgroundMusic.stopMusic();
        }

        // get player name from user input (userTextField)
        public String getPlayerName () {
            playerName = userTextField.getText();
            return playerName;
        }

        /**
         * Centers the frame according to the user's window size
         * @param frame the JFrame that needs to be centered
         */
        private void centreWindow (Window frame){
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
            frame.setLocation(x, y);
        }
}

