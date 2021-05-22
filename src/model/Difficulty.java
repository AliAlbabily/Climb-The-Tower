package model;

/**
 * @author Ardian Glamniki
 * @version 1.0
 *
 * This enum represents the difficulty levels.
 * Each enum constant is declared with values for the initial monster HP and the amount of seconds to count down from.
 */
public enum Difficulty {
    EASY(50, 30),
    MEDIUM(80, 20),
    HARD(100,10);

    private final int monsterHP;
    private final int countDown;

    Difficulty(int monsterHP, int countDown) {
        this.monsterHP = monsterHP;
        this.countDown = countDown;
    }

    public final int getMonsterHP() {
        return monsterHP;
    }

    public final int getCountDown() {
        return countDown;
    }
}
