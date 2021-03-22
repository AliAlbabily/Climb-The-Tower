package model;

public class Character {

    private int hitPoints = 0;

    public Character(int hitPoints) {
        this.hitPoints = hitPoints;
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
}
