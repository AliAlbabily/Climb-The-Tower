package model;

/**
 * @author Hanis, Mads and Ali
 * @version 1.1
 */
public class Player extends Character {

    private int points;

    public Player(String name, int hitPoints, int points) {
        super(name, hitPoints);
        this.points = points;
    }

    /**
     * Increases the points of the player by a default amount.
     */
    public void increasePoints() {
        points += 10;
    }

    /**
     * Increases the points of the player by a specific amount.
     * @param bonuspoints amount of extra points to be added to the player's points.
     */
    public void increasePoints(int bonuspoints) {
        points += bonuspoints; //Bonuspoäng som läggs till för x antal korrekta svar i rad.
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("Player's name: %s  Score:  %d",getName(),points);
    }
}