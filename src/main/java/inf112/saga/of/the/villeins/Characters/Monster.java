package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;

public class Monster implements ICharacter {
    private Vector2 position;
    private Vector2 destination;
    private int health;
    private int maxHealth;
    private int strength;
    private int defense;



    @Override
    public void update() {

    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public float getxCurrentPosition() {
        return this.position.x;
    }

    @Override
    public float getyCurrentPosition() {
        return this.position.y;
    }

    @Override
    public void moveXAxis(float distance) {
        this.position.x += distance;
    }

    @Override
    public void moveYAxis(float distance) {
        this.position.y += distance;
    }

    @Override
    public void setPosition(float xPosition, float yPosition) {
        this.position = new Vector2(xPosition, yPosition);
    }

    @Override
    public void moveToPosition(float xDestination, float yDestination) {
    }
    @Override
    public void moveToPosition(Vector2 Destination) {
    }
    @Override
    public boolean isMoving() {
        return false;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public int getStrength() {
        return 0;
    }

    @Override
    public int getDefense() {
        return 0;
    }

    @Override
    public int getMaxHealth() {
        return 0;
    }

    @Override
    public void setStrength(int strength) {

    }

    @Override
    public void setHealth(int damageTaken, ICharacter character) {

    }
}
