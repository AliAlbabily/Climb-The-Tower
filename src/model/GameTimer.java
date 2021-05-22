package model;

import java.util.LinkedList;

/**
 * @author Ardian Glamniki
 * @version 1.2
 *
 * This class is the timer for the math questions, which counts down from a set time and invokes the callback functions
 * when the timer runs out.
 */
public class GameTimer extends Thread {
    private LinkedList<TimerCallback> callbackList = new LinkedList<>();
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
            if (gameManager.getGameHasEnded()) {
                break;
            }
            if (seconds == 0) {
                for (TimerCallback cb: callbackList) {
                    cb.timesUp();
                }
                break;
            }
            seconds--;
            for (TimerCallback cb: callbackList) {
                cb.onTick(seconds);
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

    // The actual countdown time in seconds
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
