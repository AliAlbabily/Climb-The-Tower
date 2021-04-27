package view;

import control.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jagtej Sidhu
 * @version 1.0
 */
public class HighscoreGUI extends JFrame
{

    private JList list = new JList<String[]>();
    private JButton btnBack;
    private JFrame frame;
    private JPanel panel;

    private Controller controller;

    public HighscoreGUI(Controller controller)
    {
        this.controller = controller;
        initializePanels();
    }

    public void initializePanels()
    {
        createHsFrame();
        createHighscore();
    }

    public void createHsFrame()
    {
        frame = new JFrame("Highscore");
        frame.setPreferredSize(new Dimension(350, 550));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    public void createHighscore()
    {
        BorderLayout layout = new BorderLayout();
        Border b2 = BorderFactory.createTitledBorder("Highscore");

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 550));
        panel.setLayout(layout);
        panel.setBorder(b2);

        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    closeHsWindow();
                    controller.buttonPressed(ButtonType.Back);
            }
        });

        panel.add(list, BorderLayout.CENTER);
        panel.add(btnBack, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
    }




    public void closeHsWindow()
    {
        frame.setVisible(false);
        frame.dispose();

    }

    public void updateHighscoreGUI(String[] highscore)
    {
        list.setListData(highscore);
    }




}
