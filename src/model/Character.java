package model;


public class Character {

    private final int baseHitPoints;
    private int hitPoints = 0;
    private String name;

    public Character(String name, int hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.baseHitPoints = hitPoints;
    }

    public void takeDamage(int damage) {
        hitPoints = hitPoints - damage;
    }

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