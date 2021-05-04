package view;

import control.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jagtej Sidhu, Hanis Saley
 * @version 1.2
 */

public class HighscoreGUI extends JFrame
{

    //java swing variables
    private JList list = new JList<String[]>();
    private JButton btnBack;
    private JFrame frame;
    private JPanel panel;

    private Controller controller;

    /**
     * Constructor
     * @param controller
     */
    public HighscoreGUI(Controller controller)
    {
        this.controller = controller;
        initializePanels();
    }

    /**
     * Method that initializes all the other frames and jpanels
     */
    public void initializePanels()
    {
        createHsFrame();
        createHighscore();
    }

    /**
     * Creation of the frame
     */
    public void createHsFrame()
    {
        frame = new JFrame("Highscore");
        frame.setPreferredSize(new Dimension(350, 550));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        centreWindow(frame);
    }

    /**
     * The window that players see
     */
    public void createHighscore()
    {
        BorderLayout layout = new BorderLayout();
        Border b2 = BorderFactory.createTitledBorder("Highscore");

        /**
         * Jpanel window
         */
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 550));
        panel.setLayout(layout);
        panel.setBorder(b2);

        /**
         * Back button on screen
         */
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    closeHsWindow();
                    controller.buttonPressed(ButtonType.Back);
            }
        });
        panel.add(btnBack, BorderLayout.SOUTH);
        panel.add(list, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
    }

    /**
     * Method that closes the frames window on command
     */
    public void closeHsWindow()
    {
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * Updates the current highscorelist
     * @param highscore
     */
    public void updateHighscoreGUI(String[] highscore)
    {
        list.setListData(highscore);
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
