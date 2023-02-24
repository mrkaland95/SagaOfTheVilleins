package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Game.Main;

public class baseMonster implements ICharacter {
    private Vector2 position;
    private Vector2 destinationPosition;
    private int currentHealth;
    private int maxHealth;
    private int strength;
    private int defense;
    private final float moveSpeed = Main.globalDefaultMoveSpeed;
    private boolean moving;

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
    public void moveToPosition(Vector2 Destination) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        destinationPosition = Destination;


        if (destinationPosition == null) return;

        // Temp variable until we can make the character go to the center of a tile.
        // Used to snap the character's position to the destination once it's within this threshold.
        float positionMarginOfError = 5.0f;

        if ((Math.abs(destinationPosition.x - position.x) > positionMarginOfError) ||
            (Math.abs(destinationPosition.y - position.y) > positionMarginOfError)) {
            float pathX = destinationPosition.x - position.x;
            float pathY = destinationPosition.y - position.y;
            float distanceToMove = (float) Math.sqrt(pathX * pathX + pathY * pathY);
            float directiontoMoveX = pathX / distanceToMove;
            float directiontoMoveY = pathY / distanceToMove;

            // TODO implement a "ramping" function so the character accelerates and slows down when moving.
            // Something similar to this.
            // https://frc1756-argos.github.io/ArgoBot-Drive-Training/tutorials/8/
            position.x += directiontoMoveX * deltaTime * this.moveSpeed;
            position.y += directiontoMoveY * deltaTime * this.moveSpeed;
        } else {
            // Is the player within the margin of error?
            // Then snap the player's position to the desired spot.
            position.x = destinationPosition.x;
            position.y = destinationPosition.y;
        }
    }

    @Override
    public boolean isMoving() {
        return this.moving;
    }
    @Override
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public void setStrength(int strength) {
    }

    @Override
    public void setHealth(int damageTaken, ICharacter character) {

    }
}
