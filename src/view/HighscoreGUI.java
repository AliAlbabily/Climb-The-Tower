package view;

import control.Controller;

import javax.swing.*;
import java.awt.*;

public class HighscoreGUI extends JFrame
{

    private JList list = new JList<String[]>();
    private JButton closeHS;
    private JFrame frame;

    private Controller controller;

    public HighscoreGUI()
    {
//        this.controller = controller;

        initializePanels();
    }

    public void initializePanels()
    {
        createHsFrame();
    }

    public void createHsFrame()
    {
        frame = new JFrame("Highscore");
        frame.setPreferredSize(new Dimension(350, 550));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    public void closeHsFrame()
    {
        setVisible(false);
        dispose();
    }

    public void createHighscore()
    {

    }

    public static void main(String[] args) {
        new HighscoreGUI();
    }
}
