package model;

/**
 * @author Ardian Glamniki
 * @version 1.1
 *
 * This interface contains the callback methods which updates the game.
 */
public interface TimerCallback {

    // When the countdown reaches zero
    void timesUp();

    // For every second in the countdown loop
    void onTick(int seconds);
}
