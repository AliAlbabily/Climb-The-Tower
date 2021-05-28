package view;

import control.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Ardian Glamniki, Jagtej
 * @version 1.1
 *
 * This class contains a window with buttons easy, medium and hard.
 */

public class DifficultyGUI {
    private Controller controller;
    private final int width = 300;
    private final int height = 300;
    private JButton btnEasy;
    private JButton btnMedium;
    private JButton btnHard;
    private JFrame frame = new JFrame();

    public DifficultyGUI(Controller controller) {
        this.controller = controller;
        initializeGUI();
        addBtnListeners();
    }

    public void initializeGUI() {
        createFrame();
        createButtons();
    }

    public void createFrame() {
        frame.setTitle("Choose difficulty");
        frame.setLayout(new GridLayout(3,1,1,1));
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        centreWindow(frame);
    }

    private void createButtons() {
        Font font = new Font("Rockwell Extra Bold", 0, 14);
        btnEasy = new JButton("Easy");
        btnEasy.setFont(font);
        btnEasy.setForeground(Color.WHITE);
 //       btnEasy.setBackground(Color.GREEN);
        btnEasy.setBackground(new Color(153, 255, 51));
        btnMedium = new JButton("Medium");
        btnMedium.setFont(font);
        btnMedium.setForeground(Color.WHITE);
//        btnMedium.setBackground(Color.ORANGE);
        btnMedium.setBackground(new Color(255, 204, 51));
        btnHard = new JButton("Hard");
        btnHard.setFont(font);
        btnHard.setForeground(Color.WHITE);
 //       btnHard.setBackground(Color.RED);
        btnHard.setBackground(new Color(255,51,51));
        frame.add(btnEasy);
        frame.add(btnMedium);
        frame.add(btnHard);
    }

    private void addBtnListeners() {
        ActionListener l = new ButtonListeners();
        btnEasy.addActionListener(l);
        btnMedium.addActionListener(l);
        btnHard.addActionListener(l);
    }

    public void closeFrame() {
        frame.setVisible(false);
        frame.dispose();

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

    private class ButtonListeners implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnEasy) {
                controller.startGamePlay(DifficultyLevel.Easy);
            } else if (e.getSource() == btnMedium) {
                controller.startGamePlay(DifficultyLevel.Medium);
            } else if (e.getSource() == btnHard) {
                controller.startGamePlay(DifficultyLevel.Hard);
            }
        }
    }

}
