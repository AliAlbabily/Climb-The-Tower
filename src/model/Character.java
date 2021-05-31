package model;

/**
 * @author Ali-A
 * @version 1.2
 */
public class Character {

    private final int baseHitPoints;
    private int hitPoints = 0;
    private String name;

    public Character(String name, int hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.baseHitPoints = hitPoints;
    }

    /**
     * Decreases a certain amount of points from the character's hit points.
     * @param damage the amount of points to be decreased.
     */
    public void takeDamage(int damage) {
        hitPoints = hitPoints - damage;
    }

    /**
     * Checks if the character is still alive and returns a response on that.
     */
    public boolean checkIfAlive() {
        boolean dead = false;

        if(hitPoints <= 0) {
            hitPoints = 0; // HP can't get lower than 0
            dead = true;
        }
        return dead;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getBaseHitPoints() {
        return baseHitPoints;
    }

    public String getName() {
        return name;
    }
}