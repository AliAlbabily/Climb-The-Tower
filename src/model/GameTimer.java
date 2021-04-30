package model;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * @author Ardian Glamniki
 * @version 1.2
 *
 * This class is the timer for the math questions, which counts down from a set time and notifies the observers
 * when the timer runs out.
 */
public class GameTimer extends Thread {
    private LinkedList<TimerCallback> callbackList = new LinkedList<>();
    private JLabel timeLeftLbl;
    private int seconds;

    private GameManager gameManager;

    public GameTimer(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    // Setting the listeners
    public void addListener(TimerCallback callback) {
        if(callback != null) {
            callbackList.add(callback);
        }
    }

    // Asynchronous task which is the countdown timer.
    @Override
    public void run() {
        while (seconds >= 0) {

            // check if game has ended
            if(gameManager.getGameHasEnded()) {
                break; // stop executing the loop
            }

            seconds--;
            SwingUtilities.invokeLater(new Write(seconds)); // updates time left on GUI

            if (seconds == 0) {
                for (TimerCallback cb: callbackList) {
                    cb.timesUp();
                }
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { break; }
        }
    }

    // This is called when a question has been answered which terminates the counter thread.
    public void stopTimer() {
        this.interrupt();
    }

    public void setTimeLeftLbl(JLabel timeLeftLbl)
    {
        this.timeLeftLbl = timeLeftLbl;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    // Updates the GUI timer JLabel
    private class Write implements Runnable {
        private int timeLeft;

        public Write(int timeLeft) {
            this.timeLeft = timeLeft;
        }

        @Override
        public void run() {
            timeLeftLbl.setText(String.valueOf(timeLeft));
            if (timeLeft <= 7)
            {
                timeLeftLbl.setForeground(Color.orange);
            }

            if (timeLeft <= 4)
            {
                timeLeftLbl.setForeground(Color.red);
            }

            if (timeLeft >= 8)
            {
                timeLeftLbl.setForeground(Color.green);
            }
        }
    }
}
